# Tâche 2

La tâche 2 se fait en binômes. Quand un binôme est formé, il fait une 'pull request' sur ce répertoire pour ajouter un sous-répertoire de la forme 'NOM1_NOM2/'. Ce répertoire inclut un fichier readme.md dans lequel les étudiants indiquent leur nom et prénom, suivant le format documenté ici: [github/PULL_REQUEST_TEMPLATE/tache2-readme.md](../.github/PULL_REQUEST_TEMPLATE/tache2-readme.md).

## Instructions pour la tâche 2

Pour la tâche 2, chaque binôme doit accomplir les étapes suivantes:

- sélectionner entre une et 3 classes du [cas d'étude](../README.md/#cas-détude)  qui ont déjà des tests, mais qui ne couvrent pas 100% du code
- installer et utiliser [ChatUniTest](https://github.com/ZJU-ACES-ISE/ChatUniTest) avec un modèle de langage ouvert exécuté localement (ex. via Ollama) pour générer automatiquement des tests supplémentaires sur les classes sélectionnées.
- Documenter le résultat de cette génération : où sont les tests générés ? les tests générés compilent-ils et s'exécutent-ils sans intervention manuelle ? sinon, combien de corrections ont été nécessaires ?  
- Comparer qualitativement les oracles produits par l'IA à ceux écrits à la main (pertinence, spécificité, ou au contraire vérifications triviales). 
- ajouter [pitest](https://pitest.org/) au projet
- exécuter une analyse de mutation sur les classes sélectionnées
- calculer le score de mutation avec les tests originaux pour les classes sélectionnées
- calculer le score de mutation avec les nouveaux tests et déterminer si les tests générés détectent tous les mutants. Expliquez quels mutants sont détectés et pourquoi. 
- s’il y a des mutants non détectés, ajouter manuellement des tests. Documenter précisément chaque cas de test: nom du test, intention du test (quel comportement est testé), motivation des données de test choisies, explication de l'oracle (comment déterminer le comportement attendu)

## Critères d'évaluation de la tâche 2

| critère | description |
|-------------------------------------------- | ----|
| classes à tester (10%) | justifier que les classes et méthodes choisies pour la génération de test ne sont pas déjà couvertes et ont des mutants vivants  |
| IA et test (10%) |  ChatUniTest est installé dans le pipeline Maven | 
| tests générés (10%)	| Générer des tests avec ChatUniTest | 
| documentation tests	(20%)	| les tests générés sont expliqués et critiqués| 
| mutation (15%) | exécuter pitest sur les classes testées, avec tests originaux puis avec les tests générés | 
| documentation mutants (15%) 	| les mutants détectés par les tests générés sont documentés et la raison pour la détection est expliquée| 
| test supplémentaires  (10%) | les tests écrits à la main sont clairement documentés   |
| exécution (10%)	| tous les nouveaux tests s'exécutent avec succès dans la Github action| 
| Format de documentation | toute la documentation doit être dans un fichier readme.md unique, à la racine du référentiel Github de votre tâche |



## Instructions pour rendre la tâche 2

Une fois la tâche accomplie, les étudiants font une nouvelle 'pull request' sur le sous-répertoire de leur binôme et ajoutent les informations suivantes dans le fichier readme:
- un lien vers le référentiel (repository) Github qui inclut leur tâche
- un lien vers la page README.md qui documente la tâche (readme uniquement, pas de docx, google docs ou autre)

Le format pour la PR est documenté ici: [.github/PULL_REQUEST_TEMPLATE/tache2-readme.md](../.github/PULL_REQUEST_TEMPLATE/tache2-readme.md).

La date limite pour la seconde 'pull request' est indiquée sur la [page principale](../README.md/#evaluation-ift3913---a25) du cours.
