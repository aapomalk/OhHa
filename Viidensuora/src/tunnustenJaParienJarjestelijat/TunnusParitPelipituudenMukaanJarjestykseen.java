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
public class TunnusParitPelipituudenMukaanJarjestykseen implements Comparator<TunnusPari> {

    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return (int) ((t.getPelienKeskimaarainenPituus() - t1.getPelienKeskimaarainenPituus())*10);
    }
    
}