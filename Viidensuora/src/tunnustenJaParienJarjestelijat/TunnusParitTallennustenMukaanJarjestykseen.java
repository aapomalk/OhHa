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
public class TunnusParitTallennustenMukaanJarjestykseen implements Comparator<TunnusPari> {
    /**
     * Vertaillaan tunnuspareja niiden tekemien tallennusten mukaan
     * @param t
     * @param t1
     * @return kumpi tunnuspareista on ollut ahkerampi tallentamaan
     */
    @Override
    public int compare(TunnusPari t, TunnusPari t1) {
        return t.getTallennustenLukumaara() - t1.getTallennustenLukumaara();
    }
    
}