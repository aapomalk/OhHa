/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viidensuora;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aapo
 */
public class RistiNollaMuistioTest {

    private RistiNollaMuistio ruudukko;
    
    public RistiNollaMuistioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ruudukko = new RistiNollaMuistio();
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
    public void lisaaKaksiSamaaMerkkiaPerakkainMaara1() {
        ruudukko.lisaaRisti(0, 0);
        ruudukko.lisaaRisti(1, 1);
        
        assertEquals("ristien maara ei tasmaa", 1, ruudukko.ristienMaara());
        
        ruudukko.lisaaNolla(2, 2);
        ruudukko.lisaaNolla(-1, -1);
        
        assertEquals("nollien maara ei tasmaa", 1, ruudukko.nollienMaara());
    }
    
    @Test
    public void lisaaKaksiMerkkiaSamaanPaikkaan() {
        ruudukko.lisaaRisti(0, 0);
        ruudukko.lisaaNolla(0, 0);
        
        assertEquals(0, ruudukko.nollienMaara());
    }
    
    @Test
    public void merkkienMaaraTest() {
        for (int i = 0; i < 10; i++) {
            ruudukko.lisaaRisti(i, 0);
            assertEquals(i+1, ruudukko.ristienMaara());
            
            ruudukko.lisaaNolla(i+1, 1);
            assertEquals(i+1, ruudukko.nollienMaara());
        }
        
    }
    
    @Test
    public void voikoMuistionTyhjentaa() {
        ruudukko.lisaaRisti(0, 0);
        ArrayList<Merkki> tyhjennettava = ruudukko.getMerkit();
        tyhjennettava.clear();
        assertEquals(1, ruudukko.ristienMaara());
    }
    
    @Test
    public void onnistuukoSiirronPeruminenOsa1() {
        ruudukko.lisaaRisti(0, 0);
        assertEquals("nyt olisi pitanyt olla 1 risti", 1, ruudukko.ristienMaara());
        ruudukko.peruSiirto();
        
        assertEquals("nyt olisi pitanyt olla 0 ristia", 0, ruudukko.ristienMaara());
        assertEquals("edellinenMerkkiRisti olisi pitanyt palautua falseksi", false, ruudukko.getEdellinenMerkkiRisti());
    }
    
    @Test
    public void onnistuukoSiirronPeruminenOsa2() {
        ruudukko.lisaaRisti(0, 0);
        ruudukko.lisaaNolla(1, 1);
        assertEquals("nyt olisi pitanyt olla 1 nolla", 1, ruudukko.nollienMaara());
        ruudukko.peruSiirto();
        
        assertEquals("nyt olisi pitanyt olla 0 nollaa", 0, ruudukko.nollienMaara());
        assertEquals("edellinenMerkkiRisti olisi pitanyt palautua trueksi", true, ruudukko.getEdellinenMerkkiRisti());
    }
}
