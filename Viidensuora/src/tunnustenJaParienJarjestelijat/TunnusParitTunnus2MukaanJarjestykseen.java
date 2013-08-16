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

public class TunnusParitTunnus2MukaanJarjestykseen implements Comparator<TunnusPari> {

    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTunnus2().getTunnus().compareToIgnoreCase(t1.getTunnus2().getTunnus());
    }
    
}
