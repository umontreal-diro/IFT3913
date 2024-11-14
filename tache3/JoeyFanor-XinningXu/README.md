# IFT3913 - Tâche 3

Noms:
* Joey Fanor (20228416)
* Xinning Xu (20213497)

## Introduction (avec humour?)

Qui dit Java, dit devoir optimiser le compilateur pour optimiser et réduire l’utilisation de la mémoire. Parce que oui, Java mange la mémoire comme si c’était gratuit. Bref, pour ce TP nous avons testers 5 flags qui (surprise) tente d’optimiser et réduire l’utilisation de la mémoire.

## Comment on as exécuté les tests avec flag?

Nous avons modifier la GitHub action situé dans "test.yml" en ajoutant un tableau contenant les flags à exécuté.

```yml
    strategy:
      matrix:
        jvm_flags: [
          "-XX:+UseZGC", 
          "-XX:ThreadStackSize=512",
          "-XX:+TieredCompilation",
          "-Xms256m",
          "-XX:+PrintCompilation",
        ]
```

et nous avons passer le flag dans le step qui exécute les tests 
```yml
      - name: Run Maven tests with ${{ matrix.jvm_flags }}
        run: mvn verify -DargLine="@{argLine} ${{ matrix.jvm_flags }}"
```

Ainsi, ce step va être exécuté `matrix.jvm_flags.length` fois avec chaque flags.

## Flags testés

### UseZGC

Le garbage collector de base de Java est habituellement G1C, mais il y a aussi ZGC qui est un garbage collector qui à comme objectif de donner une performance à faible latence, ce qui le rend idéal pour les applications où même des petite pause lié au GC peut causer des problème. Dans le cas de Jsoup, cela peut être utile car des utilisateurs peuvent l’utiliser pour des applications où le parsing doit être fait rapidement.

### ThreadStackSize=512

Ce flag spécifie la taille de la mémoire de pile allouée à chaque thread créé par la JVM. Cela peut s’avérer utile à tester car si on spécifie une taille trop grande, JVM peut limiter le nombre de threads que notre application peut créer, ce qui peut réduire la performance et causer des crashs. Donc, tester des petite valeurs pourrait s’avérer utile.

### +TieredCompilation

Ce flag optimise dynamiquement l'exécution du code et des méthodes qui sont utilisés fréquemment non seulement à la compilation, mais aussi durant l’éxécution du code. Ainsi, en général, plus que on laisse l’application run longtemps, plus JVM peut continuellement optimiser. Dans le cas de JSoup, il peut avoir plusieurs fonctions qui sont utilisées fréquemment surtout dans une application qui a pour but de parse une page spécifique à plusieurs reprises.

### Xms256m

Ce flag change la taille initiale du heap pour la machine virtuelle Java (JVM). La taille initiale du heap détermine la quantité de mémoire que la JVM allouera au démarrage, avant la création des objets. Ainsi, nous croyons que pour une librairie comme Jsoup, la taille initial du heap devrait être basé puisque en général, les page HTML n’ont pas une taille élevée.

### +PrintCompilation

L'option PrintCompilation de Java est flag qui enregistre des informations sur les méthodes compilées par le compilateur Just-In-Time (JIT) pendant l'exécution d'une application Java. Ce flag fournit des informations sur les méthodes compilées et à quel niveau d'optimisation. De ce fait, nous croyons que malgré le fait que ça ne optimise pas le code, cela pourrait aider les développeurs à analyser et optimiser  le code selon les méthodes qui font un bottleneck ou qui prennent beaucoup de temps puisque celle-ci sort des données comme:

0 com/example/MyClass::myMethod 243 72 1 (0x4)

Qui après le nom de la méthode indique type de compilation, le temps pris, et le niveau de l’optimization.
