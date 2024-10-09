# Tester la diversité d’environnement dans les browsers - Joey Fanor

Pour cette présentation, je vais présenter les différents moyens de tester la diversité d’environnement dans les navigateurs web. Pour cela, je vais brièvement expliquer les problèmes de compatibilité qu’on peut rencontrer dans le développement web et ensuite 
introduire différents outils utilisés communément dans l’industrie pour détecter et possiblement résoudre ces problèmes.

Plus précisément, [PlayWright](https://playwright.dev/) qui permet de créer des tests unitaire qui peuvent être exécutés à travers plusieurs browser et ensuite [eslint-plugin-compat](https://www.npmjs.com/package/eslint-plugin-compat) qui permet de automatiquement détecter des utilisations de fonctions qui ne sont pas utilisables dans la plupart des navigateurs web.
