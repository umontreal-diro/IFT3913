Voici le lien au powerpoint:

https://docs.google.com/presentation/d/1fGahAUHyv-UuQ-I4nn-E5LLa8JJdpDIK2FIZOUFh4t8/edit?usp=sharing


*Le plan de la présentation a changé un peu en raison de manque de temps. Je passerai plus de temps sur Docker et les containers, et je me limiterai au processus de tests sous différentes distributions Linux quand je donne l'exemple de la compagnie chez qui j'ai fais mon stage.





Je présenterai le processus de développement logiciel de la compagnie où j'ai fait mon stage cet été. Ce processus commence au moment où nous nous faisons assigner une tâche jusqu'au moment où nous déterminons que la mise à jour peut être envoyée aux clients. Puisque le sujet de la semaine est "tests sous différents environnements", l'aspect technique principal de la présentation sera l'utilisation de containers Docker pour compiler et tester le logiciel sous différentes distributions Linux. Cela répondra aux questions suivantes :
- pourquoi veut-on utiliser Docker?
- comment utiliser Docker?
- comment automatiser cet aspect? (Azure DevOps)
- comment ça marche?

Cette partie n'est qu'une seule des nombreuses méthodes que la compagnie utilise pour assurer la qualité de leur logiciel. J'expliquerai donc toutes les étapes du développement logiciel pour montrer tout ce qu'ils font à ces fins, mais seuls les outils Docker et Azure DevOps seront abordés de manière plus approfondie/technique.

Voici le processus que j'aborderai lors de la présentation:

1. Assignation d'un ticket.
2. Création d'une branche git dans le répertoire de la compagnie qui est "hébergé" sur Azure.
3. Développement dans l'éditeur de code.
4. Compilation du code et test des modifications sur une des machines de tests qui sont libres pour s'assurer que le nouveau code fonctionne avant de push les changements.

5. Git commit et git push des changements qui entraînent un appel de Clang-Tidy sur les fichiers modifiés pour évaluer le respect des normes d'écriture de code, trouver des bugs qui peuvent être induits par analyse statique et détection de "mauvaises pratiques".
6. Un autre git commit et git push après avoir résolu les erreurs que Clang-Tidy a sans doute trouvées dans le code.

7. Exécution manuelle des smoke tests avec un script Bash qui est une séquence de tests très simples couvrant tous les modules principaux du logiciel. Ces tests sont exécutés sous un seul environnement Linux (peu importe lequel). Le but est d'assurer la fonctionnalité générale du logiciel avant de passer aux tests unitaires qui prennent plus de temps.

8. Quand les smoke tests passent, création d'une pull request pour la branche.
9. La création de la pull request déclenche l'exécution du pipeline de build automatisé à travers les Pipelines d'Azure DevOps. Ici, je donnerai une explication de ce que sont les Pipelines d'Azure DevOps et j'expliquerai comment on peut organiser cela. Ce n'est pas très différent des GitHub Actions vues en cours. Ce pipeline exécute plusieurs actions détaillées dans les étapes suivantes.

10. Build des images Docker des distributions Linux supportées. C'est ici que je prendrai le temps de faire une présentation détaillée sur Docker, incluant le but de Docker, ce que sont les images et les containers, la différence avec cela et les VMs, comment ça marche, comment l'utiliser, etc.
11. Compilation du code et exécution des tests unitaires dans les containers Docker de chacune des distributions. Les distributions sont toutes évaluées en parallèle.
12. Publication des "artéfacts" pour chacune des distributions. Ce sont les résultats des tests unitaires du logiciel.
13. La pull request sera seulement autorisée si les "artéfacts" montrent que tous les tests unitaires de toutes les distributions ont passé. En d'autres mots, la branche ne sera mergée dans la branche master que si tous les tests ont passé sur toutes les distributions supportées.

14. Ensuite, on test la performance du logiciel sur la distribution linux principale pour s'assurer que la performance n'aie pas vu de dégradation.

15. À la fin du sprint, avant d'envoyer une mise à jour au client, la performance du logiciel est testé une dernière fois, cette fois-ci sur toutes les distributions linux supportées et sur plusieurs machines avec des configurations de processeurs différentes.

Pour terminer, je ferai un recul sur les avantages et inconvénients des étapes de ce processus. De plus, pour souligner l'importance des tests à toutes étapes du développement logiciel, je vais maintenir le compte du nombre de fois qu'on effectue des tests au cours du processus. Je compte présentement 6 étapes distinctes de tests.