package com.graphhopper;
import com.graphhopper.config.LMProfile;
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

    // LMProfile.setPreparationProfile 

    // Intention : valider qu’on peut définir un preparation_profile valide (≠ "this")
    // Données   : LMProfile("bike"), setPreparationProfile("prep1")
    // Oracle    : getPreparationProfile()=="prep1" et usesOtherPreparation()==true
    @Test
    void prepProfile_ok_setsFlag() {
        LMProfile lm = new LMProfile("bike");

        lm.setPreparationProfile("prep1");

        assertEquals("prep1", lm.getPreparationProfile());
        assertTrue(lm.usesOtherPreparation());

    }

    // Intention : vérifier que setPreparationProfile échoue si maximum_lm_weight est déjà défini
    // Données   : LMProfile("car"), setMaximumLMWeight(12.3), puis setPreparationProfile("naromba")
    // Oracle    : IllegalArgumentException
    @Test
    void prepProfile_conflictWithWeight() {
        LMProfile lm = new LMProfile("car");
        lm.setMaximumLMWeight(1.1);

        assertThrows(IllegalArgumentException.class, () -> lm.setPreparationProfile("valid_name"));
    }

    // Intention : couvrir la validation qui rejette un nom invalide
    // Données   : LMProfile("bike"), setPreparationProfile("") (nom vide)
    // Oracle    : IllegalArgumentException
    @Test
    void prepProfile_invalidName() {
        LMProfile lm = new LMProfile("bike");

        assertThrows(IllegalArgumentException.class, () -> lm.setPreparationProfile(""));
    }

    // Intention : vérifier le cas spécial "this" => pas d’autre préparation
    // Données   : LMProfile("bike"), setPreparationProfile("this")
    // Oracle    : getPreparationProfile()=="this" et usesOtherPreparation()==false
    @Test
    void prepProfile_thisCase() {
        LMProfile lm = new LMProfile("bike");

        lm.setPreparationProfile("this");

        assertEquals("this", lm.getPreparationProfile());
        assertFalse(lm.usesOtherPreparation());
    }

    // Intention : valider le chaînage (return this)
    // Données   : LMProfile("bike"), out = setPreparationProfile("prep1")
    // Oracle    : out et lm référencent la même instance
    @Test
    void prepProfile_returnsSameInstance() {
        LMProfile lm = new LMProfile("bike");

        LMProfile out = lm.setPreparationProfile("prep1");

        assertSame(lm, out);
    }

    // Intention : vérifier la symétrie des garde-fous avec setMaximumLMWeight
    // Données   : LMProfile("car"), setPreparationProfile("prep_a"), puis setMaximumLMWeight(3.14)
    // Oracle    : IllegalArgumentException
    @Test
    void weight_conflictWithPrepProfile() {
        LMProfile lm = new LMProfile("car");
        lm.setPreparationProfile("prep_a");

        assertThrows(IllegalArgumentException.class, () -> lm.setMaximumLMWeight(1.0));
    }

    // Intention : vérifier que la validation échoue si le nom contient uniquement des espaces
    // Données   : profileName="bike", prepName="   "
    // Oracle    : IllegalArgumentException
    @Test
    void prepProfile_invalidName_spaces() {
        LMProfile lm = new LMProfile("bike");

        assertThrows(IllegalArgumentException.class, () -> lm.setPreparationProfile("   "));
    }

}
