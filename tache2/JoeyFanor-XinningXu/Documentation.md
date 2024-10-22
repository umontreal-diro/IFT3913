# Documentation Tache 2

## Test 1
Public void testReadIndexOut() teste la méthode public int read(byte[] dest, int offset, int desiredLen) de la classe SimpleBufferedInput du package Internal. 

Nous avons choisi de teste cette méthode en raison de son importance sur la lecture des input streams par les buffers. La méthode read(byte[] dest, int offset, int desiredLen) assure le transfert des inputs streams dans les tableaux, ce qui est essentiel pour les opérations futures de performer.

## Test 2 
Public void testRead() teste la méthode public int read() de la classe SimpleBufferedInput du package Internal.

Nous avons choisi cette méthode pour ajouter un test puisqu’il est nécessaire d’assurer que, dans le cas où les données extraites du buffer qui dépasse la longueur du buffer, il ne causera pas un bug, et que le processus de la lecture est géré correctement malgré la situation. Je trouve qu’il est critique de tester ce méthode puisque la lecture incorrecte des données causera des problèmes pour les opérations futures.

## Test 3 
Public void testReadLimit() teste la méthode public void mark(int readLimit) de la classe SimpleBufferedInput du package Internal, spécifiquement sur le cas où la limite de la lecture dépasse la taille du buffer. 

Nous avons choisi de tester de méthode puisqu’en assurant que la lecture du système ne va pas dépasser la taille du buffer, on empêche des erreurs telles que le dépassement de mémoire ou des comportements inattendus lors de la lecture de grandes quantités de données. Le test vérifie également qu'une exception IllegalArgumentException est lancée correctement lorsque la limite de lecture dépasse la taille du buffer, et évite des bugs lors des cas limites (edge cases).

## Test 4 
Public void testReset() teste la méthode public void reset() de la classe SimpleBufferedInput du package Internal, en se concentrant sur le cas limite où la position du marqueur est négative. 

On a choisi cette méthode puisque si la méthode ne lance pas correctement l'exception IOException lorsqu'il y a un marqueur invalide, cela pourrait entraîner une mauvaise gestion des positions lors de la lecture des streams d'entrée. reset() est nécessaire pour revenir à une position marquée dans un stream. Si ce comportement échoue, cela pourrait causer des incohérences dans la gestion des données, avec des conséquences comme la perte ou la duplication de données lues. 

## Test 5 
Public void testMap() teste la méthode public Connection data(Map<String, String> data) de la classe HttpConnection du package helper. 

On a choisi cette méthode à tester puisqu’en testant cette méthode, on assure que les données sont correctement validées et ajoutées. Si la méthode ne fonctionne pas correctement, les requêtes HTTP pourraient être mal formées ou manquer des paramètres critiques, ce qui nuira à l'intégrité des communications entre le client et le serveur. Le test vérifie aussi qu'une ValidationException est lancée lorsqu’il y a des données invalides, garantissant ainsi que la méthode continuera à rouler lors des entrées incorrectes.


## Test 6

.headers() dans la classe HttpConnection (Test unitaire dans HttpConnectionTest getHeaders())

Nous avons choisi d’ajouter ce test car premièrement, cela est une classe essentielle pour que les utilisateurs de la librairie puissent parse et web scrap. En d’autres mots, il est possible qu’un utilisateur puisse changer les headers d’une requête à parse basé sur des conditions, et si .headers() ne retourne pas correctement les headers définis dans une requête, cela pourrait dérouter les développeur qui utilise la librairie.
Test 7

.cookiestores() dans la classe HttpConnection (Test unitaire dans HttpConnectionTest cookieStore())

De plus, nous avons choisi d’ajouter ce test car un utilisateur pourrait définir un cookie manager venant d’une third-party library pour gérer les cookies, et si .cookiestores() ne retourne pas le bon cookie manager associée, un développeur qui utilise JSoup pour parser des pages qui demande des cookies spécifiques pourrait être confus sur pourquoi ça ne marche pas.


## Test 8

Attribute.createFromEncoded(key, value) dans la classe Attribute (Test unitaire dans AttributeTest testCreateAttributeFromEncoded())

Vérifier le bon fonctionnement de cette fonction est essentiel car elle permet d’instancier une classe Attribute et cette classe est essentielle pour gérer les attributs individuels des éléments HTML dans JSoup.

## Test 9

TokenQueue.consume(seq) dans la classe TokenQueue (Test unitaire dans TokenQueueTest dans testConsumeSequence())

Cette méthode est utilisée à l’interne pour permettre le parsing de document HTML/XSS. Par exemple, elle est utilisée pour parse un sélecteur CSS, ce qui est couramment utilisé par les développeur qui utilise cette librairie.

## Test 10

.postDataCharset(String) dans la classe HttpConnection (Test unitaire dans HttpConnectionTest dans postDataCharset())

Malgré le fait que cette fonction n’est pas utilisée à l’interne dans la librairie, il s’agit quand même d’une fonction très utile pour les développeurs qui utilisent la librairie. Effectivement, un développeur pourrait vouloir s’assurer que le charset d’une requête soit quelque chose soit égal à une valeur spécifique avant de faire une requête HTTP post.
