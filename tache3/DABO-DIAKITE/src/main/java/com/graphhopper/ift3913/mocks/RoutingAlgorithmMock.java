package com.graphhopper.ift3913.mocks;

import com.graphhopper.routing.Path;
import com.graphhopper.util.PMap;
import com.graphhopper.util.Parameters;

/**
 * Mock simplifié de RoutingAlgorithm pour tests.
 * Contient une petite logique interne pour PIT.
 */
public class RoutingAlgorithmMock {

    private final boolean directed;
    private final int speedLimit;

    public RoutingAlgorithmMock(boolean directed, int speedLimit) {
        this.directed = directed;
        this.speedLimit = speedLimit;
    }

    public Path calcPath(int distance) {
        // petite logique testable
        double time = (speedLimit > 0) ? (double) distance / speedLimit : Double.POSITIVE_INFINITY;
        boolean valid = time < 10.0 && directed;
        return new Path(null).setFound(valid);
    }

    public boolean isDirected() {
        return directed;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public PMap getRoutingHints() {
        PMap hints = new PMap();
        hints.putObject(Parameters.Routing.ALGORITHM, directed ? "astar" : "dijkstra");
        return hints;
    }
}
