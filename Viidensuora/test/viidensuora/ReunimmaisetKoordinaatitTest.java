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
 * @author aapomalk
 */
public class ReunimmaisetKoordinaatitTest {
    private RistiNollaMuistio ruudukko;
    private ReunimmaisetKoordinaatit rajaaja;
    
    public ReunimmaisetKoordinaatitTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ruudukko = new RistiNollaMuistio();
        rajaaja = new ReunimmaisetKoordinaatit();
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
    public void loytyykoReunimmaisetKoordinaatit() {
        int pieninX = -5;
        int suurinX = 12;
        int pieninY = -8;
        int suurinY = 3;
        
        ruudukko.lisaaRisti(pieninX, suurinY);
        ruudukko.lisaaNolla(suurinX, pieninY);
        
        rajaaja.etsiKoordinaatit(ruudukko.getMerkit());
        
        assertEquals("pieninX", pieninX, rajaaja.getPieninX());
        assertEquals("suurinX", suurinX, rajaaja.getSuurinX());
        assertEquals("pieninY", pieninY, rajaaja.getPieninY());
        assertEquals("suurinY", suurinY, rajaaja.getSuurinY());
    }
}
