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

/**
 *
 * @author Aapo
 */
public class TunnusTest {
    private Tunnus tunnus;
    
    public TunnusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tunnus = new Tunnus("testi");
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
    public void laskeekoPelienPituudenOikein() {
        tunnus.syotaPelinPituus(3.5);
        tunnus.syotaPelinPituus(8.0);
        assertEquals("pelien lukumaaran olisi pitanyt olla 2", 2, tunnus.getPelatutPelit());
        assertEquals("pituuksien keskiarvo meni vaarin", (11.5/2), tunnus.getPelienKeskimaarainenPituus(), 0.01);
    }
}
