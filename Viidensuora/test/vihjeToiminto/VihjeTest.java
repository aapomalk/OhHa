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
import viidensuora.RistiNollaMuistio;
import viidensuora.Laatu;

/**
 *
 * @author aapomalk
 */
public class VihjeTest {
    private Vihje vihje;
    private RistiNollaMuistio ruudukko;
    
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
        ruudukko = new RistiNollaMuistio();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void kokeillaan() {
         
         ruudukko.lisaaRisti(0, 0);
         ruudukko.lisaaNolla(1, 1);
         ruudukko.lisaaRisti(1, 0);
         ruudukko.lisaaNolla(2, 0);
         ruudukko.lisaaRisti(0, 2);
         ruudukko.lisaaNolla(0, 1);
         ruudukko.lisaaRisti(-1, 1);
         ruudukko.lisaaNolla(2, 1);
         ruudukko.lisaaRisti(-2, 0);
         ruudukko.lisaaNolla(1, 3);
         ruudukko.lisaaRisti(2, 2);
         ruudukko.lisaaNolla(1, 2);
         ruudukko.lisaaRisti(-2, 2);
         ruudukko.lisaaNolla(1, -1);
         ruudukko.lisaaRisti(3, 1);
         ruudukko.lisaaNolla(1, 4);
         ruudukko.lisaaRisti(-2, 1);
         
         System.out.println("tarkasta seuraavat tulokset kasin: (nollalle)");
         vihje.lisaaVihjeetListoihin(ruudukko.getMerkit(), Laatu.NOLLA);
         System.out.println("sininen1: " + vihje.getEhkaHyodyllinen());
         System.out.println("turkoosi: " + vihje.getRakennaHyokkays());
         System.out.println("vihrea: " + vihje.getVarmaVoitto());
         
         System.out.println("sininen2: " + vihje.getHairitseVastustajaa());
         System.out.println("violetti: " + vihje.getVaroVastustajaa());
         System.out.println("punainen: " + vihje.getTaytyyEstaa());
         
         System.out.println("\nseuraavat vastaavasti ristille:");
         vihje.lisaaVihjeetListoihin(ruudukko.getMerkit(), Laatu.RISTI);
         System.out.println("sininen1: " + vihje.getEhkaHyodyllinen());
         System.out.println("turkoosi: " + vihje.getRakennaHyokkays());
         System.out.println("vihrea: " + vihje.getVarmaVoitto());
         
         System.out.println("sininen2: " + vihje.getHairitseVastustajaa());
         System.out.println("violetti: " + vihje.getVaroVastustajaa());
         System.out.println("punainen: " + vihje.getTaytyyEstaa());
     }
    
}
