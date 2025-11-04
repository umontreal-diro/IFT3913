# Tâche 3

La tâche 3 se fait avec les mêmes binômes que pour la tâche 2.

## Instructions pour la tâche 3

Pour la tâche 3, chaque binôme doit accomplir les étapes suivantes:
- modifier le workflow GitHub Actions de graphhopper de tel sorte à ce que le processus de buils échoue si le score de mutation baisse après un commit
- documenter et expliquer les choix de conception de d'implémentation pour cette modification de workflows, ainsi que la manière dont cette modification a été validée
- choisir une ou deux classes de graphhopper et écrire des nouveaux cas de test (au moins 2 nouveaux cas de test, un pour chaque classe simulée) pour lesquels vous simulez au moins 2 classes différentes avec des mocks pilotés avec la bibliothèque [mockito](https://site.mockito.org/)
- documenter et justifier le choix des classes testées, le choix des classes simulées, la définition des mocks et le choix des valeurs simulées.
- introduire un élément d'humour dans la suite de test de graphhopper: [rickroll](https://en.wikipedia.org/wiki/Rickrolling) quand un cas de test de graphhopper échoue. Pour cela, vous invoquerez une action réutilisable qui rickroll, par exemple, en modifiant la github action existante [random-rickroll](https://github.com/TejasvOnly/random-rickroll/tree/master), ou bien faire votre propre implémentation d'une action qui rickroll, par exemple en [rust](https://youtu.be/dQw4w9WgXcQ?si=0VWLSHX2-SsCIzG5).

## Instructions pour rendre la tâche 3

Une fois la tâche accomplie, les étudiants font une 'pull request' sur ce répertoire avec un répertoire de la forme 'NOM1-NOM2/', qui inclut un fichier readme.md indiquant 
- le référentiel (repository) Github qui inclut la github action qui a été modifiée
- un lien vers une page qui documente votre travail pour la tâche 3

La date limite pour cette 'pull request' est indiquée sur la [page principale](../README.md/#evaluation-ift3913---a25) du cours.
