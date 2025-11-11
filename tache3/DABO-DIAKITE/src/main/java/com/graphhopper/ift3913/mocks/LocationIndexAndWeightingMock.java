package com.graphhopper.ift3913.mocks;

import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.index.LocationIndex;

/**
 * Mock combinant LocationIndex et Weighting pour tests unitaires.
 * Sert uniquement de conteneur pour vérifier leur interaction.
 */
public class LocationIndexAndWeightingMock {

    private final LocationIndex locationIndex;
    private final Weighting weighting;

    public LocationIndexAndWeightingMock(LocationIndex locationIndex, Weighting weighting) {
        this.locationIndex = locationIndex;
        this.weighting = weighting;
    }

    public LocationIndex getLocationIndex() {
        return locationIndex;
    }

    public Weighting getWeighting() {
        return weighting;
    }

    public boolean isValid() {
        return locationIndex != null && weighting != null;
    }

    public double computeWeight(double base, double factor) {
        if (weighting == null) return Double.NaN;
        return base * factor * weighting.getMinWeight(1);
    }
}
