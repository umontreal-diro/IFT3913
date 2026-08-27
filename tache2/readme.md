# Tâche 2

La tâche 2 se fait en binômes. Quand un binôme est formé, il fait une 'pull request' sur ce répertoire pour ajouter un sous-répertoire de la forme 'NOM1_NOM2/'. Ce répertoire inclut un fichier readme.md dans lequel les étudiants indiquent leur nom et prénom, suivant le format documenté ici: [.github/PULL_REQUEST_TEMPLATE/tache2-readme.md](.github/PULL_REQUEST_TEMPLATE/tache2-readme.md).

## Instructions pour la tâche 2

Pour la tâche 2, chaque binôme doit accomplir les étapes suivantes:

- sélectionner entre une et 3 classes de [Tika](https://github.com/umontreal-diro/tika)  qui ont déjà des tests, mais qui ne couvrent pas 100% du code
- utiliser un outil de génération de tests basé sur l'IA (ex. [ChatUniTest](https://github.com/ZJU-ACES-ISE/ChatUniTest)) avec un modèle de langage ouvert exécuté localement (ex. via Ollama) pour générer automatiquement des tests supplémentaires sur les mêmes classes.
- documenter précisément chaque cas de test:
- Documenter le résultat de cette génération : où sont les tests générés ? les tests générés compilent-ils et s'exécutent-ils sans intervention manuelle ? sinon, combien de corrections ont été nécessaires ?  Documenter le nom du test, intention du test (quel comportement est testé), motivation des données de test choisies, explication de l'oracle (comment déterminer le comportement attendu)
- Comparer qualitativement les oracles produits par l'IA à ceux écrits à la main (pertinence, spécificité, ou au contraire vérifications triviales). 
- ajouter [pitest](https://pitest.org/) au projet
- exécuter une analyse de mutation sur les classes sélectionnées
- calculer le score de mutation avec les tests originaux pour les classes sélectionnées
- calculer le score de mutation avec les nouveaux tests et déterminer si les nouveaux tests détectent de nouveaux mutants. Si oui, expliquez quels mutants sont détectés et pourquoi. Si non, ajoutez des tests pour détecter au moins 2 nouveaux mutants et documentez / justifiez votre démarche.

## Instructions pour rendre la tâche 2

Une fois la tâche accomplie, les étudiants font une nouvelle 'pull request' sur le sous-répertoire de leur binôme et ajoutent les informations suivantes dans le fichier readme:
- un lien vers le référentiel (repository) Github qui inclut leur tâche
- un lien vers la page README.md qui documente la tâche

Le format pour la PR est documenté ici: [.github/PULL_REQUEST_TEMPLATE/tache2-readme.md](.github/PULL_REQUEST_TEMPLATE/tache2-readme.md).

La date limite pour la seconde 'pull request' est indiquée sur la [page principale](../README.md/#evaluation-ift3913---a25) du cours.
