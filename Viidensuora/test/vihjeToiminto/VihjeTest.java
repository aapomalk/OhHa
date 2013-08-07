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

/**
 *
 * @author aapomalk
 */
public class VihjeTest {
    private Vihje vihje;
    
    public VihjeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vihje = new Vihje();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         System.out.println("Muista testata Vihje -luokkaa!");
     }
    
}
