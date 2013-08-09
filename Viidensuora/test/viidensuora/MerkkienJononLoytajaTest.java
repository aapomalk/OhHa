/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 *
 * @author Aapo
 */
public class MerkkienJononLoytajaTest {

    private RistiNollaMuistio ruudukko;
    private MerkkienJononLoytaja loytaja;
    private ArrayList<Laatu> ristiLista;

    public MerkkienJononLoytajaTest() {
        ristiLista = new ArrayList<Laatu>();
        for (int i = 0; i < 5; i++) {
            ristiLista.add(Laatu.RISTI);
        }
        loytaja = new MerkkienJononLoytaja();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ruudukko = new RistiNollaMuistio();
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void loytyykoViisiRistia() {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                boolean loytyiko = loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista);
                assertTrue("ei pitanyt viela loytya", !loytyiko);
            }

            ruudukko.lisaaRisti(0, i);
            if (i < 4) {
                ruudukko.lisaaNolla(i + 1, 0);
            }
        }

        assertTrue("nyt olisi pitanyt loytya", loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista));
    }

    @Test
    public void toimiikoTarkastaViimeinenMetodi() {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                assertTrue("ei pitanyt viela loytya", !loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista));
            }

            ruudukko.lisaaRisti(0, i);
            if (i < 4) {
                ruudukko.lisaaNolla(i + 1, 0);
            }
        }

        assertTrue("nyt olisi pitanyt loytya", loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista));
    }

    @Test
    public void loytyykoViisiRistiaVinottain() {
        for (int i = 0; i < 5; i++) {
            ruudukko.lisaaRisti(i, i);

            if (i < 4) {
                ruudukko.lisaaNolla(i + 1, i);
            }
        }

        assertTrue(loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista));
    }

    @Test
    public void loytyykoViisiRistiaJosValeja() {
        for (int i = 0; i < 5; i++) {
            ruudukko.lisaaRisti(2 * i, 2 * i);

            if (i < 4) {
                ruudukko.lisaaNolla(i + 1, i);
            }
        }

        assertTrue(!loytaja.tarkastaViimeinen(ruudukko.getMerkit(), ristiLista));
    }
    
    @Test
    public void toimiikoEtsiminenJaetullaListalla() {
        for (int i = 0; i < 10; i++) {
            ruudukko.lisaaRisti(i, i);
            ruudukko.lisaaNolla(i+1, 0);
        }
        
        ArrayList<Merkki> loydetyt = loytaja.tarkasta(ruudukko.getMerkit(), ristiLista, ristiLista);
        assertEquals("loydettyja olisi pitanyt olla 2", 2, loydetyt.size());
    }
    
    @Test
    public void testataanTarkastaMerkkiMetodia() {
        for (int i = 0; i < 10; i++) {
            ruudukko.lisaaRisti(i, i);
            ruudukko.lisaaNolla(i+1, 0);
        }
        
        for (int i = 0; i < ruudukko.getMerkit().size(); i+=2) {
            if (i == 8 || i == 10) {
                assertTrue("nyt olisi pitanyt toimia", loytaja.tarkastaMerkki(ruudukko.getMerkit(), ruudukko.getMerkit().get(i), ristiLista, ristiLista));
            } else {
                assertFalse("nyt ei olisi pitanyt loytya", loytaja.tarkastaMerkki(ruudukko.getMerkit(), ruudukko.getMerkit().get(i), ristiLista, ristiLista));
            }
        }
    }
}
