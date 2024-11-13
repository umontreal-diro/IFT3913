# IFT3913 - TÂCHE #2

### Équipe
Simon Lajoie - 20145660<br>
Erick Diaz - 20233602

### Étude de cas choisi:
[Makelangelo](https://github.com/umontreal-diro/Makelangelo-software)

### Lien repository GitHub:
[Repo](https://github.com/Simon-Lajoie/Makelangelo-software)

### Coverage globale du projet:
[jaCoCo index](https://tlaloc-debug.github.io/coverage-makelangelo/)
### Amelioration globale
24,46% -> 29,4%

## Coverage par tests

### Lien vers QuadGraph:
**Tests**<br>[QuadGraph Tests](https://github.com/Simon-Lajoie/Makelangelo-software/blob/master/src/test/java/com/marginallyclever/convenience/QuadGraphTest.java)<br>
**Coverage**<br>[QuadGraph Coverage](https://tlaloc-debug.github.io/coverage-makelangelo/com.marginallyclever.convenience/QuadGraph.html)

### Lien vers FilterCMYK:
**Tests**<br>[FilterCMYK Tests](https://github.com/Simon-Lajoie/Makelangelo-software/blob/master/src/test/java/com/marginallyclever/makelangelo/makeart/imagefilter/FilterCMYKTest.java)<br>
**Corevage**<br>[FilterCMYK Coverage](https://tlaloc-debug.github.io/coverage-makelangelo/com.marginallyclever.makelangelo.makeart.imagefilter/FilterCMYK.html)

### Lien vers FilterGaussianBlur:
**Tests**<br>[FilterGaussianBlur Tests](https://github.com/Simon-Lajoie/Makelangelo-software/blob/master/src/test/java/com/marginallyclever/makelangelo/makeart/imagefilter/FilterGaussianBlurTest.java)<br>
**Coverage**<br>[FilterGaussianBlur Coverage](https://tlaloc-debug.github.io/coverage-makelangelo/com.marginallyclever.makelangelo.makeart.imagefilter/FilterGaussianBlur.html)

### Lien vers RangeSlider:
**Tests**<br>[RangeSlider Tests](https://github.com/Simon-Lajoie/Makelangelo-software/tree/master/src/test/java/com/marginallyclever/makelangelo/rangeslider)<br>
**Coverage**<br>[RangeSlider Coverage](https://tlaloc-debug.github.io/coverage-makelangelo/com.marginallyclever.makelangelo.rangeslider/RangeSlider.html)

### Lien vers Noise:
**Tests**<br>[Noise Tests](https://github.com/Simon-Lajoie/Makelangelo-software/tree/master/src/test/java/com/marginallyclever/convenience/noise)<br>
**Coverage**<br>[Noise Coverage](https://tlaloc-debug.github.io/coverage-makelangelo/com.marginallyclever.convenience.noise/index.html)

# IFT3913 - TÂCHE #3

### Test 1
### MAX RAM Test
L'idée ici est de vérifier si le logiciel peut fonctionner sur des machines avec une mémoire RAM limitée, en tenant compte par exemple que le logiciel pourrait être utilisé dans une entreprise où les équipements sont souvent restreints en termes de capacité de traitement et de mémoire disponible.

### Test 2
### HEAP USE
L'idée de ce test est de vérifier si la quantité de mémoire allouée pour le tas du garbage collector est suffisante pour assurer la maintenance du système. Le pourcentage de la mémoire utilisée est calculé en divisant la quantité de mémoire utilisée par la quantité totale de mémoire disponible. Pour obtenir ces données, nous utilisons le drapeau qui imprime les informations détaillées du GC et enregistrons ces informations dans un fichier. Ensuite, nous exécutons un script bash pour retrouver les informations et effectuer les calculs.

### Test 3
### Max Cache Test
L'idée de ce test est d'exécuter le logiciel avec une quantité limitée de mémoire cache en tenant compte du fait qu'il pourrait être, à terme, implémenté dans un système embarqué au sein d'une machine de contrôle numérique. Dans ce cas, nous devons nous assurer que le logiciel peut fonctionner avec une quantité limitée de mémoire cache.

### Test 4
### Max Cache Test
L'idée de ce test est de vérifier la compatibilité du logiciel sur des systèmes 64 et 32 bits. Normalement, cela se fait avec le paramètre -d32, mais il semble que la version de la JVM ne prenne pas en charge ce paramètre. Nous avons donc décidé de réaliser un test en simulant la différence entre ces deux architectures.
### L'idée est de limiter la mémoire cache à un nombre déterminé de mots pour l'architecture 64 bits. En considérant qu'une architecture 64 bits utilise des mots de 8 octets et qu'une architecture 32 bits utilise des mots de 4 octets, nous pouvons effectuer un second test en limitant la quantité de mémoire cache, mesurée en mots, à la moitié de celle utilisée pour le test en 64 bits. Enfin, nous tirons une conclusion de la manière suivante :
- Si les deux tests réussissent, le logiciel peut être exécuté sur des systèmes 64 et 32 bits.
- Si un test réussit et l'autre échoue, le logiciel ne peut être exécuté que sur des systèmes 32 bits.
- Si les deux tests échouent, il est nécessaire de les réexécuter avec des valeurs différentes.
La conclusion est obtenue en exécutant un script bash.
