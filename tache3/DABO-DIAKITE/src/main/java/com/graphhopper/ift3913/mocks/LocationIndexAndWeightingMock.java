/**
 * ------------------------------------------------------------
 * Classe : LocationIndexAndWeightingMock
 * ------------------------------------------------------------
 * Objectif :
 *  - Simuler un objet combinant un indice de localisation et un calcul de pondération
 *  - Fournir un comportement simple mais testable pour les tests unitaires
 *
 * Constructeur :
 *  - LocationIndexAndWeightingMock(int speedLimit, boolean limitApplied)
 *
 * Méthodes :
 *  - getSpeedLimit() : retourne la vitesse, limitée ou non
 *  - computeWeight(double distance) : calcule une pondération simple en fonction de la distance
 * ------------------------------------------------------------
 */

 package com.graphhopper.ift3913.mocks;

 public class LocationIndexAndWeightingMock {
 
     private int speedLimit;
     private boolean limitApplied;
 
     public LocationIndexAndWeightingMock(int speedLimit, boolean limitApplied) {
         this.speedLimit = speedLimit;
         this.limitApplied = limitApplied;
     }
 
     /**
      * Retourne la vitesse selon que la limitation est active ou non.
      */
     public double getSpeedLimit() {
         return limitApplied ? speedLimit * 0.8 : speedLimit;
     }
 
     /**
      * Calcule une pondération simple selon la distance.
      */
     public double computeWeight(double distance) {
         if (distance <= 0) return 0.0;
         return 1.0 / (1.0 + distance / speedLimit);
     }
 }
 