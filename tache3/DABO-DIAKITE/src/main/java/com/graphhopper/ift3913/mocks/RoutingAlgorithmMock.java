package com.graphhopper.ift3913.mocks;

import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.storage.GraphHopperStorage;
import com.graphhopper.util.Parameters;
import com.graphhopper.util.PMap;

/**
 * Mock simplifié de RoutingAlgorithm pour les tests de mutation.
 * Il reproduit des comportements minimaux suffisants pour les tests unitaires.
 */
public class RoutingAlgorithmMock {

    private final GraphHopperStorage graph;
    private final FlagEncoder encoder;
    private final AlgorithmOptions opts;
    private boolean used;

    public RoutingAlgorithmMock(GraphHopperStorage graph, FlagEncoder encoder, AlgorithmOptions opts) {
        this.graph = graph;
        this.encoder = encoder;
        this.opts = opts;
        this.used = false;
    }

    public boolean wasUsed() {
        return used;
    }

    public Path calcPath(int from, int to) {
        used = true;
        Path p = new Path(graph, encoder);
        p.setFromNode(from);
        p.setEndNode(to);
        p.setWeight(Math.abs(to - from));
        return p;
    }

    public PMap getHints() {
        PMap hints = new PMap();
        hints.putObject(Parameters.Routing.EDGE_BASED, opts.getHints().getBool(Parameters.Routing.EDGE_BASED, false));
        return hints;
    }

    public String getName() {
        return "MockAlgo";
    }

    public FlagEncoder getEncoder() {
        return encoder;
    }

    public AlgorithmOptions getOpts() {
        return opts;
    }
}
