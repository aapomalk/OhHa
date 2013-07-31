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
        
        assertEquals(1, ruudukko.ristienMaara());
        
        ruudukko.lisaaNolla(2, 2);
        ruudukko.lisaaNolla(-1, -1);
        
        assertEquals(1, ruudukko.nollienMaara());
    }
    
    @Test
    public void lisaaKaksiMerkkiaSamaanPaikkaan() {
        ruudukko.lisaaRisti(0, 0);
        ruudukko.lisaaNolla(0, 0);
        
        assertEquals(0, ruudukko.nollienMaara());
    }
    
    @Test
    public void merkkienMaaraTest() {
        ruudukko.lisaaRisti(0, 0);
        assertEquals(1, ruudukko.ristienMaara());
        
        ruudukko.lisaaNolla(1, 1);
        assertEquals(1, ruudukko.nollienMaara());
    }
    
    
}
