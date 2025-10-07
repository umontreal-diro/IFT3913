# Auteurs:

Lallia Diakite 

Tunwend-raabo Fahîma Carmen Dabo

# Tests unitaires et tests de mutation
# Objectif:
Dans le cadre de la tâche 2, nous avons appliqué les tests de mutation avec PIT sur le module graphhopper-web-api, et plus précisément sur le package com.graphhopper.jackson.
L’objectif était d’évaluer la qualité et la robustesse des tests unitaires existants en mesurant leur capacité à détecter des mutations artificielles dans le code.

# Méthodologie :
# 1-Configuration du plugin PIT
Le plugin pitest-maven a été ajouté au fichier pom.xml du module web-api, avec le plugin pitest-junit5 pour supporter les tests JUnit 5.
# 2-Exécution de PIT
La commande suivante a été utilisée :
cd web-api
mvn org.pitest:pitest-maven:mutationCoverage \
  -DtargetClasses="com.graphhopper.jackson.*" \
  -DtargetTests="com.graphhopper.jackson.*Test" \
  -DtestPlugin=junit5

# Le rapport HTML est ensuite généré à l’emplacement :
web-api/target/pit-reports/index.html

# 3-Résultats obtenus
Nombre de classes analysées : 16
Line Coverage (JaCoCo) : 53 %
Mutation Coverage (PIT) : 52 %
Test Strength : 83 %
Ces résultats indiquent que plus de la moitié du code est couvert par des tests, et que la majorité des mutations sur les lignes couvertes sont détectées par les tests actuels.
# 4-Interprétation
La couverture de ligne (53 %) montre qu’il reste du code non testé.
Le taux de mutation (52 %) révèle qu’environ la moitié des mutants ne sont pas tués, ce qui signifie que certains cas ne sont pas encore couverts.
Le test strength (83 %) montre que les tests existants sont globalement efficaces, mais qu’il reste place à amélioration.

