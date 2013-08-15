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

public class TunnuksetRistienMukaanJarjestykseen implements Comparator<Tunnus> {

    @Override
    public int compare(Tunnus t, Tunnus t1) {
        return t.getRistillaPelatutPelit() - t1.getRistillaPelatutPelit();
    }
    
}