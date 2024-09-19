# Couverture de code .NET dans un environnement de travail CI/CD

**Présentateur:**  
Vincent Péloquin

## Aperçu de la présentation

Dans cette présentation, nous explorerons comment l'intégration des mesures de couverture du code dans les pipelines d'intégration continue (CI) améliore la qualité des livraisons de logiciels dans un environnement professionnel. Nous aborderons les pièges potentiels de la couverture de code pour évaluer la qualité des tests. Le tout dans un environnement .NET C# utilisant les pipelines de déploiement azure DevOps.

## Thèmes clés:
0. **Définition du code coverage (au besoin, car je passerai 9e, donc éviter la redondance)**

1. **Couverture du code dans les projets .NET**
   - Brièvement expliquer les outils comme Coverlet.

2. **Intégrer la couverture de code dans les pipelines CI/CD**
   - Aperçu des pipelines Azure DevOps
   - Comment intégrer la couverture du code dans le pipeline.

3. **Utilisation de SonarQube pour l'analyse de la qualité et de la couverture du code**
   - Comment SonarQube s'intègre à Azure DevOps.
   - Visualisation et application des mesures de couverture.
   - Fixer des barrières de qualité (gating) pour une meilleure couverture des tests.

4. **Meilleures pratiques et pièges potentiels**
   - Fixer des objectifs de couverture raisonnables.
   - Importance de tests significatifs, et pas seulement d'une couverture élevée.
   - Les défis de l'équilibre entre la couverture et la qualité des tests.

## Resources:

- **Documentation Microsoft** - [Use Code Coverage for Unit Testing](https://learn.microsoft.com/en-us/dotnet/core/testing/unit-testing-code-coverage?tabs=windows)  
- **Documentation SonarQube** - [SonarQube .NET Test Coverage](https://docs.sonarsource.com/sonarqube/latest/analyzing-source-code/test-coverage/dotnet-test-coverage/)  
- **Documentation Microsoft** - [Review Code Coverage Results in Azure DevOps](https://learn.microsoft.com/en-us/azure/devops/pipelines/test/review-code-coverage-results?view=azure-devops)

