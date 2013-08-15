/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnustenJaParienJarjestelijat;

/**
 *
 * @author Aapo
 */
import java.util.Comparator;
import tilastotJaTunnukset.*;

public class TunnuksetPelipituudenMukaanJarjestykseen implements Comparator<Tunnus> {

    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return (int) ((t.getPelienKeskimaarainenPituus() - t1.getPelienKeskimaarainenPituus())*10);
    }
    
}
