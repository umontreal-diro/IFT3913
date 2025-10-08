Aïssatou Ndiaye
Naromba Condé

# IFT3913 - Tâche 2 : Mutation Testing avec PiTest

## Prérequis
- Java 17
- Maven

## Configuration Java
    export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home

## Exécution des tests
    cd graphhopper/core
    mvn clean test -Dmaven.test.failure.ignore=true

## Génération du rapport de couverture (JaCoCo)
    cd graphhopper/core
    mvn clean test jacoco:report
    open target/site/jacoco/index.html

## Génération du rapport de mutation (PiTest)
    cd graphhopper/core
    mvn clean test org.pitest:pitest-maven:mutationCoverage -Ppitest -Dmaven.test.failure.ignore=true
    open target/pit-reports/index.html

## Fichiers du rapport
- **Rapport principal :** `Rapport.md`
- **Images :** dossier `image/`