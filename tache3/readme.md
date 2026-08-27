# Tâche 3

La tâche 3 se fait avec les mêmes binômes que pour la tâche 2.

## Instructions pour la tâche 3

Pour la tâche 3, chaque binôme doit accomplir les étapes suivantes:

- modifier le workflow GitHub Actions de Tika de telle sorte que le processus de build échoue si un commit introduit une nouvelle vulnérabilité détectée par Semgrep;
- documenter et expliquer les choix de conception et d’implémentation pour cette modification de workflow, ainsi que la manière dont cette modification a été validée;
- modifier le code de Tika afin d’introduire 7 vulnérabilités de types différents détectables par Semgrep;
- documenter et justifier chaque vulnérabilité introduite: le choix du fichier modifié, le type de vulnérabilité, la raison pour laquelle le code introduit est vulnérable, et le résultat obtenu avec Semgrep;
- comparer le rapport Semgrep obtenu avant les modifications avec le rapport obtenu après l’introduction des vulnérabilités, afin de montrer quelles vulnérabilités sont nouvellement introduites;
- introduire un élément d'humour dans la suite de test de graphhopper: [rickroll](https://en.wikipedia.org/wiki/Rickrolling) quand un cas de test de graphhopper échoue. Pour cela, vous invoquerez une action réutilisable qui rickroll, par exemple, en modifiant la github action existante [random-rickroll](https://github.com/TejasvOnly/random-rickroll/tree/master), ou bien faire votre propre implémentation d'une action qui rickroll, par exemple en [rust](https://youtu.be/dQw4w9WgXcQ?si=0VWLSHX2-SsCIzG5).

## Instructions pour rendre la tâche 3

Une fois la tâche accomplie, les étudiants font une 'pull request' sur ce répertoire avec un répertoire de la forme 'NOM1_NOM2/', qui inclut un fichier readme.md indiquant 
- le référentiel (repository) Github qui inclut la github action qui a été modifiée
- un lien vers la page README.md qui documente votre travail pour la tâche 3

Le format pour la PR est documenté ici: [github/PULL_REQUEST_TEMPLATE/tache3-readme.md](../.github/PULL_REQUEST_TEMPLATE/tache3-readme.md)

La date limite pour cette 'pull request' est indiquée sur la [page principale](../README.md/#evaluation-ift3913---a25) du cours.
