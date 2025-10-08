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
package com.graphhopper.routing.util;

import com.github.javafaker.Faker;
import com.graphhopper.routing.ev.*;
import com.graphhopper.routing.util.parsers.BikeAccessParser;
import com.graphhopper.routing.util.parsers.CarAccessParser;
import com.graphhopper.routing.util.parsers.FootAccessParser;
import com.graphhopper.util.PMap;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.graphhopper.jackson.Jackson;
import com.graphhopper.util.Constants;
import java.util.LinkedHashMap;

import com.graphhopper.storage.StorableProperties;
import com.graphhopper.storage.Directory;
import com.graphhopper.storage.RAMDirectory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Peter Karich
 */
public class EncodingManagerTest {

    @Test
    public void testSupportFords() {
        EncodingManager manager = new EncodingManager.Builder()
                .add(VehicleAccess.create("car"))
                .add(VehicleAccess.create("bike"))
                .add(VehicleAccess.create("foot"))
                .add(Roundabout.create())
                .build();

        // 1) default -> no block fords
        assertFalse(new CarAccessParser(manager, new PMap()).isBlockFords());
        assertFalse(new BikeAccessParser(manager, new PMap()).isBlockFords());
        assertFalse(new FootAccessParser(manager, new PMap()).isBlockFords());

        // 2) true
        assertTrue(new CarAccessParser(manager, new PMap("block_fords=true")).isBlockFords());
        assertTrue(new BikeAccessParser(manager, new PMap("block_fords=true")).isBlockFords());
        assertTrue(new FootAccessParser(manager, new PMap("block_fords=true")).isBlockFords());

        // 3) false
        assertFalse(new CarAccessParser(manager, new PMap("block_fords=false")).isBlockFords());
        assertFalse(new BikeAccessParser(manager, new PMap("block_fords=false")).isBlockFords());
        assertFalse(new FootAccessParser(manager, new PMap("block_fords=false")).isBlockFords());
    }

    /**
     * Test that verifies that adding a turn cost encoded value with a duplicate name throws an exception.
     * This ensures that the EncodingManager.Builder correctly handles attempts to add encoded values
     * with names that already exist, maintaining the integrity of the encoding system.
     
    @Test
    void testFromProperties_invalidVersionAndDuplicate() throws Exception {
        // simulate a property file
    StorableProperties props = new StorableProperties();
        props.put("graph.em.version", String.valueOf(Constants.VERSION_EM + 1)); // invalid version
        props.put("graph.em.bytes_for_flags", "4");
        props.put("graph.em.ints_for_turn_cost_flags", "1");

        // create a fake encoded value list with a duplicate name
        EncodedValue ev = new SimpleBooleanEncodedValue("car_access", true);
        String serialized = "[\"" + EncodedValueSerializer.serializeEncodedValue(ev) + "\",\"" + EncodedValueSerializer.serializeEncodedValue(ev) + "\"]";

        props.put("graph.encoded_values", serialized);
        props.put("graph.turn_encoded_values", serialized);

        // 1) expect incompatible version exception
        IllegalStateException ex1 = assertThrows(IllegalStateException.class, () -> EncodingManager.fromProperties(props));
        assertTrue(ex1.getMessage().contains("Incompatible encoding version"));

        // 2) Now fix version and trigger duplicate exception
        props.put("graph.em.version", String.valueOf(Constants.VERSION_EM));
        assertThrows(IllegalStateException.class, () -> EncodingManager.fromProperties(props));
    }*/

    @Test
    void testBuilder_addAndBuild() {
        EncodingManager.Builder builder = EncodingManager.start();

        BooleanEncodedValue access = new SimpleBooleanEncodedValue("car_access", true);
        DecimalEncodedValue speed = new DecimalEncodedValueImpl("car_average_speed", 4, 5, false);

        builder.add(access);
        builder.addTurnCostEncodedValue(speed);

        EncodingManager manager = builder.build();

        assertTrue(manager.hasEncodedValue("car_access"));
        assertTrue(manager.hasTurnEncodedValue("car_average_speed"));

        // Après build(), le builder ne doit pas accepter de nouvelles valeurs
        assertThrows(IllegalStateException.class, () -> builder.add(access));
    }

    @Test
    void testGetVehiclesAndToString() {
        EncodingManager.Builder builder = EncodingManager.start();
        builder.add(new SimpleBooleanEncodedValue("bike_access", true));
        builder.add(new DecimalEncodedValueImpl("bike_average_speed", 4, 15, false));

        EncodingManager manager = builder.build();

        var vehicles = manager.getVehicles();
        assertEquals(1, vehicles.size());
        assertTrue(vehicles.contains("bike"));
        assertEquals("bike", manager.toString());
    }

    @Test
    void testEncodedValueRetrievalAndExceptions() {
        EncodingManager.Builder builder = EncodingManager.start();
        builder.add(new SimpleBooleanEncodedValue("walk_access", true));
        builder.addTurnCostEncodedValue(new DecimalEncodedValueImpl("walk_turn_cost", 2, 5, false));
        EncodingManager manager = builder.build();

        assertNotNull(manager.getBooleanEncodedValue("walk_access"));
        assertNotNull(manager.getTurnDecimalEncodedValue("walk_turn_cost"));
        assertTrue(manager.needsTurnCostsSupport());
        assertThrows(IllegalArgumentException.class, () -> manager.getEncodedValue("unknown_key", BooleanEncodedValue.class));
        assertThrows(IllegalArgumentException.class, () -> manager.getTurnEncodedValue("bad_key", DecimalEncodedValue.class));
    }



    @Test
    void testFromPropertiesAndEncodingsAdvanced() throws JsonProcessingException {
        Faker faker = new Faker();

        //  Cas principal : initialisation correcte 
        Directory dir = new RAMDirectory(); // stockage en mémoire
        StorableProperties props = new StorableProperties(dir);
        props.put("graph.em.version", String.valueOf(Constants.VERSION_EM));
        props.put("graph.em.bytes_for_flags", "4");
        props.put("graph.em.ints_for_turn_cost_flags", "1");

        ObjectMapper mapper = new ObjectMapper();

        // Génération dynamique des noms et valeurs
        String accessName = faker.ancient().god().toLowerCase() + "_access";
        String surfaceName = faker.address().streetName().replaceAll("\\s+", "_").toLowerCase();
        String turnCostName = faker.harryPotter().spell().replaceAll("\\s+", "_").toLowerCase();

        EncodedValue ev1 = new SimpleBooleanEncodedValue(accessName, true);
        EncodedValue ev2 = new StringEncodedValue(surfaceName, 5);
        EncodedValue tev = new DecimalEncodedValueImpl(turnCostName, 4, 2, false);

        // Sérialisation des encodages
        String serializedEVs = mapper.writeValueAsString(List.of(
            EncodedValueSerializer.serializeEncodedValue(ev1),
            EncodedValueSerializer.serializeEncodedValue(ev2)
        ));

        String serializedTurnEVs = mapper.writeValueAsString(List.of(
            EncodedValueSerializer.serializeEncodedValue(tev)
        ));
        props.put("graph.encoded_values", serializedEVs);
        props.put("graph.turn_encoded_values", serializedTurnEVs);

        //  Test principal : fromProperties valide 
        EncodingManager manager = EncodingManager.fromProperties(props);
        assertNotNull(manager, "EncodingManager ne doit pas être null après fromProperties");
        assertEquals(1, manager.getTurnEncodedValues().size());

        // Vérifie que les encodages de tournants sont bien enregistrés
        List<EncodedValue> turnValues = manager.getTurnEncodedValues();
        assertEquals(1, turnValues.size(), "Un seul encodage de tournant attendu");
        assertTrue(turnValues.get(0) instanceof DecimalEncodedValue, "L'encodage doit être de type DecimalEncodedValue");

        // Vérifie que l'encodage surface est bien accessible
        StringEncodedValue strEv = manager.getStringEncodedValue(surfaceName);
        assertNotNull(strEv, "L'encodage surface doit être présent");
        assertEquals(surfaceName, strEv.getName(), "Le nom de l'encodage doit correspondre");

        //  Branche d'erreur : version manquante 
        Directory dir2 = new RAMDirectory();
        StorableProperties missingVersion = new StorableProperties(dir2);
        missingVersion.put("graph.em.version", "");
        missingVersion.put("graph.em.bytes_for_flags", "4");
        missingVersion.put("graph.em.ints_for_turn_cost_flags", "1");
        missingVersion.put("graph.encoded_values", serializedEVs);
        missingVersion.put("graph.turn_encoded_values", serializedTurnEVs);
        assertThrows(IllegalStateException.class, () -> EncodingManager.fromProperties(missingVersion),
                "Une version vide doit déclencher une IllegalStateException");

        //  Branche d'erreur : clé manquante 
        Directory dir3 = new RAMDirectory();
        StorableProperties missingKey = new StorableProperties(dir3);
        missingKey.put("graph.em.version", String.valueOf(Constants.VERSION_EM));
        missingKey.put("graph.em.bytes_for_flags", "");
        assertThrows(IllegalStateException.class,
                () -> invokeGetIntegerProperty(missingKey, "graph.em.bytes_for_flags"),
                "Une clé vide doit déclencher une IllegalStateException");

        //  Branche d'erreur : JSON invalide 
        assertThrows(UncheckedIOException.class,
                () -> invokeDeserializeEncodedValueList("not a valid json"),
                "Un JSON invalide doit déclencher une UncheckedIOException");

        //  Branche d'erreur : doublon d'encodage 
        ObjectMapper mapper2 = new ObjectMapper();
        String duplicateName = faker.pokemon().name().replaceAll("\\s+", "_").toLowerCase() + "_access";
        EncodedValue duplicate = new SimpleBooleanEncodedValue(duplicateName, true);
        String dupSerialized = mapper2.writeValueAsString(List.of(
            EncodedValueSerializer.serializeEncodedValue(duplicate),
            EncodedValueSerializer.serializeEncodedValue(duplicate)
        ));

        Directory dir4 = new RAMDirectory();
        StorableProperties propsDup = new StorableProperties(dir4);
        propsDup.put("graph.em.version", String.valueOf(Constants.VERSION_EM));
        propsDup.put("graph.em.bytes_for_flags", "4");
        propsDup.put("graph.em.ints_for_turn_cost_flags", "1");
        propsDup.put("graph.encoded_values", dupSerialized);
        propsDup.put("graph.turn_encoded_values", "[]");

        Exception ex = assertThrows(IllegalStateException.class,
                () -> EncodingManager.fromProperties(propsDup),
                "Un doublon d'encodage doit déclencher une IllegalStateException");
        assertTrue(ex.getMessage().contains(duplicateName), "Le message d'erreur doit mentionner le nom de l'encodage dupliqué");

        //  Nettoyage des ressources 
        props.close();
        missingVersion.close();
        missingKey.close();
        propsDup.close();
    }



    // Permet d'appeler la méthode privée getIntegerProperty
    private static int invokeGetIntegerProperty(StorableProperties props, String key) {
        try {
            var method = EncodingManager.class.getDeclaredMethod("getIntegerProperty", StorableProperties.class, String.class);
            method.setAccessible(true);
            return (int) method.invoke(null, props, key);
        } catch (Exception e) {
            if (e.getCause() instanceof IllegalStateException) throw (IllegalStateException) e.getCause();
            throw new RuntimeException(e);
        }
    }

    // Permet d'appeler la méthode privée deserializeEncodedValueList
    private static Object invokeDeserializeEncodedValueList(String json) {
        try {
            var method = EncodingManager.class.getDeclaredMethod("deserializeEncodedValueList", String.class);
            method.setAccessible(true);
            return method.invoke(null, json);
        } catch (Exception e) {
            if (e.getCause() instanceof UncheckedIOException) throw (UncheckedIOException) e.getCause();
            throw new RuntimeException(e);
        }
    }


}
