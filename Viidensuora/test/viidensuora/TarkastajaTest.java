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
public class TarkastajaTest {

    private RistiNollaMuistio ruudukko;
    private Tarkastaja viisiRistia;

    public TarkastajaTest() {
        ArrayList<Laatu> ristiLista = new ArrayList<Laatu>();
        for (int i = 0; i < 5; i++) {
            ristiLista.add(Laatu.RISTI);
        }
        viisiRistia = new Tarkastaja(ristiLista);
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
            assertEquals(0, loydetyt.size());

            ruudukko.lisaaRisti(0, i);
            ruudukko.lisaaNolla(i + 1, 0);
        }

        ArrayList<Merkki> loydetyt = viisiRistia.tarkasta(ruudukko.getMerkit());
        assertEquals(2, loydetyt.size());
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
}
