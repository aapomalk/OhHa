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

import tilastotJaTunnukset.*;
import viidensuora.Laatu;

/**
 *
 * @author Aapo
 */
public class TilastojenLukijaTest {
    private TilastoTunnusMuistio muistio;
    private VirheidenKasittelija kasittelija;
    private TilastojenJaTunnustenTallentaja tallentaja;
    private TilastojenLukija lukija;
    
    public TilastojenLukijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        muistio = new TilastoTunnusMuistio();
        kasittelija = new VirheidenKasittelijaTeksti();
        tallentaja = new TilastojenJaTunnustenTallentaja();
        lukija = new TilastojenLukija();
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
    public void kokeillaanTallentaaTiedostoon() {
        muistio.lisaaTunnus("testi1");
        muistio.lisaaTunnus("testi2");
        muistio.lisaaTunnus("testi3");
        muistio.lisaaTunnusPari(muistio.getTunnukset().get(0), muistio.getTunnukset().get(1));
        muistio.lisaaTunnusPari(muistio.getTunnukset().get(2), muistio.getTunnukset().get(1));
        muistio.peliPelattu(8.5, Laatu.NOLLA, muistio.getTunnukset().get(0), muistio.getTunnusParit().get(0));
        
        tallentaja.tallennaTiedostoon(muistio, "Testi4.txt", kasittelija);
        
        TilastoTunnusMuistio muistio2 = lukija.lataaTilastot("Testi4.txt", kasittelija);
        tallentaja.tallennaTiedostoon(muistio2, "Testi5.txt", kasittelija);
        System.out.println("Tarkista Testi4.txt:t ja Testi5.txt:t kasin");
//        tallentaja.tallennaTiedostoon(muistio2, "Testi5.txt", kasittelija);
//        System.out.println("Vertaile Testi4.txt ja Testi5.txt -tiedostoja kasin");
        assertEquals("tilastojen olisi pitanyt olla samat", muistio.getNollienVoitot(), muistio2.getNollienVoitot());
        assertEquals("myos ristien voitot olisi pitanyt olla samat", muistio.getRistienVoitot(), muistio2. getRistienVoitot());
        assertEquals("tunnusparien olisi pitanyt olla samat", muistio.getTunnusParit().get(0).getTunnus1().getTunnus(), 
                muistio2.getTunnusParit().get(0).getTunnus1().getTunnus());
    }
}
