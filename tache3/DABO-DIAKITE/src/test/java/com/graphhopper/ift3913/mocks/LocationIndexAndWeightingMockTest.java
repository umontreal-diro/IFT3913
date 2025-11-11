/**
 * ------------------------------------------------------------
 * Classe de test : LocationIndexAndWeightingMockTest
 * ------------------------------------------------------------
 * Objectif :
 *  - Tester les méthodes principales de la classe LocationIndexAndWeightingMock
 *  - Vérifier que la logique de limitation de vitesse et de pondération fonctionne
 *  - Permettre à PIT de mesurer une couverture réelle sur les branches du code
 *
 * Couverture visée :
 *  - getSpeedLimit() : avec et sans limitation active
 *  - computeWeight() : pour vérifier la valeur de pondération calculée
 *
 * Auteur : Adaptation automatisée pour IFT3913 (Tâche 3)
 * ------------------------------------------------------------
 */

 package com.graphhopper.ift3913.mocks;

 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;
 
 public class LocationIndexAndWeightingMockTest {
 
     @Test
     void testSpeedLimitApplied() {
         LocationIndexAndWeightingMock mock = new LocationIndexAndWeightingMock(100, true);
         // Si la limitation est activée, la vitesse doit être réduite à 80
         assertEquals(80, mock.getSpeedLimit(), 0.1);
     }
 
     @Test
     void testSpeedLimitNotApplied() {
         LocationIndexAndWeightingMock mock = new LocationIndexAndWeightingMock(100, false);
         // Si la limitation est désactivée, la vitesse reste inchangée
         assertEquals(100, mock.getSpeedLimit(), 0.1);
     }
 
     @Test
     void testComputeWeight() {
         LocationIndexAndWeightingMock mock = new LocationIndexAndWeightingMock(50, false);
         double weight = mock.computeWeight(10);
         // La pondération doit être un ratio entre 0 et 1
         assertTrue(weight > 0 && weight < 1);
     }
 }
 