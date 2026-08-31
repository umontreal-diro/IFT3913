# Instructions pour contribuer les tâches 1, 2 et 3 avec des Pull Requests (PR)

Nous utilisons des Github Actions Workflows pour vérifier la conformité des PRs que vous faites pour rendre vos devoirs. 
Vous devez impérativement suivre ces instructions, ou bien vos PRs seront refusées et vos devoirs ne seront pas soumis.

## Tâche 1

Choisir le sujet qui vous intéresse parmi les [sujets proposés](./presentations/), puis modifier le fichier readme.md correspondant

Première PR: ajouter votre nom sur le sujet choisi

Deuxième PR: ajouter un lien vers la présentation

Le format de la PR est automatiquement vérifié par un [workflow Github](.github/workflows/validate-presentation-selection.yml). Vous ne devez pas changer les intitulés des sujets, ni d'autres parties du fichier readme.md, sinon vos PR seront automatiquement refusées.

## Tâche 2

Lorsque vous êtes prêt à rendre la tâche 2, en binôme, veuillez créer un répertoire ```NOM1_NOM2``` dans le répertoire [tache2](./tache2/) puis préparer une pull request en suivant le format documenté ici: [.github/PULL_REQUEST_TEMPLATE/tache2-readme.md](.github/PULL_REQUEST_TEMPLATE/tache2-readme.md).

Aucune PR pour la tâche 2 ne sera acceptée avant 9 octobre 2026.

Le format de la PR est vérifié par un [workflow Github](.github/workflows/validate-tache2-readme.yml), la validité de la date de rendu est vérifié par un autre [workflow Github](.github/workflows/check-tache2-submissions.yml).

## Tâche 3

Lorsque vous êtes prêt à rendre la tâche 3, en binôme, veuillez créer un répertoire ```NOM1_NOM2``` dans le répertoire [tache3](./tache3/) puis préparer une pull request en suivant le format documenté ici: [.github/PULL_REQUEST_TEMPLATE/tache3-readme.md](.github/PULL_REQUEST_TEMPLATE/tache3-readme.md).

Aucune PR pour la tâche 3 ne sera acceptée avant 13 novembre 2026.

Le format de la PR est vérifié par un [workflow Github](.github/workflows/validate-tache3-readme.yml), la validité de la date de rendu est vérifié par un autre [workflow Github](.github/workflows/check-tache2-submissions.yml).

