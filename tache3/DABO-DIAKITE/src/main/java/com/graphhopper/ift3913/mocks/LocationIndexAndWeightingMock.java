package com.graphhopper.ift3913.mocks;

/**
 * Mock combinant deux valeurs pour tests.
 */
public class LocationIndexAndWeightingMock {

    private final double weight;
    private final int index;

    public LocationIndexAndWeightingMock(double weight, int index) {
        this.weight = weight;
        this.index = index;
    }

    public double computeScore() {
        // logique testable
        if (index < 0) return 0;
        return weight * (index % 5);
    }

    public boolean isHeavy() {
        return weight > 10;
    }

    public double getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }
}
