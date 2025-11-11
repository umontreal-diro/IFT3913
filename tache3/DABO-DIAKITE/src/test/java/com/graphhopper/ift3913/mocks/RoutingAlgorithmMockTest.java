/**
 * ------------------------------------------------------------
 * Classe de test : RoutingAlgorithmMockTest
 * ------------------------------------------------------------
 * Objectif :
 *  - Tester la logique interne simulée d'un algorithme de routage
 *  - Vérifier que la méthode computeRoute() renvoie une distance cohérente
 *    et met à jour les nœuds visités
 *  - Tester le comportement en cas d’entrée invalide (null)
 *
 * Couverture visée :
 *  - computeRoute(String, String)
 *  - hasComputedRoute()
 *  - getVisitedNodes()
 *
 * Auteur : Adaptation automatisée pour IFT3913 (Tâche 3)
 * ------------------------------------------------------------
 */

 package com.graphhopper.ift3913.mocks;

 import org.junit.jupiter.api.Test;
 import static org.junit.jupiter.api.Assertions.*;
 
 public class RoutingAlgorithmMockTest {
 
     @Test
     void testComputeRouteAndVisitedNodes() {
         RoutingAlgorithmMock routing = new RoutingAlgorithmMock();
 
         // Exécution normale du calcul de route
         double distance = routing.computeRoute("A", "B");
 
         // Vérifie que la distance est valide et que des nœuds ont été visités
         assertTrue(distance >= 0);
         assertTrue(routing.hasComputedRoute());
         assertEquals(2, routing.getVisitedNodes().size());
     }
 
     @Test
     void testComputeRouteThrowsOnNull() {
         RoutingAlgorithmMock routing = new RoutingAlgorithmMock();
 
         // Un appel avec une source null doit lever une exception
         assertThrows(IllegalArgumentException.class, () -> routing.computeRoute(null, "B"));
     }
 }
 