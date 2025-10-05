package com.graphhopper;

import com.graphhopper.config.Profile;
import com.graphhopper.util.CustomModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests ciblés pour tuer des mutants spécifiques dans Profile
 * 
 * Ces tests sont conçus pour détecter 2 mutants survivants critiques :
 * 1. Mutant setName() - removed call to validateProfileName  
 * 2. Mutant setCustomModel() - replaced return value with null
 */
public class MutantKillerProfileTest {

    /**
     * TEST 1 : KILLER DU MUTANT "removed call to validateProfileName"
     * 
     * Intention : Vérifier que validateProfileName() est bien appelée dans setName()
     * Données : Un nom invalide qui doit déclencher une exception
     * Oracle : Une exception doit être levée si validateProfileName() est appelée
     * 
     * Ce test ÉCHOUE si le mutant "removed call to validateProfileName" survit,
     * car sans l'appel à validateProfileName(), aucune exception n'est levée.
     */
    @Test
    public void test_setName_mutant_killer_validateProfileName() {
        // Arrange
        Profile profile = new Profile("valid");
        
        // Act & Assert - Ce test TUE le mutant qui supprime validateProfileName()
        assertThrows(IllegalArgumentException.class, () -> {
            profile.setName("INVALID_NAME_WITH_UPPERCASE"); // validateProfileName doit rejeter ceci
        }, "setName() doit appeler validateProfileName() et rejeter les noms invalides");
        
        // Vérification supplémentaire : le nom ne doit pas changer si invalide
        assertEquals("valid", profile.getName(), "Le nom ne doit pas changer si validation échoue");
    }

    /**
     * TEST 2 : KILLER DU MUTANT "replaced return value with null" dans setCustomModel()
     * 
     * Intention : Vérifier que setCustomModel() retourne bien 'this' pour le chaînage
     * Données : Un CustomModel valide 
     * Oracle : setCustomModel() doit retourner l'instance Profile (pas null)
     * 
     * Ce test ÉCHOUE si le mutant "replaced return value with null" survit,
     * car il tenterait de chaîner sur null et lèverait une NullPointerException.
     */
    @Test 
    public void test_setCustomModel_mutant_killer_returnValue() {
        // Arrange
        Profile profile = new Profile("test");
        CustomModel customModel = new CustomModel();
        
        // Act - Ce test TUE le mutant qui retourne null au lieu de 'this'
        Profile result = profile.setCustomModel(customModel);
        
        // Assert - Vérification du chaînage (échoue si retour null)
        assertNotNull(result, "setCustomModel() ne doit jamais retourner null");
        assertSame(profile, result, "setCustomModel() doit retourner 'this' pour permettre le chaînage");
        
        // Test de chaînage complet - échoue immédiatement si mutant survit
        assertDoesNotThrow(() -> {
            Profile chained = new Profile("chain")
                .setCustomModel(customModel)  // Si retourne null, la ligne suivante crash
                .setName("newname");           // NullPointerException ici si mutant survit
            
            assertEquals("newname", chained.getName());
        }, "Le chaînage des méthodes doit fonctionner sans NullPointerException");
    }
}