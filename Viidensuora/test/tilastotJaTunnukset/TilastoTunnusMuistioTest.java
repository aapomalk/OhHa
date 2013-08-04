/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tilastotJaTunnukset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.Laatu;

/**
 *
 * @author Aapo
 */
public class TilastoTunnusMuistioTest {
    private TilastoTunnusMuistio muistio;
    private String tunnusNimi1;
    private String tunnusNimi2;
    
    public TilastoTunnusMuistioTest() {
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
        tunnusNimi1 = "testi1";
        tunnusNimi2 = "testi2";
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
    public void rakennetaanTilastoTunnusMuistio() {
        muistio.lisaaTunnus(tunnusNimi1);
        muistio.lisaaTunnus(tunnusNimi2);
        muistio.lisaaTunnusPari(muistio.getTunnukset().get(0), muistio.getTunnukset().get(1));
        
        muistio.peliTallennettu(muistio.getTunnusParit().get(0));
        muistio.peliPelattu(8.5, Laatu.NOLLA, muistio.getTunnukset().get(0), muistio.getTunnusParit().get(0));
        muistio.peliPelattu(10.0, Laatu.RISTI, muistio.getTunnukset().get(1), muistio.getTunnusParit().get(0));
        
        assertEquals("pelien maaran piti olla 2", 2, muistio.getPelienMaara());
        assertEquals("ristin voittoja piti olla 1", 1, muistio.getRistienVoitot());
        assertEquals("nollan voittoja piti olla 1", 1, muistio.getNollienVoitot());
        assertEquals("pelien keskimaarainen pituus meni vaarin", (18.5/2), muistio.getPelienKeskimaarainenPituus(), 0.01);
        assertEquals("peleja tallennettiin kerran", 1, muistio.getPelienTallennustenMaara());
        
        assertEquals("tunnusparilla piti olla 2 pelia", 2, muistio.getTunnusParit().get(0).getPelatutPelit());
    }
}
