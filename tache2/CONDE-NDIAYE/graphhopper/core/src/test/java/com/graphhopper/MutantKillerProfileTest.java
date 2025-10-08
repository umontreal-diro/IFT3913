package com.graphhopper;

import com.graphhopper.config.Profile;
import com.graphhopper.util.CustomModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour tuer des mutants spécifiques dans Profile
 */
public class MutantKillerProfileTest {

    /**
     * Test pour tuer le mutant "removed call to validateProfileName"
     * Intention : Vérifier que validateProfileName() est appelée
     * Données : Nom invalide "INVALID_NAME_WITH_UPPERCASE"
     * Oracle : IllegalArgumentException levée
     */
    @Test
    public void testSetNameValidation() {
        Profile profile = new Profile("valid");
        
        assertThrows(IllegalArgumentException.class, () -> {
            profile.setName("INVALID_NAME_WITH_UPPERCASE");
        });
        
        assertEquals("valid", profile.getName());
    }

    /**
     * Test pour tuer le mutant "replaced return value with null"
     * Intention : Vérifier que setCustomModel() retourne 'this'
     * Données : CustomModel valide
     * Oracle : Retourne l'instance Profile pour chaînage
     */
    @Test 
    public void testSetCustomModelChaining() {
        Profile profile = new Profile("test");
        CustomModel customModel = new CustomModel();
        
        Profile result = profile.setCustomModel(customModel);
        
        assertNotNull(result);
        assertSame(profile, result);
        
        // Test de chaînage
        assertDoesNotThrow(() -> {
            new Profile("chain")
                .setCustomModel(customModel)
                .setName("newname");
        });
    }
}