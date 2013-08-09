/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import viidensuora.RistiNollaMuistio;

/**
 *
 * @author Aapo
 */
public class PelitilanteenTallentajaTest {
    private PelitilanteenTallentaja kirjoittaja;
    private VirheidenKasittelija kasittelija;
    private RistiNollaMuistio muistio;
    
    public PelitilanteenTallentajaTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        muistio = new RistiNollaMuistio();
        kasittelija = new VirheidenKasittelijaTeksti("tyhja");
        kirjoittaja = new PelitilanteenTallentaja();
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
    public void onnistuukoTiedostoonKirjoittaminen() {
        muistio.lisaaRisti(0, 0);
        muistio.lisaaNolla(1, 1);
        muistio.lisaaRisti(-1, -1);
        muistio.lisaaNolla(2, 0);
        kirjoittaja.tallennaPelitilanne(muistio.getMerkit(), "Testi.txt", kasittelija);
        System.out.println("Tarkista Testi.txt -tiedosto kasin, toistaiseksi");
        assertEquals(0, 0);
    }
}
