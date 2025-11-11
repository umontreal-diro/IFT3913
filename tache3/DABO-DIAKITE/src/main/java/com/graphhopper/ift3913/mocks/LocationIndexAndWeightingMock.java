package com.graphhopper.ift3913.mocks;

public class LocationIndexAndWeightingMock {

    private double latitude;
    private double longitude;
    private double weight;

    public LocationIndexAndWeightingMock(double latitude, double longitude, double weight) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.weight = weight;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isHeavierThan(LocationIndexAndWeightingMock other) {
        return this.weight > other.weight;
    }

    public double computeDistanceTo(LocationIndexAndWeightingMock other) {
        double dx = this.latitude - other.latitude;
        double dy = this.longitude - other.longitude;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

