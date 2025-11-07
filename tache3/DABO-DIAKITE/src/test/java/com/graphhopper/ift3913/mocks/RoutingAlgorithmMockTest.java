package com.graphhopper.ift3913.mocks;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Vérifie le comportement d’un algorithme de routage simulé à l’aide de Mockito.
 * Teste le retour de calcPath() et la capture des arguments passés.
 */
class RoutingAlgorithmMockTest {

    
    static class MockResponse {
        private double distance;
        private long time;

        public double getDistance() { return distance; }
        public long getTime() { return time; }
        public void setDistance(double distance) { this.distance = distance; }
        public void setTime(long time) { this.time = time; }
    }

    interface MockRoutingAlgorithm {
        MockResponse calcPath(int from, int to);
    }

    @Test
    void calcPath_returnsMockedResponse_andIsVerified() {
        MockRoutingAlgorithm algo = mock(MockRoutingAlgorithm.class);
        MockResponse mockedPath = new MockResponse();
        mockedPath.setDistance(123.45);
        mockedPath.setTime(6789L);

        when(algo.calcPath(42, 77)).thenReturn(mockedPath);

        MockResponse out = algo.calcPath(42, 77);

        assertNotNull(out);
        assertEquals(123.45, out.getDistance(), 1e-9);
        assertEquals(6789L, out.getTime());

        ArgumentCaptor<Integer> from = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> to = ArgumentCaptor.forClass(Integer.class);
        verify(algo).calcPath(from.capture(), to.capture());
        assertEquals(42, from.getValue());
        assertEquals(77, to.getValue());
        verifyNoMoreInteractions(algo);
    }
}
