 - Test 1 : Vérifie que la fonction equals compare bien les différentes lignes entre elles. 

 - Test 2 : Vérifie que la fonction getElapsedTime dans StringHelper calcule bien le temps : non, elle ne calcule pas bien le temps, somehow. Le test a du être modifié pour voir l'exécution du code, mais le test inital demandait si 16000 secondes sont bien compilées, ce qui n'est pas le cas, ça devrait donner 4h:26:40 alors que ça donne 4h:26:04. /src/test/java/convenience/TestStringHelper : testGetElapsedTime
 
 - Test 3 : Vérifie que la classe Point2d ne modifie pas imprévisiblement les valeurs et que les égalités soient respectées. /src/test/java/convenience/Point2DTest.java : testAssertPoint2DEquals

 - Test 4 : Vérifie que la fonction distanceSquared fait bien sa job. /src/test/java/convenience/Point2DTest.java : testAssertDistanceSquaredEquals

 - Test 5 : Vérifie que la distance entre deux points est bien la bonne. /src/test/java/convenience/Point2DTest.java : testAssertDistanceEquals

 - Test 6 : Vérifie que le scaling des points est fiable. /src/test/java/convenience/Point2DTest.java : testAssertScaleEquals

 - Test 7 : Vérifie que le carré des ditances est bien le bon. /src/test/java/convenience/Point2DTest.java : testAssertLengthSquaredEquals

 - Test 8 : Vérifie que la longueur d'une ligne est bien la bonne en fonction de ses points. /src/test/java/convenience/Point2DTest.java : testAssertLengthEquals

 - Test 9 : Vérifie que la normalization d'un vecteur se fait correctement. /src/test/java/convenience/Point2DTest.java : testNormalizeFunction

 - Test 10 : Vérifie que la somme de vecteurs se fait de la bonne façon. /src/test/java/convenience/Point2DTest.java : testAddEquals
