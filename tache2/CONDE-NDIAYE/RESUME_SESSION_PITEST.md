# Résumé de la session de travail - Tests de mutation avec PiTest

## 🎯 **Objectif du travail**
Mise en place et exécution de tests de mutation avec PiTest sur le projet GraphHopper pour comparer l'efficacité des tests par rapport à la couverture de code traditionnelle (JaCoCo).

## 📋 **Classes sélectionnées pour les tests**

### Choix stratégique des classes cibles :
- **`Profile`** (com.graphhopper.config) : Classe de configuration centrale avec méthodes `putHint()` 
- **`LMProfile`** (com.graphhopper.config) : Spécialisation des profils Landmark
- **`CHStorage`** (com.graphhopper.storage) : Gestion du stockage des hiérarchies contractées
- **`GraphHopper`** (com.graphhopper) : Classe principale du moteur de routage

### Répartition : 3 packages, 4 classes
- **Package `com.graphhopper.config`** : 2 classes (Profile + LMProfile)
- **Package `com.graphhopper.storage`** : 1 classe (CHStorage)  
- **Package `com.graphhopper`** : 1 classe (GraphHopper)

Cette répartition permet de tester différents niveaux d'architecture : configuration, stockage, et moteur principal.

## 🛠 **Étapes de réalisation et résolution des problèmes**

### 1. Configuration initiale de PiTest
**Problème rencontré :** Incompatibilité Java 24 avec PiTest 1.17.2
```
Error: PiTest ne supporte pas Java 24
```
**Solution :** Configuration de JAVA_HOME vers Java 17
```bash
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home
```

### 2. Configuration Maven
**Problème :** Profile PiTest manquant dans le pom.xml
**Solution :** Ajout du profil complet avec :
- Plugin PiTest 1.17.2
- Support JUnit 5
- Configuration des classes cibles et des tests

### 3. Problèmes d'exécution des tests
**Problème 1 :** Corruption XML du pom.xml lors de l'édition
**Solution :** Recréation complète du fichier avec structure XML valide

**Problème 2 :** Nom de classe incorrect dans GraphHopperExtraTest.java
```java
// Erreur : nom de fichier ≠ nom de classe
class GraphHopperReadCustomAreasSimpleTest // dans GraphHopperExtraTest.java
```
**Solution :** Correction du nom de classe pour correspondre au fichier

**Problème 3 :** Erreur argLine avec JaCoCo
```
Error: could not open `{argLine}'
```
**Cause :** Conflit entre JaCoCo et PiTest sur la variable argLine
**Solution :** Exécution avec tests spécifiques uniquement

### 4. Exécution finale réussie
**Commande finale :**
```bash
mvn clean test org.pitest:pitest-maven:mutationCoverage -Ppitest -Dtest="ConfigProfileLMProfileTest,CHStorageTest,GraphHopperExtraTest"
```

## 📊 **Résultats obtenus**

### Tests créés :
- **ConfigProfileLMProfileTest** : 10 tests (Profile + LMProfile)
- **CHStorageTest** : 6 tests (CHStorage)
- **GraphHopperExtraTest** : 1 test (GraphHopper)
- **Total** : 17 tests

### Comparaison JaCoCo vs PiTest :

**JaCoCo (couverture traditionnelle) :**
- LMProfile : 100% de couverture
- Profile : ~90% de couverture
- CHStorage : Bonne couverture
- GraphHopper : Couverture partielle

**PiTest (mutation testing) :**
- **659 mutations générées**
- **78 mutations tuées (12%)**
- **Couverture de ligne : 19% (222/1185)**
- **Force des tests : 52%**

### Détail par package :
| Package | Couverture ligne | Couverture mutation | Force des tests |
|---------|------------------|-------------------|-----------------|
| com.graphhopper | 6% (54/912) | 0% (2/446) | 100% (2/2) |
| com.graphhopper.config | 53% (43/81) | 28% (12/43) | 60% (12/20) |
| com.graphhopper.storage | 65% (125/192) | 38% (64/170) | 50% (64/129) |

## 🔍 **Analyse des résultats**

### Points clés pour le rapport académique :
1. **Démonstration claire** que 100% de couverture JaCoCo ≠ tests de qualité
2. **CHStorage** montre les meilleurs résultats (38% mutation coverage)
3. **GraphHopper** à 0% mutation coverage illustre les limites des tests par réflexion
4. **Force des tests à 52%** indique que les tests détectent bien les fautes quand ils les couvrent

### Leçons apprises :
- La couverture de code traditionnelle peut être trompeuse
- Les tests de mutation révèlent la véritable qualité des tests
- L'importance de tester les API publiques plutôt que les méthodes privées
- La nécessité d'environnements Java compatibles pour les outils d'analyse

## 🎯 **Conclusion**
Le projet démontre parfaitement l'intérêt du mutation testing pour évaluer la qualité réelle des tests, au-delà des métriques de couverture traditionnelles. Les 12% de mutation coverage obtenus offrent une base solide pour améliorer la suite de tests.

## 📁 **Fichiers générés**
- Rapport JaCoCo : `target/site/jacoco/index.html`
- Rapport PiTest : `target/pit-reports/index.html`
- Configuration Maven : `pom.xml` (profil pitest)
- Tests créés : 
  - `src/test/java/com/graphhopper/ConfigProfileLMProfileTest.java`
  - `src/test/java/com/graphhopper/storage/CHStorageTest.java`
  - `src/test/java/com/graphhopper/GraphHopperExtraTest.java`