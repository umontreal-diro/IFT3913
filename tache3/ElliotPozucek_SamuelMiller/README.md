# Elliot Pozucek - Samuel Miller - Tâche 3

La fichier `test.yml` a été modifié pour la tâche 3. [Voir le répo](https://github.com/sa-miller/jackson-core/actions).

## Flags JVM Utilisés

1. **`-Xmx256m`**
   - **Description** : Limite la mémoire de la JVM à 256 Mo.
   - **Motivation** : Tester la sensibilité du code aux contraintes de mémoire.
   - **Impact** : Possiblement ralentit la compilation.
   - **Couverture** : `Coverage with -Xmx256m: 81.64 %`

2. **`-Xlog:gc*,gc+age=trace`**
   - **Description** : Affiche des logs du GarbageCollector détaillés incluant la distribution des objets.
   - **Motivation** : Observer le comportement des objets en mémoire et cycles de vie.
   - **Impact** : Journaux détaillés pour le diagnostic.
   - **Couverture** : `Coverage with -Xlog:gc*,gc+age=trace: 81.65 %`

3. **`-XX:+UseCompressedOops`**
   - **Description** : Utilise des pointeurs compressés (32 bits).
   - **Motivation** : Réduit l'empreinte mémoire sur systèmes 64 bits.
   - **Impact** : Optimisation de l'utilisation mémoire.
   - **Couverture** : `Coverage with -XX:+UseCompressedOops: 81.65 %`

4. **`-enableassertions` (ou `-ea`)**
   - **Description** : Active les assertions.
   - **Motivation** : Renforce la validation des hypothèses dans le code.
   - **Impact** : Améliore la robustesse des tests.
   - **Couverture** : `Coverage with -enableassertions: 81.66 %`

5. **`-XX:+UseStringDeduplication`**
   - **Description** : Déduplication des chaînes, nécessite l'utilisation du G1GC.
   - **Motivation** : Réduit la mémoire utilisée pour les chaînes de caractères identiques à travers les tests (s'il y en a).
   - **Impact attendu** : Moins de consommation mémoire pour les tests de String.
   - **Couverture** : `Coverage with -XX:+UseStringDeduplication: 81.65 %`

### Flag qui n'a pas fonctionné
   - **"-XX:+AggressiveOpts"** <br>
Ce flag n'a pas fonctionné car il n'est plus supporté par la JVM depuis Java 9. Supposement, il activait des optimisations expérimentales qui pouvaient ou non être bénéfique, de ce que j'ai compris.
