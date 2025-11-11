/**
 * ------------------------------------------------------------
 * Classe : RoutingAlgorithmMock
 * ------------------------------------------------------------
 * Objectif :
 *  - Simuler un algorithme de routage minimaliste pour tests unitaires
 *  - Représenter la logique de calcul de distance entre deux points
 *  - Conserver une liste des nœuds visités et un état du calcul
 *
 * Méthodes :
 *  - computeRoute(String, String) : retourne une distance simulée
 *  - hasComputedRoute() : indique si un calcul a été effectué
 *  - getVisitedNodes() : retourne la liste des nœuds visités
 * ------------------------------------------------------------
 */

 package com.graphhopper.ift3913.mocks;

 import java.util.ArrayList;
 import java.util.List;
 
 public class RoutingAlgorithmMock {
 
     private boolean computed;
     private List<String> visitedNodes = new ArrayList<>();
 
     public RoutingAlgorithmMock() {
         this.computed = false;
     }
 
     /**
      * Simule le calcul d’un itinéraire entre deux points.
      */
     public double computeRoute(String from, String to) {
         if (from == null || to == null) {
             throw new IllegalArgumentException("Les points de départ et d'arrivée ne peuvent pas être null.");
         }
         visitedNodes.clear();
         visitedNodes.add(from);
         visitedNodes.add(to);
         computed = true;
         return Math.random() * 100; // distance simulée
     }
 
     /**
      * Indique si un itinéraire a été calculé.
      */
     public boolean hasComputedRoute() {
         return computed;
     }
 
     /**
      * Retourne la liste des nœuds visités.
      */
     public List<String> getVisitedNodes() {
         return visitedNodes;
     }
 }
 