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
public class TunnusParitTunnus2RistienMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnusparien toisten tunnusten ristipelien määrää
     * @param t
     * @param t1
     * @return toisten tunnusten ristipelit
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus2nRistiPelit() - t1.getTunnus2nRistiPelit();
    }
    
}