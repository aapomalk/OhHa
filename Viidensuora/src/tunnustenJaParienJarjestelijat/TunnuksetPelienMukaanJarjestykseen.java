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
public class TunnuksetPelienMukaanJarjestykseen implements Comparator<Tunnus> {
    /**
     * Vertaillaan tunnuksia pelien lukumäärän perusteella
     * @param t
     * @param t1
     * @return palauttaa tiedon int -muodossa, kumpi tunnuksista on edella
     */
    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return t.getPelatutPelit() - t1.getPelatutPelit();
    }
    
}
