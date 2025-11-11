package com.graphhopper.ift3913.mocks;

public class RoutingAlgorithmMock {

    private int distance;

    public RoutingAlgorithmMock(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isShortRoute() {
        return distance < 10;
    }

    public int calculateEstimatedTime() {
        return distance * 2; // Simple estimation: 2 minutes par unité
    }
}

