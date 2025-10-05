# 📊 **RAPPORT PITEST - TEST DE MUTATION**
**Projet :** GraphHopper Core  
**Date :** 5 octobre 2025  
**Auteurs :** CONDE-NDIAYE  

---

## **1. Résumé Simple**

### 🎯 **Objectif :**
Créer **2 tests** pour améliorer le mutation testing sur **3 classes**.

### 📈 **Résultats :**
- **Mutants tués :** 71 → 75 (+4)
- **Amélioration :** +5.6%
- **2 tests ajoutés :** MutantKillerProfileTest

---

## **2. Classes Testées**

### **Pourquoi ces 3 classes ?**

1. **[Profile](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/main/java/com/graphhopper/config/Profile.java)** - Configuration des profils de routage
2. **[CHStorage](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/main/java/com/graphhopper/storage/CHStorage.java)** - Stockage des raccourcis
3. **[GraphHopper](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/main/java/com/graphhopper/GraphHopper.java)** - Moteur principal

**Justification :** Classes centrales avec logique métier critique et bonne couverture de code existante.

---

## **3. Tests Créés**

### **Tests Existants (7) :**

#### **ConfigProfileLMProfileTest** (4 tests)
- [testPutHintValidation](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/config/ConfigProfileLMProfileTest.java#L20)
- [testPutHintReservedKeys](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/config/ConfigProfileLMProfileTest.java#L28)
- [testValidateProfileNameValid](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/config/ConfigProfileLMProfileTest.java#L36)
- [testValidateProfileNameInvalid](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/config/ConfigProfileLMProfileTest.java#L44)

#### **CHStorageTest** (2 tests)
- [testToDetailsString](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/storage/CHStorageTest.java#L20)
- [testDebugPrintOutput](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/storage/CHStorageTest.java#L35)

#### **GraphHopperExtraTest** (1 test)
- [testReadsSingleGeoJsonFeature](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/GraphHopperExtraTest.java#L25)

### **Nouveaux Tests (2) :**

#### **MutantKillerProfileTest** (2 tests tueurs)
- [test_setName_mutant_killer_validateProfileName](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/MutantKillerProfileTest.java#L15) - Tue mutant "removed call to validateProfileName"
- [test_setCustomModel_mutant_killer_returnValue](https://github.com/Naromba/IFT3913/blob/2025/graphhopper/core/src/test/java/com/graphhopper/MutantKillerProfileTest.java#L28) - Tue mutant "replaced return value with null"

---

## **4. Commandes d'Exécution**

### **JaCoCo (couverture traditionnelle) :**
```bash
cd graphhopper/core
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home
mvn clean test jacoco:report
open target/site/jacoco/index.html
```

### **PiTest (mutation testing) :**
```bash
cd graphhopper/core
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.16/libexec/openjdk.jdk/Contents/Home
mvn clean test org.pitest:pitest-maven:mutationCoverage -Ppitest
open target/pit-reports/index.html
```

---

## **5. Analyse des Mutants**

### **Avant les 2 nouveaux tests :**
- 71 mutants tués
- 11% couverture mutation

### **Après les 2 nouveaux tests :**
- 75 mutants tués (+4)
- 12% couverture mutation

### **Mutants tués spécifiquement :**
1. **Profile.setName()** - Appel à validateProfileName supprimé → TUÉ
2. **Profile.setName()** - Retourne null au lieu de this → TUÉ  
3. **Profile.setCustomModel()** - Retourne null → TUÉ
4. **Profile.setCustomModel()** - Condition supprimée → TUÉ

---

## **6. Conclusion**

### **Succès :**
✅ 2 tests créés exactement  
✅ +5.6% d'amélioration mesurée  
✅ 3 classes maintenues  
✅ Approche scientifique appliquée  

### **Apprentissage :**
Le mutation testing révèle des faiblesses que la couverture traditionnelle ne détecte pas. Même avec 85% de couverture de ligne, des mutants survivent.

### **Recommandation :**
Intégrer PiTest dans le pipeline CI/CD pour surveillance continue de la qualité des tests.
