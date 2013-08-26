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
public class TunnuksetRistienMukaanJarjestykseen implements Comparator<Tunnus> {
    /**
     * Vertaillaan tunnuksia ristillä pelattujen pelien lukumäärän perusteella
     * @param t
     * @param t1
     * @return kumpi on pelannut enemmän pelejä
     */
    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return t.getRistillaPelatutPelit() - t1.getRistillaPelatutPelit();
    }
    
}