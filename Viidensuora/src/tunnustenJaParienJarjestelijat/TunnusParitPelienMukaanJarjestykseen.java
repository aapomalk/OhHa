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
public class TunnusParitPelienMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnuspareja keskenään pelien lukumäärän perusteella
     * @param t
     * @param t1
     * @return kummalla tunnusparilla on enemmän pelattuja pelejä
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getPelatutPelit() - t1.getPelatutPelit();
    }
    
}
