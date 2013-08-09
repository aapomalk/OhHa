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

public class TiedostoonKirjoittaja {
    private FileWriter kirjoittaja;
    
    public void kirjoitaTiedostoon(ArrayList<String> tekstit, String tiedostoNimi, VirheidenKasittelija kasittelija) {
        try {
            kirjoittaja = new FileWriter("pelit_ja_tilastot/" + tiedostoNimi);
            for (String string : tekstit) {
                kirjoittaja.write(string + "\n");
            }
            kirjoittaja.close();
        } catch (Exception e) {
            kasittelija.virheTiedostoonKirjoittamisessa();
        }
    }
    
}
