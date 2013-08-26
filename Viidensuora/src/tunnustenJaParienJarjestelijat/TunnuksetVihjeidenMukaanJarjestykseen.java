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
public class TunnuksetVihjeidenMukaanJarjestykseen implements Comparator<Tunnus> {
    /**
     * Vertaillaan tunnuksia käytettyjen vihjekertojen perusteella
     * @param t
     * @param t1
     * @return kumpi tunnuksista on leikkinyt enemmän vihjenapilla
     */
    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return t.getVihjenapinKaytot() - t1.getVihjenapinKaytot();
    }
    
}
