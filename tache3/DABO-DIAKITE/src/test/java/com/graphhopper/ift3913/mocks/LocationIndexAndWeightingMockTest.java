package test.java.com.graphhopper.ift3913.mocks;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.Snap;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationIndexAndWeightingMockTest {

    // petite classe locale pour orchestrer et tester les interactions
    static class RouteOrchestrator {
        private final LocationIndex locationIndex;
        private final Weighting weighting;
        RouteOrchestrator(LocationIndex li, Weighting w) { this.locationIndex = li; this.weighting = w; }

        double scoreBetween(GHPoint a, GHPoint b) {
            Snap sa = locationIndex.findClosest(a.lat, a.lon, null, 0);
            Snap sb = locationIndex.findClosest(b.lat, b.lon, null, 0);
            if (sa == null || sb == null || !sa.isValid() || !sb.isValid()) return -1;
            return weighting.getMinWeight(1) + weighting.getMinWeight(2);
        }
    }

    @Test
    void scoreBetween_validSnaps_usesBothMocks() {
        LocationIndex li = mock(LocationIndex.class);
        Weighting w = mock(Weighting.class);
        Snap sa = mock(Snap.class), sb = mock(Snap.class);
        when(sa.isValid()).thenReturn(true);
        when(sb.isValid()).thenReturn(true);
        when(li.findClosest(45.0, -73.0, null, 0)).thenReturn(sa);
        when(li.findClosest(46.0, -74.0, null, 0)).thenReturn(sb);
        when(w.getMinWeight(1)).thenReturn(10.0);
        when(w.getMinWeight(2)).thenReturn(20.0);

        RouteOrchestrator r = new RouteOrchestrator(li, w);
        double score = r.scoreBetween(new GHPoint(45.0, -73.0), new GHPoint(46.0, -74.0));

        assertEquals(30.0, score, 1e-9);
        verify(li).findClosest(45.0, -73.0, null, 0);
        verify(li).findClosest(46.0, -74.0, null, 0);
        verify(w).getMinWeight(1);
        verify(w).getMinWeight(2);
        verifyNoMoreInteractions(li, w);
    }

    @Test
    void scoreBetween_invalidSnap_returnsMinusOne_andSkipsWeighting() {
        LocationIndex li = mock(LocationIndex.class);
        Weighting w = mock(Weighting.class);
        Snap bad = mock(Snap.class);
        when(bad.isValid()).thenReturn(false);
        when(li.findClosest(45.0, -73.0, null, 0)).thenReturn(bad);
        when(li.findClosest(46.0, -74.0, null, 0)).thenReturn(bad);

        RouteOrchestrator r = new RouteOrchestrator(li, w);
        double score = r.scoreBetween(new GHPoint(45.0, -73.0), new GHPoint(46.0, -74.0));

        assertEquals(-1.0, score, 1e-9);
        verifyNoInteractions(w);
    }
}
