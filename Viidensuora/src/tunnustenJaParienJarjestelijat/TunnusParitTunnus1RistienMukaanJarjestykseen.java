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
public class TunnusParitTunnus1RistienMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnusparien ensimmäisten tunnusten ristillä pelattujen pelien määrää
     * @param t
     * @param t1
     * @return tunnusparien ensimmäisten tunnusten järjestys ristipelien mukaan
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus1nRistiPelit() - t1.getTunnus1nRistiPelit();
    }
    
}
