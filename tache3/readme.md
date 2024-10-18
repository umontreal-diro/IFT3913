# Tâche 3

La tâche 3 se fait en binômes. Les binômes et l'[étude de cas](../cas-etude.md) traités sont les mêmes que pour la [tâche 2](../tache2/readme.md). Les cas de test et le code ne sont pas modifiés, seule la github action est modifiée.

## Resources supplémentaires pour la tâche 3

Cette tâche a 2 objectifs principaux
- modifier une github action pour exécuter plusieurs builds avec différentes configurations de la JVM
- explorer les centaines d'options (flags) de la JVM

Pour lister les flags disponibles pour votre JVM
```
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version
```
Pour utiliser un flag avec Maven (par exemple ici, ExtendedDTraceProbes)
```
export MAVEN_OPTS=" -XX:+ExtendedDTraceProbes"
```
Pour réinitialiser les flags avec Maven 
```
unset MAVEN_OPTS
```
Posts qui documentent certains flags
- [Let’s talk about Java/JVM flags](https://medium.com/codex/lets-talk-about-java-jvm-flags-23fe0f826bc2)
- [Critical Java JVM options and parameters](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/jvm-options-java-parameters-command-line-environment-variable-list-xms-xmx-memory)
- [Working with the JIT Compiler](https://dev.to/yousef_zook/java-performance-4-working-with-the-jit-compiler-1ak4)
- [Optimizing Performance](https://javanexus.com/blog/optimizing-performance-jit-compiler-pitfalls-hotspot)

## Rendu pour la tâche 3

Une fois la tâche accomplie, les étudiants font une 'pull request' sur ce répertoire avec un répertoire de la forme 'NOM1-NOM2/', qui inclut un fichier readme.md indiquant 
- le référentiel (repository) Github qui inclut leur tâche
- un lien vers une page (par ex. readme.md) qui documente votre travail pour la tâche 3

La date limite pour cette 'pull request' est indiquée sur la [page principale](../README.md/#tâche-3-test-sur-divers-environnements) du cours.
