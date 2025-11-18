## Tâche 3 - PARAISON-HERARD

## Binôme:

Emmanuel Paraison | Dorensky Hérard

## Lien vers référentiel GitHub

https://github.com/MufasaKhan/GraphHopper

## Documentation – Éléments humoristiques ajoutés

## 1. Rickroll intégré dans la CI

Selon les exigences de la tâche 3, nous avons ajouté un élément humoristique dans la pipeline GitHub Actions.  
Lorsque les tests échouent, GitHub Actions déclenche automatiquement un Rickroll.

Ce comportement est implémenté dans un job additionnel appelé **`rickroll`**, qui dépend du job principal `build` :

```yaml
rickroll:
  needs: build
  if: failure()
  runs-on: ubuntu-latest
  steps:
    - name: Rick Roll
      uses: JavaJam/random-rickroll@v2
```

## 2. Référence humoristique à Volkswagen

```yaml
- name: Volkswagen Integrity Check
  if: success()
  run: echo "All tests passed... unlike Volkswagen's emissions tests"
```

## Emplacement des modifications

Les éléments humoristiques ont été ajoutés dans :
.github/workflows/build.yml
