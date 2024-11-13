# Tâche 3 - Modification d'action sur le répertoire Cryptomator
# Nicolas Campbell

[Lien vers le répertoire modifié](https://github.com/NickoCam/cryptomator/blob/develop/.github/workflows/)
Action modifiée: [build.yml](https://github.com/NickoCam/cryptomator/blob/develop/.github/workflows/build.yml)


# Flags ajoutés

1. -XX:+UseG1GC
Utiliser G1GC peut améliorer la performance de l'application en optimisant la gestion de la mémoire et en réduisant les pauses dues au garbage collection.
2. -XX:+UseContainerSupport
UseContainerSupport est particulièrement utile si l'application est exécutée dans un environnement containerisé comme des CI/CD pipelines. Cela permet à la JVM d'adapter ses ressources en fonction de l'environnement d'exécution ce qui mène à une meilleure gestion des ressources.
3. -XX:+EnableNativeMemoryTracking
EnableNativeMemoryTracking peut être utile pour améliorer la traçabilité des problèmes de mémoire et optimiser l'utilisation de la mémoire pendant les tests.
4. -XX:+TieredCompilation
TieredCompilation améliore les performances des tests en réduisant le temps nécessaire pour compiler les méthodes lors de l'exécution.
5. -XX:+PrintGCDetails
PrintGCDetails permet de surveiller les performances des tests, surtout dans des environnements à mémoire partagée ou dans des environnements de test où la performance est importante.

# Génération des logs

echo "Running with JVM flag: $FLAG" (ligne 41) affiche dans les logs la configuration du flag JVM actuel.
echo "Coverage for $FLAG: $coverage%" >> coverage.log (ligne 49) sauvegarde le pourcentage de couverture pour chaque flag dans un fichier coverage.log.
echo "Coverage for $FLAG: $coverage%" (ligne 54) affiche la couverture de chaque flag dans les logs GitHub pour que ce soit visible lors de chaque build.


# Partie humoristique
echo "🤞 Fingers crossed for good coverage!" (ligne 51 de build.yml) vise à afficher un message qui se veut léger lorsque le calcul de couverture est lancé
