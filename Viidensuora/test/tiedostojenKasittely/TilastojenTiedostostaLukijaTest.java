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
public class TilastojenTiedostostaLukijaTest {
    private TilastoTunnusMuistio muistio;
    private VirheidenKasittelija kasittelija;
    private TilastojenJaTunnustenTiedostoonTallentaja tallentaja;
    private TilastojenTiedostostaLukija lukija;
    
    public TilastojenTiedostostaLukijaTest() {
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
        kasittelija = new VirheidenKasittelija("TekstiKayttoliittyma");
        tallentaja = new TilastojenJaTunnustenTiedostoonTallentaja();
        lukija = new TilastojenTiedostostaLukija();
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
        System.out.println("Vertaile Testi4.txt ja Testi5.txt -tiedostoja kasin");
    }
}
