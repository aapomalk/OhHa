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
public class TunnuksetPelipituudenMukaanJarjestykseen implements Comparator<Tunnus> {
    /**
     * Vertaillaan tunnuksia pelien pituuksien keskiarvon perusteella
     * @param t
     * @param t1
     * @return kummalla tunnuksista on pidemmät pelit
     */
    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return (int) ((t.getPelienKeskimaarainenPituus() - t1.getPelienKeskimaarainenPituus())*10);
    }
    
}
