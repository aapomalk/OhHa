/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
import java.util.ArrayList;
import viidensuora.Merkki;

public class PelitilanteenTallentaja {
    
    public void tallennaPelitilanne(ArrayList<Merkki> merkit, String tiedosto, VirheidenKasittelija kasittelija) {
        if (merkit.isEmpty()) {
            return;
        }
        ArrayList<String> merkitKasitelty = new ArrayList<String>();
        for (Merkki merkki : merkit) {
            merkitKasitelty.add(merkki.getLaatu() + " " + merkki.getX() + " " + merkki.getY() + " ");
        }
        //mahdollisesti salausluokan kaytto tahan valiin
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        kirjoittaja.kirjoitaTiedostoon(merkitKasitelty, tiedosto, kasittelija);
    }
}
