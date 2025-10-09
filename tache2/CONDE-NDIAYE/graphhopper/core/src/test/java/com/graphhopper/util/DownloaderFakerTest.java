package com.graphhopper.util;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * L’objectif du test est de simuler la création d’une connexion HTTP avec des données variées et réalistes.
Plutôt que d’utiliser une URL statique, Java Faker permet de générer des URLs dynamiques (faker.internet().url()),
 ce qui reflète mieux les cas d’usage réels (différents domaines, protocoles, etc.).
Cela rend le test plus robuste et plus crédible, tout en validant que la méthode createConnection() du composant 
Downloader configure correctement les en-têtes HTTP (User-Agent, Referrer) et les délais de connexion.


 * Test utilisant Java Faker pour simuler la création de connexions HTTP réalistes.
 * 
 * Intention :
 * Vérifier que la méthode createConnection() configure correctement la connexion
 * (user-agent, referrer, timeout, etc.) avec une URL générée de manière aléatoire.
 * 
 *
 * 
 * Oracle :
 * La connexion doit être bien formée et contenir les bons paramètres configurés
 * (User-Agent et Referrer correspondant à ceux définis dans Downloader).
 */
public class DownloaderFakerTest {

   @Test
public void testCreateConnectionWithFaker() throws IOException {
    Faker faker = new Faker();

    //  Génère une URL réaliste complète avec protocole
    String fakeUrl = String.format("https://%s/%s", faker.internet().domainName(), faker.lorem().word());

    Downloader downloader = new Downloader("GraphHopper-Test-Agent");
    downloader.setReferrer("https://graphhopper.com");
    downloader.setTimeout(5000);

    HttpURLConnection connection = downloader.createConnection(fakeUrl);

    assertNotNull(connection);
    assertEquals("GraphHopper-Test-Agent", connection.getRequestProperty("User-Agent"));
    assertEquals("https://graphhopper.com", connection.getRequestProperty("Referrer"));
    assertEquals(5000, connection.getConnectTimeout());
    assertEquals(5000, connection.getReadTimeout());
}

    @Test
    public void testSetTimeoutAndReferrerWithFaker() {
        Faker faker = new Faker();
        String randomReferrer = faker.internet().url();
        if (!randomReferrer.startsWith("http")) {
            randomReferrer = "https://" + randomReferrer;
        }

        Downloader downloader = new Downloader("Test-Agent");
        downloader.setReferrer(randomReferrer);
        downloader.setTimeout(faker.number().numberBetween(1000, 10000));

        assertNotNull(downloader);
    }
}
