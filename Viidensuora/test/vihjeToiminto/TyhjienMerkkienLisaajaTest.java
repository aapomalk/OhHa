/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vihjeToiminto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import viidensuora.Merkki;
import viidensuora.Laatu;

/**
 *
 * @author aapomalk
 */
public class TyhjienMerkkienLisaajaTest {
    private TyhjienMerkkienLisaaja lisaaja;
    private ArrayList<Merkki> merkkeja;
    
    public TyhjienMerkkienLisaajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lisaaja = new TyhjienMerkkienLisaaja();
        merkkeja = new ArrayList<Merkki>();
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
    public void lisaakoTyhjatYmparille() {
        merkkeja.add(new Merkki(0, 0, Laatu.RISTI));
        lisaaja.lisaaTyhjatMerkit(merkkeja);
        
        assertEquals(9, merkkeja.size());
    }
    
    @Test
    public void lisaakoTyhjiaPaallekkain() {
        merkkeja.add(new Merkki(0, 0, Laatu.RISTI));
        merkkeja.add(new Merkki(1, 1, Laatu.RISTI));
        lisaaja.lisaaTyhjatMerkit(merkkeja);
        
        assertEquals(14, merkkeja.size());
    }
}
