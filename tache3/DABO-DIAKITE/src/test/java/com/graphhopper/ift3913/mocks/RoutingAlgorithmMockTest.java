package com.graphhopper.ift3913.mocks;


import com.graphhopper.ResponsePath;
import com.graphhopper.routing.algorithm.RoutingAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoutingAlgorithmMockTest {

    @Test
    void calcPath_returnsMockedResponse_andIsVerified() {
        RoutingAlgorithm algo = mock(RoutingAlgorithm.class);

        ResponsePath mockedPath = new ResponsePath();
        mockedPath.setDistance(123.45);
        mockedPath.setTime(6789L);

        when(algo.calcPath(42, 77)).thenReturn(mockedPath);

        ResponsePath out = algo.calcPath(42, 77);

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
