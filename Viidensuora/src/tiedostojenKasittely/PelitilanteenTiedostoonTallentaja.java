/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
import java.io.FileWriter;
import java.util.ArrayList;
import viidensuora.Merkki;

public class PelitilanteenTiedostoonTallentaja {
    private FileWriter kirjoittaja;
    
    public void tallennaPelitilanne(ArrayList<Merkki> merkit, String tiedosto, VirheidenKasittelija kasittelija) {
        try {
            kirjoittaja = new FileWriter(tiedosto);
            kasitteleLista(merkit, kasittelija);
            kirjoittaja.close();
        } catch (Exception e) {
            kasittelija.virheFileWriterinLuomisessa();
        }
        
    }
    
    private void kasitteleLista(ArrayList<Merkki> merkit, VirheidenKasittelija kasittelija) {
        if (merkit.isEmpty()) {
            return;
        }
        ArrayList<String> merkitKasitelty = new ArrayList<String>();
        for (Merkki merkki : merkit) {
            merkitKasitelty.add(merkki.getLaatu() + " " + merkki.getX() + " " + merkki.getY() + " ");
        }
        //mahdollisesti salausluokan kaytto tahan valiin
        for (String string : merkitKasitelty) {
            try {
                kirjoittaja.write(string + "\n");
            } catch (Exception e) {
                kasittelija.virheTiedostoonKirjoittamisessa();
            }
        }
    }
}
