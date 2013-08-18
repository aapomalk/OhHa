/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnustenJaParienJarjestelijat;

import java.util.Comparator;
import tilastotJaTunnukset.*;

/**
 * Nimi kertoo kaiken
 * @author Aapo
 */
public class TunnuksetVoittojenMukaanJarjestykseen implements Comparator<Tunnus> {
    /**
     * Vertaillaan tunnuksia voittojen perusteella
     * @param t
     * @param t1
     * @return 
     */
    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return t.getVoitot() - t1.getVoitot();
    }
    
}
