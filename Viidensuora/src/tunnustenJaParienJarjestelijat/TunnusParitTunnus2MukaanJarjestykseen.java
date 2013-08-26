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
public class TunnusParitTunnus2MukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnusparien toisten tunnusten nimiä
     * @param t
     * @param t1
     * @return tunnusparien toisten tunnusten voitot
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus2().getTunnus().compareToIgnoreCase(t1.getTunnus2().getTunnus());
    }
    
}
