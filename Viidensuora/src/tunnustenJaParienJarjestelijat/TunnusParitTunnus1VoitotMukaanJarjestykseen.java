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
public class TunnusParitTunnus1VoitotMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnusparien ensimmäisten tunnusten voittoja
     * @param t
     * @param t1
     * @return tunnusparien ensimmäisten tunnusten voittojen mukaan järjestykseen
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus1nVoitot() - t1.getTunnus1nVoitot();
    }
    
}
