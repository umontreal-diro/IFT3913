package com.graphhopper;
import com.graphhopper.config.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigProfileLMProfileTest {
    // Profile.putHints 

    /**
     * Intention :
     *   Vérifier que putHint() enregistre bien la paire (clé, valeur) dans la map des hints.
     * Données :
     *   - Profile p = new Profile("p1")
     *   - Appel : p.putHint("foo", "bar")  
     * Oracle :
     *   - Aucune exception n'est levée (test du comportement normal)
     */
    @Test
    public void profile_putHint_storeKeyValue() {
        Profile p = new Profile("p1");

        // Test que putHint() fonctionne sans lever d'exception
        assertDoesNotThrow(() -> p.putHint("foo", "bar"));
        
        // Test le chaînage - putHint() doit retourner le même Profile
        Profile result = p.putHint("another", "value");
        assertSame(p, result, "putHint() should return the same Profile instance for method chaining");
    }

    /**
     * Intention :
     *   S'assurer que la clé réservée "u_turn_costs" est refusée et qu'une IllegalArgumentException est levée.
     * Données :
     *   - Profile p = new Profile("p1")
     *   - Appel : p.putHint("u_turn_costs", 5)
     * Oracle :
     *   - L'appel lève IllegalArgumentException
     */
    @Test
    public void profile_putHint_rejects() {
        Profile p = new Profile("p1");

        assertThrows(IllegalArgumentException.class, () -> p.putHint("u_turn_costs", "car"));
    }

    //Intention :
    //  S'assurer que la clé réservée "vehicle" est refusée et qu'une IllegalArgumentException est levée.
    //Données :
    //  - Profile p = new Profile("p1")
    //  - Appel : p.putHint("vehicle", "car")
    //Oracle :
    //  - L'appel lève IllegalArgumentException
     
    @Test
    public void profile_putHint_rejectsVehicle() {
        Profile p = new Profile("p1");

        assertThrows(IllegalArgumentException.class, () -> p.putHint("vehicle", "car"));
    }

    /**
    * Intention : vérifier que validateProfileName rejette les noms invalides
    * Données   : noms valides (minuscules, chiffres, tirets) et invalides (majuscules, espaces, caractères spéciaux)
    * Oracle    : IllegalArgumentException pour noms non conformes, aucune exception pour noms valides
    */
    @Test
    public void profile_validateProfileName_enforcesFormat() {
        // Test noms valides - aucune exception
        assertDoesNotThrow(() -> Profile.validateProfileName("valid_name"));
        assertDoesNotThrow(() -> Profile.validateProfileName("test123"));
        assertDoesNotThrow(() -> Profile.validateProfileName("my-profile"));
        assertDoesNotThrow(() -> Profile.validateProfileName("a"));
    
        // Test noms invalides - IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("Invalid_Name")); // majuscule
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("invalid name")); // espace
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("invalid@name")); // caractère spécial
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("invalid.name")); // point
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("test/name")); // slash
        assertThrows(IllegalArgumentException.class, () -> Profile.validateProfileName("")); // nom vide
    }

}
