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
public class PelitilanteenLukijaTest {
    private PelitilanteenTallentaja kirjoittaja;
    private VirheidenKasittelija kasittelija;
    private RistiNollaMuistio muistio;
    private PelitilanteenLukija lukija;
    
    public PelitilanteenLukijaTest() {
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
        kasittelija = new VirheidenKasittelijaTeksti();
        kirjoittaja = new PelitilanteenTallentaja();
        lukija = new PelitilanteenLukija();
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
    public void kokeillaanLukeaTiedostosta() {
        muistio.lisaaRisti(0, 0);
        muistio.lisaaNolla(1, 1);
        kirjoittaja.tallennaPelitilanne(muistio.getMerkit(), "Testi2.txt", kasittelija);
        muistio.lisaaRisti(2, 2);
        assertEquals("risteja olisi pitanyt olla 2", 2, muistio.ristienMaara());
        lukija.lataaPelitilanne("Testi2.txt", kasittelija, muistio);
        
        assertEquals("risteja olisi pitanyt olla 1", 1, muistio.ristienMaara());
    }
    
    @Test
    public void tyhjentyykoMuistio() {
        muistio.lisaaRisti(0, 0);
        lukija.tyhjennaMuistio(muistio);
        assertEquals("muistion olisi pitanyt olla tyhja", 0, muistio.ristienMaara());
    }
}
