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

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.javafaker.Faker;

/**
 * @author Peter Karich
 */
public class TranslationMapTest {
    // use a static singleton to parse the I18N files only once per test execution
    public final static TranslationMap SINGLETON = new TranslationMap().doImport();

    @Test
    public void testToString() {
        Translation enMap = SINGLETON.getWithFallBack(Locale.UK);
        assertEquals("continue onto blp street", enMap.tr("continue_onto", "blp street"));

        Translation trMap = SINGLETON.getWithFallBack(Locale.GERMANY);
        assertEquals("Zu Fuß", trMap.tr("web.FOOT"));

        Translation ruMap = SINGLETON.getWithFallBack(new Locale("ru"));
        assertEquals("Пешком", ruMap.tr("web.FOOT"));

        Translation zhMap = SINGLETON.getWithFallBack(new Locale("vi", "VI"));
        assertEquals("Đi bộ", zhMap.tr("web.FOOT"));

        trMap = SINGLETON.get("de_DE");
        assertEquals("Zu Fuß", trMap.tr("web.FOOT"));

        trMap = SINGLETON.get("de");
        assertEquals("Zu Fuß", trMap.tr("web.FOOT"));

        trMap = SINGLETON.get("de_AT");
        assertEquals("Zu Fuß", trMap.tr("web.FOOT"));

        trMap = SINGLETON.get("he");
        assertEquals("רגל", trMap.tr("web.FOOT"));
        trMap = SINGLETON.get("iw");
        assertEquals("רגל", trMap.tr("web.FOOT"));

        // Indonesian
        // for jdk17 and later "id" is returned, before "in" was returned
        String lang = SINGLETON.get("id").getLanguage();
        assertTrue(Arrays.asList("id", "in").contains(lang));
        assertEquals(lang, SINGLETON.get("in").getLanguage());
        assertEquals(lang, SINGLETON.get("in_ID").getLanguage());

        // Vietnamese
        assertEquals("vi", SINGLETON.get("vi").getLanguage());
        assertEquals("vi", SINGLETON.get("vi_VN").getLanguage());
    }

    @Test
    public void testToRoundaboutString() {
        Translation ptMap = SINGLETON.get("pt");
        assertTrue(ptMap.tr("roundabout_exit_onto", "1", "somestreet").contains("somestreet"));
    }

    // ========== NOUVEAUX TESTS ==========

    @Test
    public void testPutAddsNewTranslationSuccessfully() {
        // Arrange
        TranslationMap.TranslationHashMap map = new TranslationMap.TranslationHashMap(Locale.FRENCH);
        String key = "turn.left";
        String value = "Tournez à gauche";

        // Act
        map.put(key, value);

        // Assert
        assertEquals(value, map.tr(key));
        assertTrue(map.asMap().containsKey(key.toLowerCase()));
        assertEquals(1, map.asMap().size());
    }

    @Test
    public void testPutThrowsExceptionWhenOverwriting() {
        // Arrange
        TranslationMap.TranslationHashMap map = new TranslationMap.TranslationHashMap(Locale.FRENCH);
        String key = "distance.km";
        String firstValue = "kilomètres";
        String secondValue = "km";

        map.put(key, firstValue);

        // Act & Assert - JUnit 5 syntaxe
        assertThrows(IllegalStateException.class, () -> {
            map.put(key, secondValue);
        });
    }

    @Test
    public void testTrReturnsKeyWhenTranslationNotFound() {
        // Arrange
        TranslationMap.TranslationHashMap map = new TranslationMap.TranslationHashMap(Locale.FRENCH);
        String nonExistentKey = "nonexistent.key";

        // Act
        String result = map.tr(nonExistentKey);

        // Assert
        assertEquals(nonExistentKey, result);
    }

    /**
     * Test 4 : Tester avec des données aléatoires générées par Java-Faker
     */
    @Test
    public void testPutAndRetrieveWithRandomData() {
        // Arrange - Utiliser Faker pour générer des données aléatoires
        Faker faker = new Faker();
        TranslationMap.TranslationHashMap map = new TranslationMap.TranslationHashMap(Locale.ENGLISH);

        // Générer 5 traductions aléatoires
        String key1 = faker.lorem().word();
        String value1 = faker.address().city();

        String key2 = faker.lorem().word();
        String value2 = faker.name().fullName();

        String key3 = faker.lorem().word();
        String value3 = faker.company().name();

        String key4 = faker.lorem().word();
        String value4 = faker.food().dish();

        String key5 = faker.lorem().word();
        String value5 = faker.book().title();

        // Act - Ajouter toutes les traductions
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);

        // Assert - Vérifier que toutes sont récupérables
        assertEquals(value1, map.tr(key1));
        assertEquals(value2, map.tr(key2));
        assertEquals(value3, map.tr(key3));
        assertEquals(value4, map.tr(key4));
        assertEquals(value5, map.tr(key5));
        assertEquals(5, map.asMap().size());
    }
}
