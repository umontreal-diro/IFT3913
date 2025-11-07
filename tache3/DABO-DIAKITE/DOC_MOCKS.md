
##  Objectif
Ajouter des cas de test JUnit 5 + Mockito simulant au moins **deux classes différentes** de GraphHopper.

---

##  Classes concernées

| Type | Nom de classe | Rôle | Statut |
|------|----------------|------|--------|
| Testée | `RouteOrchestrator` (classe interne aux tests) | Orchestration simple d'un calcul de route | Créée pour la démo |
| Mockée | `RoutingAlgorithm` | Simule le calcul de chemin (`calcPath`) | Mockito |
| Mockée | `LocationIndex` | Simule la recherche de coordonnées (`findClosest`) | Mockito |
| Mockée | `Weighting` | Simule le calcul de coût minimal | Mockito |

---

##  Test 1 : `RoutingAlgorithmMockTest`
**But :** vérifier qu’un `RoutingAlgorithm` mocké renvoie un `ResponsePath` avec les bonnes valeurs et que l’appel est correctement vérifié.  
**Interactions :**  
- `when(algo.calcPath(42, 77)).thenReturn(mockedPath)`  
- Vérifie `verify(algo, times(1)).calcPath(42, 77)`.

---

##  Test 2 : `LocationIndexAndWeightingMockTest`
**But :** simuler un calcul d’un score entre deux points, basé sur deux classes mockées.  
**Interactions :**
- `LocationIndex.findClosest(...)` → retourne des `Snap` valides ou non.
- `Weighting.getMinWeight(...)` → retourne un coût arbitraire.
- Vérifie `verifyNoInteractions(weighting)` si `Snap` invalide.

---

##  Résultats attendus
- Les deux tests compilent et s’exécutent sans dépendre du vrai GraphHopper.
- Ils démontrent l’utilisation de Mockito : `mock()`, `when()`, `verify()`, `verifyNoInteractions()`.

---

##  Fichiers liés
| Fichier | Description |
|----------|-------------|
| `src/test/java/.../RoutingAlgorithmMockTest.java` | Test 1 |
| `src/test/java/.../LocationIndexAndWeightingMockTest.java` | Test 2 |
| `pom.xml` | Intégration de JUnit 5 + Mockito |
