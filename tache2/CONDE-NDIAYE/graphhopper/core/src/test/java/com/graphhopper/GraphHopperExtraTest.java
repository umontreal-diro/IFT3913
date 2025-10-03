// Fichier : core/src/test/java/com/graphhopper/GraphHopperReadCustomAreasSimpleTest.java
package com.graphhopper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Intention : vérifier que readCustomAreas() lit un fichier .geojson simple.
 * Données   : dossier temporaire avec 1 fichier area.geojson contenant 1 Feature (Polygon).
 * Oracle    : la liste retournée contient exactement 1 CustomArea.
 */
class GraphHopperExtraTest {

    @Test
    void readsSingleGeoJsonFeature(@TempDir Path dir) throws Exception {
        // Crée un petit FeatureCollection avec un seul polygon
        Path f = dir.resolve("area.geojson");
        Files.writeString(f, """
        {
          "type": "FeatureCollection",
          "features": [{
            "type": "Feature",
            "id": "area1",
            "properties": { "name": "area1" },
            "geometry": {
              "type": "Polygon",
              "coordinates": [[[7.0,43.0],[7.0,43.1],[7.1,43.1],[7.1,43.0],[7.0,43.0]]]
            }
          }]
        }
        """);

        // Instancie GraphHopper et configure le dossier des zones custom
        GraphHopper gh = new GraphHopper();
        Field dirField = GraphHopper.class.getDeclaredField("customAreasDirectory");
        dirField.setAccessible(true);
        dirField.set(gh, dir.toString());

        // Appelle la méthode privée readCustomAreas() via réflexion (sans helper)
        Method m = GraphHopper.class.getDeclaredMethod("readCustomAreas");
        m.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<?> areas = (List<?>) m.invoke(gh);

        // Oracle : 1 zone lue
        assertEquals(1, areas.size());
    }
}
