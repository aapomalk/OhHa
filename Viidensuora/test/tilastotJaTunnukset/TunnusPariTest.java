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
public class TunnusPariTest {
    private TunnusPari pari;
    private Tunnus tunnus1;
    private Tunnus tunnus2;
    
    public TunnusPariTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tunnus1 = new Tunnus("testi1");
        tunnus2 = new Tunnus("testi2");
        pari = new TunnusPari(tunnus1, tunnus2);
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
    public void pelienLisays() {
        pari.kerroKumpiVoitti(tunnus1, Laatu.RISTI, 8.5);
        assertEquals("voittoja olisi pitanyt olla 1", 1, tunnus1.getVoitot());
        pari.kerroKumpiVoitti(tunnus2, Laatu.NOLLA, 10.0);
        
        assertEquals("pelattuja peleja olisi pitanyt olla 2", 2, pari.getPelatutPelit());
        assertEquals("voittoja olisi pitanyt olla vain 1", 1, pari.getTunnus1nVoitot());
        assertEquals("ristipeleja tuli olla 2", 2, pari.getTunnus1nRistiPelit());
        assertEquals("voittoja vain 1", 1, pari.getTunnus2nVoitot());
        assertEquals("ristipeleja piti olla 0", 0, pari.getTunnus2nRistiPelit());
        assertEquals("pelien keskimaarainen pituus meni vaarin", (18.5/2), pari.getPelienKeskimaarainenPituus(), 0.01);
        
        assertEquals("pelattuja peleja olisi pitanyt olla 2", 2, tunnus2.getPelatutPelit());
        assertEquals("voitto piti valittya tunnukselle", 1, tunnus2.getVoitot());
        assertEquals("tunnuksen ristipeleja piti olla 0", 0, tunnus2.getRistillaPelatutPelit());
        assertEquals("keskimaaraiset pituudet piti olla sama", pari.getPelienKeskimaarainenPituus(), tunnus2.getPelienKeskimaarainenPituus(), 0.01);
        
        assertEquals("pelattuja peleja olisi pitanyt olla 2", 2, tunnus1.getPelatutPelit());
        assertEquals("voitto piti valittya tunnukselle", 1, tunnus1.getVoitot());
        assertEquals("tunnuksen ristipeleja piti olla 2", 2, tunnus1.getRistillaPelatutPelit());
        assertEquals("keskimaaraiset pituudet piti olla sama", pari.getPelienKeskimaarainenPituus(), tunnus1.getPelienKeskimaarainenPituus(), 0.01);
    }
}
