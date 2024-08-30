# Cas d'étude pour les tâches 2 et 3 de IFT3913

Pour les tâches 2 et 3 du cours, vous choisirez un projet parmi la liste ci-dessous.

- [Makelangelo](#makelangelo)
- [jsoup](#jsoup)
- [guava](#guava)
- [Jackson](#jackson)
- [Graphhopper](#graphhopper)
- [Dubbo](#dubbo)
- [Cryptomator](#cryptomator)

Ces projets ont été sélectionnés pour le cours IFT3913, car ce sont des projets open source, développés en Java, activement maintenus, qui utilisent Maven et des actions Github. Ces projets représentent différents domaines d'application (des micro services à l'art mural) et sont tous à l'état de l'art du développement en Java.

## [Makelangelo](https://github.com/umontreal-diro/Makelangelo-software)

Un programme Java qui prépare des oeuvres d'art pour les traceurs CNC (CNC plotters). Les traceurs CNC sont utilisés pour créer des images 2D et 3D sur des surfaces planes. Un traceur peut dessiner des images en déplaçant ses composants selon les instructions qu'il reçoit. Ce
projet a été créé à l'origine pour le [Makelangelo Art Robot](http://www.makelangelo.com/) qui peut dessiner des affiches et des peintures murales.

| Stats                            | #     |
|:---------------------------------|-------|
| **Modules**                      | 1     |
| **Tests**                        |       |
| com.marginallyclever.makelangelo | 149   |
| **Coverage**                     | 24.4% |


## [jsoup](https://github.com/umontreal-diro/jsoup)

Une bibliothèque Java qui simplifie le travail avec le HTML et le XML du monde réel. Elle offre de nombreuses API utiles pour le traitement du HTML/XML
notamment l'analyse du HTML à partir d'une URL, l'extraction de données en parcourant les DOM (arbres structurés d'objets HTML) et la manipulation d'éléments HTML et la prévention des attaques Cross-Site Scripting (XSS).

| Stats           | #     |
|:----------------|-------|
| **Modules**     | 1     |
| **Tests**       |       |
| org.jsoup.jsoup | 1359  |
| **Coverage**    | 90.4% |

## [Guava](https://github.com/umontreal-diro/guava)

Ce référentiel contient un ensemble de bibliothèques 'core' de Google. Ces bibliothèques incluent de nouveaux types de collection tels que multimap
et multiset, des versions légères de cache, des solutions de publication-abonnement et des extensions aux types de données primitifs Java.

| Stats                          | #      |
|:-------------------------------|--------|
| **Modules**                    | 5      |
| **Tests**                      |        |
| com.google.guava.guava-testlib | 856734 |
| com.google.guava.guava-tests   | 857854 |
| **Coverage**                   | 90.2%  |

## [Jackson](https://github.com/umontreal-diro/jackson-core)

La bibliothèque principale du projet Jackson, un des meilleurs outils pour l'analyse de JSON Java. Les abstractions principales de ce projet prennent également en charge leur implémentation dans d'autres formats de données tels que XML, CSV et Protobuf. Ce projet contient certaines des classes les plus couramment utilisées dans les projets Java pour la lecture/écriture de contenus JSON, telles que JSONParser, JSONGenerator et JSONFactory.

| Stats                              | #     |
|:-----------------------------------|-------|
| **Modules**                        | 1     |
| **Tests**                          |       |
| com.fasterxml.jackson.jackson-base | 1412  |
| **Coverage**                       | 81.5% |

## [Graphhopper](https://github.com/umontreal-diro/graphhopper)

Graphhopper est un moteur de routage qui peut être utilisé comme bibliothèque Java ou comme serveur Web autonome pour calculer la distance,
le temps, les instructions étape par étape et de nombreux attributs routiers pour un itinéraire entre deux ou plusieurs points. Il prend en charge plusieurs
algorithmes de routage populaires tels que Dijkstra ou A*.

| Stats                       | #     |
|:----------------------------|-------|
| **Modules**                 | 10    |
| **Tests**                   |       |
| com.graphhopper.core        | 2532  |
| com.graphhopper.reader-gtfs | 284   |
| com.graphhopper.web-bundle  | 9     |
| com.graphhopper.web-api     | 43    |
| com.graphhopper.web         | 196   |
| com.graphhopper.client-hc   | 24    |
| com.graphhopper.navigation  | 22    |
| com.graphhopper.example     | 4     |
| **Coverage**                | 78.1% |

## [Dubbo](https://github.com/umontreal-diro/dubbo)

Dubbo est un framework de microservices RPC (Remote Procedure Call) hautes performances. Il est utilisé pour résoudre les problèmes de communication
et de gouvernance dans l'architecture de microservices. Les principales fonctionnalités de Dubbo incluent la découverte automatique d'adresses, le contrôle du trafic et l'équilibrage de charge.

| Stats                                                          | #     |
|:---------------------------------------------------------------|-------|
| **Main Modules**                                               | 26    |
| **Tests**                                                      |       |
| org.apache.dubbo.dubbo-common                                  | 1071  |
| org.apache.dubbo.dubbo-serialization-hessian2                  | 808   |
| org.apache.dubbo.dubbo-serialization-fastjson2                 | 806   |
| org.apache.dubbo.dubbo-remoting-api                            | 806   |
| org.apache.dubbo.dubbo-rpc-api                                 | 146   |
| org.apache.dubbo.dubbo-remoting-http                           | 11    |
| org.apache.dubbo.dubbo-remoting-netty                          | 14    |
| org.apache.dubbo.dubbo-dubbo-rpc-injvm                         | 9     |
| org.apache.dubbo.dubbo-dubbo-metrics-api                       | 1135  |
| org.apache.dubbo.dubbo-dubbo-metrics-registry                  | 19    |
| org.apache.dubbo.dubbo-dubbo-metrics-default                   | 36    |
| org.apache.dubbo.dubbo-cluster                                 | 291   |
| org.apache.dubbo.dubbo-metrics-metadata                        | 9     |
| org.apache.dubbo.dubbo-metadata-api                            | 47    |
| org.apache.dubbo.dubbo-rpc-rest                                | 72    |
| org.apache.dubbo.dubbo-remoting-zookeeper                      | 29    |
| org.apache.dubbo.dubbo-remoting-zookeeper-curator5             | 28    |
| org.apache.dubbo.dubbo-remoting-netty4                         | 32    |
| org.apache.dubbo.dubbo-rpc-dubbo                               | 68    |
| org.apache.dubbo.dubbo-rpc-triple                              | 98    |
| org.apache.dubbo.dubbo-registry-api                            | 103   |
| org.apache.dubbo.dubbo-registry-multicast                      | 13    |
| org.apache.dubbo.dubbo-registry-zookeeper                      | 34    |
| org.apache.dubbo.dubbo-registry-multiple                       | 5     |
| org.apache.dubbo.dubbo-monitor-api                             | 9     |
| org.apache.dubbo.dubbo-monitor-default                         | 9     |
| org.apache.dubbo.dubbo-metrics-config-center                   | 2     |
| org.apache.dubbo.dubbo-metrics-prometheus                      | 6     |
| org.apache.dubbo.dubbo-configcenter-zookeeper                  | 4     |
| org.apache.dubbo.dubbo-metadata-report-zookeeper               | 9     |
| org.apache.dubbo.dubbo-metadata-report-redis                   | 6     |
| org.apache.dubbo.dubbo-configcenter-nacos                      | 6     |
| org.apache.dubbo.dubbo-filter-cache                            | 25    |
| org.apache.dubbo.dubbo-filter-validation                       | 20    |
| org.apache.dubbo.dubbo-config-api                              | 682   |
| org.apache.dubbo.dubbo-config-spring                           | 147   |
| org.apache.dubbo.dubbo-metadata-report-nacos                   | 3     |
| org.apache.dubbo.dubbo-qos                                     | 104   |
| org.apache.dubbo.dubbo-native                                  | 1     |
| org.apache.dubbo.dubbo-metadata-processor                      | 83    |
| org.apache.dubbo.dubbo-compatible                              | 236   |
| org.apache.dubbo.dubbo-configcenter-apollo                     | 3     |
| org.apache.dubbo.dubbo-metadata-definition-protobuf            | 1     |
| org.apache.dubbo.dubbo-auth                                    | 18    |
| org.apache.dubbo.dubbo-security                                | 24    |
| org.apache.dubbo.dubbo-reactive                                | 6     |
| org.apache.dubbo.dubbo-spring-security                         | 1     |
| org.apache.dubbo.dubbo-spring-boot-autoconfigure-compatible    | 20    |
| org.apache.dubbo.dubbo-spring-boot-observability-autoconfigure | 7     |
| org.apache.dubbo.dubbo-spring-boot-actuator-compatible         | 8     |
| org.apache.dubbo.dubbo-test-spring                             | 3     |
| org.apache.dubbo.dubbo-test-modules                            | 7     |
| **Coverage**                                                   | 66.2% |

## [Cryptomator](https://github.com/umontreal-diro/cryptomator)

Un projet Java qui permet de chiffrer des fichiers de manière transparente afin de les stocker dans le cloud. Il utilise la norme de chiffrement avancée (AES) avec des clés de 256 bits. Il fonctionne avec n'importe quel service de stockage cloud qui se synchronise avec un répertoire local
tel que Dropbox, Google Drive ou OneDrive.

| Stats                       | #     |
|:----------------------------|-------|
| **Modules**                 | 1     |
| **Tests**                   |       |
| org.cryptomator.cryptomator | 287   |
| **Coverage**                | 13.4% |
