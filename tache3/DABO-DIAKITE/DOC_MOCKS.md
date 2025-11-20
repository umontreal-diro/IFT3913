# Documentation des tests et mocks 

Ce document présente les choix de conception, les classes testées, les classes simulées, ainsi que la justification des valeurs retournées par les mocks dans le cadre de la Tâche 3.

---

## 1. Objectif

L’objectif est de démontrer l’utilisation appropriée de **Mockito** pour tester du code dépendant de composants internes de **GraphHopper**, sans nécessiter d’exécuter le moteur complet. Deux classes ont été testées, chacune reposant sur des objets simulés afin d’isoler complètement la logique.

---

## 2. Choix des classes testées

Deux classes simples ont été créées afin de tester des interactions typiques avec GraphHopper :

| Classe testée | Rôle | Justification |
|---------------|------|---------------|
| `RoutingAlgorithmMock` | Orchestration d’un appel à `RoutingAlgorithm.calcPath`. | Permet de vérifier les interactions avec un algorithme de routage sans dépendre du moteur réel. |
| `LocationIndexAndWeightingMock` | Calcule un score basé sur les résultats de `LocationIndex` et `Weighting`. | Permet de tester un comportement impliquant plusieurs dépendances externes. |

Ces classes sont suffisamment légères pour permettre des tests unitaires isolés et reproductibles.

---

## 3. Choix des classes mockées

Les classes suivantes proviennent de GraphHopper. Elles sont trop complexes pour être instanciées directement dans un test unitaire, ce qui justifie leur simulation :

| Classe mockée | Rôle dans GraphHopper | Raison du mock |
|----------------|-----------------------|----------------|
| `RoutingAlgorithm` | Calcul d’un chemin via `calcPath(int, int)`. | Dépend du graphe complet et d’algorithmes internes. |
| `LocationIndex` | Recherche du point le plus proche d’une coordonnée. | Dépend de structures spatiales lourdes. |
| `Weighting` | Calcul du poids minimal pour un segment. | Sa valeur influence le routage ; un mock permet de maîtriser cette valeur. |

Mockito permet ainsi de contrôler précisément les valeurs retournées et de vérifier les interactions.

---

## 4. Test 1 – `RoutingAlgorithmMockTest`

### Objectif  
Vérifier l’appel à `RoutingAlgorithm.calcPath` et s’assurer que le résultat retourné par l’orchestrateur correspond à celui du mock.

### Mocks utilisés  
- `RoutingAlgorithm`
- `ResponsePath`

### Valeurs simulées  
- `calcPath(42, 77)` retourne un `ResponsePath` dont `getTime()` vaut `123.45`.  
Cette valeur arbitraire permet de démontrer que le résultat provient bien du mock.

### Vérifications principales  
- Appel exact : `verify(algo, times(1)).calcPath(42, 77)`  
- Résultat attendu : vérification que la méthode testée retourne le temps simulé.

### Compétence démontrée  
Test d’interaction isolé avec vérification de comportement.

---

## 5. Test 2 – `LocationIndexAndWeightingMockTest`

### Objectif  
Tester une classe dépendant de deux mocks distincts et vérifier le flux normal ainsi que les comportements conditionnels.

### Mocks utilisés  
- `LocationIndex`
- `Weighting`

### Cas 1 : Snap valide  
- `findClosest` retourne un `Snap` valide.  
- `weighting.getMinWeight(...)` retourne `10.0`.  
Ce scénario permet de tester le traitement standard d’un résultat valide.

### Cas 2 : Snap invalide  
- `findClosest` retourne un `Snap` non valide.  
- `verifyNoInteractions(weighting)` doit être vrai.  
Cela vérifie que la méthode coupe l’exécution avant d’utiliser inutilement une dépendance.

### Compétence démontrée  
Usage de deux mocks, contrôle de flux, et vérification d’interactions (ou absence d’interactions).

---

## 6. Conformité aux exigences de la Tâche 3

| Exigence | Statut | Détails |
|----------|--------|---------|
| Au moins deux classes testées | ✔️ | `RoutingAlgorithmMock`, `LocationIndexAndWeightingMock` |
| Au moins deux classes mockées | ✔️ | `RoutingAlgorithm`, `LocationIndex`, `Weighting` |
| Utilisation de Mockito | ✔️ | `mock`, `when`, `verify`, `verifyNoInteractions` |
| Tests isolés du moteur GraphHopper | ✔️ | Aucun composant réel instancié |
| Justification des valeurs simulées | ✔️ | Section 4 et 5 |
| Documentation claire | ✔️ | Ce document |

---

## 7. Conclusion

Les deux tests montrent une utilisation correcte et complète de Mockito dans un contexte inspiré de GraphHopper.  
Ils démontrent :

- l’isolation des dépendances,
- la vérification précise des interactions,
- la gestion de plusieurs mocks simultanés,
- la reproductibilité des résultats.

Ce travail satisfait intégralement les exigences techniques de la Tâche 3.

