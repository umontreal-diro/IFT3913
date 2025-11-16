# Tâche 3 — TIMMONS-BENCHEIKH

## Référentiel GitHub modifié
🔗 [https://github.com/wudm12/graphhopper](https://github.com/wudm12/graphhopper)

## Documentation du travail
📄 [docs/tache3.md](https://github.com/wudm12/graphhopper/blob/master/docs/tache3.md)

---

## Objectif
Cette tâche visait à :
- modifier le **workflow GitHub Actions** pour faire échouer le build si le *mutation score* PIT diminue ;
- ajouter de nouveaux **tests unitaires avec Mockito** pour renforcer la couverture du module `core` ;
- introduire un **élément d’humour (Rickroll)** lorsque les tests échouent.

---

## Modifications principales

### 1. Workflow GitHub Actions
- Le fichier `.github/workflows/build.yml` a été modifié.
- Un bloc compare le score de mutation actuel et précédent, et **interrompt le build** (`exit 1`) si le score diminue.
- Le workflow inclut également une **action humoristique “Rickroll”** déclenchée en cas d’échec :
  ```yaml
  - name: Rickroll on failure
    if: failure()
    uses: ./.github/actions/rickroll


L’action personnalisée se trouve dans .github/actions/rickroll/action.yml et affiche une image + lien vers la chanson “Never Gonna Give You Up”.

2. Tests unitaires avec Mockito

Deux classes de tests ont été ajoutées dans core/src/test/java/com/graphhopper/storage/ :

CHStorageMockitoTest.java

Simule le comportement de la classe Directory.

Vérifie la création d’un objet CHStorage sans dépendre d’implémentations réelles.

TurnCostStorageMockitoTest.java

Simule un stockage de coûts de virage (TurnCostStorage).

Valide les appels attendus à certaines méthodes simulées.

Ces tests permettent de valider les interactions entre composants et d’améliorer la couverture de test sans dépendances externes.


3. Validation

Les tests ont été exécutés avec succès via Maven :

mvn -pl core clean test -Dtest='*MockitoTest'


Les exécutions GitHub Actions ont confirmé :

le bon fonctionnement du Mutation Check (score PIT) ;

le déclenchement du Rickroll lors d’un échec simulé.

Résumé

Workflow GitHub Actions modifié
Deux tests unitaires Mockito ajoutés
Rickroll intégré en cas d’échec
Documentation complète dans docs/tache3.md

Binôme :

Wayne TIMMONS

Ayoub BENCHEIKH
