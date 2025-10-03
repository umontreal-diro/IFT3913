package com.graphhopper.storage;

import com.graphhopper.routing.ch.PrepareEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CHStorageTest {

    @Test
    void setAndGetLevels() {
        RAMDirectory dir = new RAMDirectory();
        CHStorage store = new CHStorage(dir, "ch1", -1, false);
        store.create(30, 5);
        assertEquals(0, store.getLevel(store.toNodePointer(10)));
        store.setLevel(store.toNodePointer(10), 100);
        assertEquals(100, store.getLevel(store.toNodePointer(10)));
        store.setLevel(store.toNodePointer(29), 300);
        assertEquals(300, store.getLevel(store.toNodePointer(29)));
    }

    @Test
    void createAndLoad(@TempDir Path path) {
        {
            GHDirectory dir = new GHDirectory(path.toAbsolutePath().toString(), DAType.RAM_INT_STORE);
            CHStorage chStorage = new CHStorage(dir, "car", -1, false);
            // we have to call create, because we want to create a new storage not load an existing one
            chStorage.create(5, 3);
            assertEquals(0, chStorage.shortcutNodeBased(0, 1, PrepareEncoder.getScFwdDir(), 10, 3, 5));
            assertEquals(1, chStorage.shortcutNodeBased(1, 2, PrepareEncoder.getScFwdDir(), 11, 4, 6));
            assertEquals(2, chStorage.shortcutNodeBased(2, 3, PrepareEncoder.getScFwdDir(), 12, 5, 7));
            // exceeding the number of expected shortcuts is ok, the container will just grow
            assertEquals(3, chStorage.shortcutNodeBased(3, 4, PrepareEncoder.getScFwdDir(), 13, 6, 8));
            assertEquals(5, chStorage.getNodes());
            assertEquals(4, chStorage.getShortcuts());
            chStorage.flush();
            chStorage.close();
        }
        {
            GHDirectory dir = new GHDirectory(path.toAbsolutePath().toString(), DAType.RAM_INT_STORE);
            CHStorage chStorage = new CHStorage(dir, "car", -1, false);
            // this time we load from disk
            chStorage.loadExisting();
            assertEquals(4, chStorage.getShortcuts());
            assertEquals(5, chStorage.getNodes());
            long ptr = chStorage.toShortcutPointer(0);
            assertEquals(0, chStorage.getNodeA(ptr));
            assertEquals(1, chStorage.getNodeB(ptr));
            assertEquals(10, chStorage.getWeight(ptr));
            assertEquals(3, chStorage.getSkippedEdge1(ptr));
            assertEquals(5, chStorage.getSkippedEdge2(ptr));
        }
    }

    @Test
    public void testBigWeight() {
        CHStorage g = new CHStorage(new RAMDirectory(), "abc", 1024, false);
        g.shortcutNodeBased(0, 0, 0, 10, 0, 1);

        g.setWeight(0, Integer.MAX_VALUE / 1000d + 1000);
        assertEquals(Integer.MAX_VALUE / 1000d + 1000, g.getWeight(0));

        g.setWeight(0, ((long) Integer.MAX_VALUE << 1) / 1000d - 0.001);
        assertEquals(((long) Integer.MAX_VALUE << 1) / 1000d - 0.001, g.getWeight(0), 0.001);

        g.setWeight(0, ((long) Integer.MAX_VALUE << 1) / 1000d);
        assertTrue(Double.isInfinite(g.getWeight(0)));
        g.setWeight(0, ((long) Integer.MAX_VALUE << 1) / 1000d + 1);
        assertTrue(Double.isInfinite(g.getWeight(0)));
        g.setWeight(0, ((long) Integer.MAX_VALUE << 1) / 1000d + 100);
        assertTrue(Double.isInfinite(g.getWeight(0)));
    }

    @Test
    public void testLargeNodeA() {
        int nodeA = Integer.MAX_VALUE;
        RAMIntDataAccess access = new RAMIntDataAccess("", "", false, -1);
        access.create(1000);
        access.setInt(0, nodeA << 1 | 1 & PrepareEncoder.getScFwdDir());
        assertTrue(access.getInt(0) < 0);
        assertEquals(Integer.MAX_VALUE, access.getInt(0) >>> 1);
    }

    /**
     * Intention: Vérifier que la méthode toDetailsString() retourne une chaîne
     * contenant le nombre de nœuds et de raccourcis créés dans le CHStorage.
     *  Données: Création d'un stockage avec 5 nœuds et 10 raccourcis attendus.
     *  Oracle: La chaîne doit contenir "shortcuts:0" et "nodesCH:5" car
     * aucun raccourci n’a encore été ajouté mais 5 nœuds ont été créés.
     */
    @Test
    void testToDetailsString() {
        CHStorage storage = new CHStorage(new RAMDirectory(), "test", 1024, false);
        storage.create(5, 10); // 5 nœuds, capacité pour 10 raccourcis

        String details = storage.toDetailsString();

        // Vérification de la présence des parties essentielles
        assertTrue(details.contains("shortcuts:0"),
                "La sortie doit indiquer qu'il n'y a pas encore de raccourcis");
        assertTrue(details.contains("nodesCH:5"),
                "La sortie doit indiquer qu'il y a 5 nœuds");
    }

    /**
     *  Intention : Vérifier que debugPrint() affiche bien les sections "nodesCH:" et "shortcuts:".
     *  Données : Création d’un CHStorage avec 3 nœuds et 0 raccourci.
     * Oracle : La sortie doit contenir les entêtes des deux tableaux.
     */
    @Test
    void testDebugPrintOutput() {
        // Préparer un flux pour capturer System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            CHStorage storage = new CHStorage(new RAMDirectory(), "test", 1024, false);
            storage.create(3, 5);

            // Appel de la méthode à tester
            storage.debugPrint();

            String output = outContent.toString();

            // Vérifications essentielles
            assertTrue(output.contains("nodesCH:"), "La sortie doit contenir la section nodesCH");
            assertTrue(output.contains("shortcuts:"), "La sortie doit contenir la section shortcuts");
            assertTrue(output.contains("N_LAST_SC"), "L'entête des nœuds doit être présent");
            assertTrue(output.contains("S_WEIGHT"), "L'entête des raccourcis doit être présent");

        } finally {
            // Restaurer System.out
            System.setOut(originalOut);
        }
    }

}
