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
    private MerkkienJononLoytaja viisiRistia;

    public MerkkienJononLoytajaTest() {
        ArrayList<Laatu> ristiLista = new ArrayList<Laatu>();
        for (int i = 0; i < 5; i++) {
            ristiLista.add(Laatu.RISTI);
        }
        viisiRistia = new MerkkienJononLoytaja(ristiLista);
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
            ArrayList<Merkki> loydetyt = viisiRistia.tarkasta(ruudukko.getMerkit());
            assertEquals("ei pitanyt viela loytya", 0, loydetyt.size());

            ruudukko.lisaaRisti(0, i);
            ruudukko.lisaaNolla(i + 1, 0);
        }

        ArrayList<Merkki> loydetyt = viisiRistia.tarkasta(ruudukko.getMerkit());
        assertEquals("nyt olisi pitanyt loytya", 2, loydetyt.size());
    }
    
    @Test
    public void loytyykoViisiRistiaVinottain() {
        for (int i = 0; i < 5; i++) {
            ruudukko.lisaaRisti(i, i);
            ruudukko.lisaaNolla(i+1, i);
        }
        
        ArrayList<Merkki> loydetyt = viisiRistia.tarkasta(ruudukko.getMerkit());
        assertEquals(2, loydetyt.size());
    }
    
    @Test
    public void loytyykoViisiRistiaJosValeja() {
        for (int i = 0; i < 5; i++) {
            ruudukko.lisaaRisti(2*i, 2*i);
            ruudukko.lisaaNolla(i+1, i);
        }
        
        ArrayList<Merkki> loydetyt = viisiRistia.tarkasta(ruudukko.getMerkit());
        assertEquals(0, loydetyt.size());
    }
    
    @Test
    public void toimiikoViereistenKoordinaattienSuunnistaminen() {
        assertEquals("suunta 0, x", 0, viisiRistia.suunnistaViereinenX(0, viisiRistia.muutaNumeroSuunnaksi(0)));
        for (int i = 1; i < 4; i++) {
            assertEquals("suunta 1-3, x", 1, viisiRistia.suunnistaViereinenX(0, viisiRistia.muutaNumeroSuunnaksi(i)));
        }
        assertEquals("suunta 4, x", 0, viisiRistia.suunnistaViereinenX(0, viisiRistia.muutaNumeroSuunnaksi(4)));
        for (int i = 5; i < 8; i++) {
            assertEquals("suunta 5-7, x", -1, viisiRistia.suunnistaViereinenX(0, viisiRistia.muutaNumeroSuunnaksi(i)));
        }
        
        assertEquals("suunta 7, y", -1, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(7)));
        assertEquals("suunta 0, y", -1, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(0)));
        assertEquals("suunta 1, y", -1, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(1)));
        assertEquals("suunta 2, y", 0, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(2)));
        for (int i = 3; i < 6; i++) {
            assertEquals("suunta 3-5, y", 1, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(i)));
        }
        assertEquals("suunta 6, y", 0, viisiRistia.suunnistaViereinenY(0, viisiRistia.muutaNumeroSuunnaksi(6)));
    }
}
