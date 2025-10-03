# Binôme

- Wayne Timmons  
- Ayoub Bencheikh  

# Tâche 2 — Tests de SpeedWeighting

## Classe testée
- `com.graphhopper.routing.weighting.SpeedWeighting`

## Nouveaux cas de test ajoutés (7)

### 1. testCalcEdgeWeightNormal
- **Intention :** Vérifier que `calcEdgeWeight()` retourne bien `distance / speed` quand la vitesse est > 0.  
- **Données de test :** distance = 1000.0, vitesse = 50.0.  
- **Oracle attendu :** Résultat = 20.0 (1000 / 50).  

### 2. testCalcEdgeWeightZeroSpeed
- **Intention :** Vérifier que `calcEdgeWeight()` retourne `Double.POSITIVE_INFINITY` si la vitesse est 0.  
- **Données de test :** vitesse = 0.0.  
- **Oracle attendu :** Résultat = `Double.POSITIVE_INFINITY`.  

### 3. testCalcEdgeWeightReverse
- **Intention :** Vérifier que `calcEdgeWeight()` utilise `getReverse(speedEnc)` quand `reverse = true`.  
- **Données de test :** distance = 500.0, vitesse inverse = 25.0.  
- **Oracle attendu :** Résultat = 20.0 (500 / 25).  

### 4. testCalcEdgeMillis
- **Intention :** Vérifier que `calcEdgeMillis()` retourne le poids en millisecondes (`calcEdgeWeight * 1000`).  
- **Données de test :** distance = 100.0, vitesse = 10.0.  
- **Oracle attendu :** Résultat = 10000 ms ((100 / 10) * 1000).  

### 5. testCalcMinWeightPerDistance
- **Intention :** Vérifier que `calcMinWeightPerDistance()` retourne `1 / maxSpeed`.  
- **Données de test :** maxSpeed = 120.0.  
- **Oracle attendu :** Résultat = 1/120.  

### 6. testGetName
- **Intention :** Vérifier que `getName()` retourne la chaîne `"speed"`.  
- **Données de test :** aucune donnée spécifique.  
- **Oracle attendu :** `"speed"`.  

### 7. testHasTurnCosts
- **Intention :** Vérifier que `hasTurnCosts()` retourne `true` si un `TurnCostProvider` est configuré.  
- **Données de test :** `TurnCostStorage` non nul, `uTurnCosts = 5.0`.  
- **Oracle attendu :** `true`.  

-----


## Étape 1 – Tests originaux  
- **Mutation coverage : 0% (0/51 mutants tués)**  
- Aucun test existant ne couvrait la classe sélectionnée.  

## Étape 2 – Nouveaux tests ajoutés  
- Nous avons ajouté **7 nouveaux tests unitaires** dans `SpeedWeightingTest`.  
- Ces tests visent à couvrir :  
  - le calcul du poids en fonction de la vitesse,  
  - la gestion des vitesses nulles ou invalides,  
  - les conditions limites (ex. vitesse très élevée ou très basse),  
  - la cohérence du retour attendu par rapport à l’oracle (valeur théorique calculée manuellement).  

## Étape 3 – Score de mutation avec les nouveaux tests  
- **Mutation coverage : ~65% (33/51 mutants tués)**  
- **Line coverage : ~86% (19/22 lignes couvertes)**  
- Les nouveaux tests ont permis de tuer une majorité des mutants, notamment ceux liés à :  
  - la négation de conditions (`NegateConditionalsMutator`),  
  - les changements de bornes dans les comparaisons (`ConditionalsBoundaryMutator`),  
  - les mutations sur les opérations mathématiques (`MathMutator`),  
  - les constantes modifiées (`InlineConstantMutator`).  

## Étape 4 – Mutants survivants  
- Certains mutants ont survécu, principalement :  
  - **`MemberVariableMutator`** : changements de valeurs internes non testées directement.  
  - **`ConstructorCallMutator`** : peu ou pas de vérification sur les appels de constructeurs.  
  - **`BooleanTrueReturnValsMutator`** : cas limites où le résultat booléen n’est pas validé par nos assertions.  

Ces survivants indiquent que des cas spécifiques ne sont pas encore couverts par nos tests.  

## Étape 5 – Conclusion  
- Avec les **tests originaux** : score de mutation **0%**.  
- Avec les **nouveaux tests** : score de mutation **65%**.  
- Les nouveaux tests apportent donc une **forte amélioration de la robustesse** face aux mutations.  

## Étape 6 – Intégration de JavaFaker

Nous avons ajouté la librairie [java-faker](https://github.com/DiUS/java-faker) au projet via Maven :

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
    <scope>test</scope>
</dependency>

