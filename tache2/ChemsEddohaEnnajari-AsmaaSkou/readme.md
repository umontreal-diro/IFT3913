# Tâche 2 - Chems-Eddoha Ennajari - Asmaa Skou


******
# Projet Makelangelo

## Répertoire

- **Projet :** [Makelangelo](https://github.com/umontreal-diro/Makelangelo-software)
- **Lien vers le répertoire du projet sur GitHub :** [Makelangelo-software Repository](https://github.com/chemsenn/Makelangelo-software.git)

## Membres

- Chems-Eddoha Ennajari, ID: 20201488
- Asmaa Skou, ID: 20217232



Documentation:

# Tests Unitaires du Projet Makelangelo



### Tests pour la Classe `MainFrame`

- ### Objectif Principal
  Vérifier que la classe `MainFrame` applique correctement les préférences de fenêtre lors de son ouverture.

- ### Tests Réalisés
  - **testDefaultSizeAndPosition()**
    - *Objectif :* Vérifier que `MainFrame` utilise les dimensions par défaut et se centre sur l'écran quand aucune configuration personnalisée n'est fournie.
  - **testCustomSizeAndPosition()**
    - *Objectif :* Confirmer que `MainFrame` utilise les préférences personnalisées de taille et de position si elles sont fournies.

- ### Méthodologie
  - Les préférences sont initialisées avant chaque test et nettoyées après pour éviter les effets de bord.
  - La création et l'évaluation de la fenêtre sont réalisées pour valider le comportement attendu.


## Tests pour la Classe `RecentFiles`

Les tests de la classe `RecentFiles` assurent la gestion correcte de la liste des fichiers récents, incluant l'ajout, la récupération, la suppression, et la persistance.

- ### Objectif Principal
  Garantir un fonctionnement robuste de la gestion des fichiers récents avec des manipulations correctes des entrées.

- ### Tests Réalisés
  - **testAddFilename()**
    - *Objectif :* Vérifier l'ajout correct de noms de fichiers, respectant l'ordre et évitant les doublons.
  - **testGetMaxFiles()**
    - *Objectif :* Confirmer la limite maximale des fichiers récents à 10.
  - **testStorageAndRetrieval()**
    - *Objectif :* Assurer la persistance et la récupération correcte de la liste à travers différentes instances.
  - **tests Additionnels :** Comprennent la suppression de fichiers, le traitement des doublons, et l'ajout de noms vides.

- ### Méthodologie
  - Chaque test initialise un état propre des préférences avant exécution.
  - Validation des comportements avec assertions sur l'ordre des fichiers et la gestion des entrées invalides.

## Conclusion

Les tests unitaires assurent que les fonctionnalités des classes `MainFrame` et `RecentFiles` fonctionnent comme prévu, permettant une expérience utilisateur fiable et cohérente.

