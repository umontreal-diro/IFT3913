/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.graphhopper.util;

import com.github.javafaker.Faker;
import com.graphhopper.util.shapes.GHPoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistanceCalcEuclideanTest {

    @Test
    public void testCrossingPointToEdge() {
        DistanceCalcEuclidean distanceCalc = new DistanceCalcEuclidean();
        GHPoint point = distanceCalc.calcCrossingPointToEdge(0, 10, 0, 0, 10, 10);
        assertEquals(5, point.getLat(), 0);
        assertEquals(5, point.getLon(), 0);
    }

    @Test
    public void testCalcNormalizedEdgeDistance() {
        DistanceCalcEuclidean distanceCalc = new DistanceCalcEuclidean();
        double distance = distanceCalc.calcNormalizedEdgeDistance(0, 10, 0, 0, 10, 10);
        assertEquals(50, distance, 0);
    }

    @Test
    public void testCalcNormalizedEdgeDistance3dStartEndSame() {
        DistanceCalcEuclidean distanceCalc = new DistanceCalcEuclidean();
        double distance = distanceCalc.calcNormalizedEdgeDistance3D(0, 3, 4, 0, 0, 0, 0, 0, 0);
        assertEquals(25, distance, 0);
    }

    @Test
    public void testValidEdgeDistance() {
        DistanceCalcEuclidean distanceCalc = new DistanceCalcEuclidean();
        boolean validEdgeDistance = distanceCalc.validEdgeDistance(5, 15, 0, 0, 10, 10);
        assertEquals(false, validEdgeDistance);
        validEdgeDistance = distanceCalc.validEdgeDistance(15, 5, 0, 0, 10, 10);
        assertEquals(false, validEdgeDistance);
    }

    @Test
    public void testDistance3dEuclidean() {
        DistanceCalcEuclidean distCalc = new DistanceCalcEuclidean();
        assertEquals(1, distCalc.calcDist3D(
                0, 0, 0,
                0, 0, 1
        ), 1e-6);
        assertEquals(10, distCalc.calcDist3D(
                0, 0, 0,
                0, 0, 10
        ), 1e-6);
    }

     @Test
    public void testIntermediatePointWithFaker() {
        Faker faker = new Faker();
        DistanceCalcEuclidean distanceCalc = new DistanceCalcEuclidean();

        // Génère aléatoirement deux points (latitude, longitude)
        double lat1 = Double.parseDouble(faker.address().latitude().replace(",", "."));
        double lon1 = Double.parseDouble(faker.address().longitude().replace(",", "."));
        double lat2 = Double.parseDouble(faker.address().latitude().replace(",", "."));
        double lon2 = Double.parseDouble(faker.address().longitude().replace(",", "."));

        // Génère un facteur entre 0 et 1 (position intermédiaire entre les deux points)
        double f = faker.number().randomDouble(2, 0, 1);

        GHPoint result = distanceCalc.intermediatePoint(f, lat1, lon1, lat2, lon2);

        // Vérifications : les coordonnées du point intermédiaire doivent être entre les deux extrémités
        assertNotNull(result);
        assertTrue(result.lat >= Math.min(lat1, lat2) && result.lat <= Math.max(lat1, lat2),
                "La latitude intermédiaire doit être comprise entre lat1 et lat2");
        assertTrue(result.lon >= Math.min(lon1, lon2) && result.lon <= Math.max(lon1, lon2),
                "La longitude intermédiaire doit être comprise entre lon1 et lon2");

        // Vérifie que f=0 retourne le premier point, et f=1 le second (sanity check)
        GHPoint start = distanceCalc.intermediatePoint(0, lat1, lon1, lat2, lon2);
        GHPoint end = distanceCalc.intermediatePoint(1, lat1, lon1, lat2, lon2);
        assertEquals(lat1, start.lat, 1e-6);
        assertEquals(lon1, start.lon, 1e-6);
        assertEquals(lat2, end.lat, 1e-6);
        assertEquals(lon2, end.lon, 1e-6);
    }
}
