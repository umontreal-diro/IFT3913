package com.graphhopper.ift3913.mocks;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.Snap;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Vérifie la logique d'interaction entre LocationIndex et Weighting.
 * Teste le calcul d'un score entre deux points selon la validité des Snaps.
 */
class LocationIndexAndWeightingMockTest {

    static class RouteOrchestrator {
        private final LocationIndex locationIndex;
        private final Weighting weighting;

        RouteOrchestrator(LocationIndex li, Weighting w) {
            this.locationIndex = li;
            this.weighting = w;
        }

        double scoreBetween(GHPoint a, GHPoint b) {
            Snap sa = locationIndex.findClosest(a.lat, a.lon, null);
            Snap sb = locationIndex.findClosest(b.lat, b.lon, null);
            if (sa == null || sb == null || !sa.isValid() || !sb.isValid()) return -1;
            return weighting.getMinWeight(1) + weighting.getMinWeight(2);
        }
    }

    @Test
    void scoreBetween_validSnaps_usesBothMocks() {
        LocationIndex li = mock(LocationIndex.class);
        Weighting w = mock(Weighting.class);
        Snap sa = mock(Snap.class);
        Snap sb = mock(Snap.class);

        when(sa.isValid()).thenReturn(true);
        when(sb.isValid()).thenReturn(true);
        when(li.findClosest(45.0, -73.0, null)).thenReturn(sa);
        when(li.findClosest(46.0, -74.0, null)).thenReturn(sb);
        when(w.getMinWeight(1)).thenReturn(10.0);
        when(w.getMinWeight(2)).thenReturn(20.0);

        RouteOrchestrator r = new RouteOrchestrator(li, w);
        double score = r.scoreBetween(new GHPoint(45.0, -73.0), new GHPoint(46.0, -74.0));

        assertEquals(30.0, score, 1e-9);
        verify(li).findClosest(45.0, -73.0, null);
        verify(li).findClosest(46.0, -74.0, null);
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
        when(li.findClosest(45.0, -73.0, null)).thenReturn(bad);
        when(li.findClosest(46.0, -74.0, null)).thenReturn(bad);

        RouteOrchestrator r = new RouteOrchestrator(li, w);
        double score = r.scoreBetween(new GHPoint(45.0, -73.0), new GHPoint(46.0, -74.0));

        assertEquals(-1.0, score, 1e-9);
        verifyNoInteractions(w);
    }
}
