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
public class TunnusParitTunnus2VoitotMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan Tunnusparien toisten tunnusten voittoja
     * @param t ensimmäinen verrattava tunnuspari
     * @param t1 toinen verrattava tunnuspari
     * @return toisten tunnusten voitot järjestykseen
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus2nVoitot() - t1.getTunnus2nVoitot();
    }
    
}
