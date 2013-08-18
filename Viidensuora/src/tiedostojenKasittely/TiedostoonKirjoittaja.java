/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Lukee tiedostoja kansiosta pelit_ja_tilastot/. Virheidenkäsittelijälle
 * välitetään tapahtuneet virheet
 * @see VirheidenKasittelija
 * @author Aapo
 */
public class TiedostoonKirjoittaja {
    private FileWriter kirjoittaja;
    /**
     * @see TiedostoonKirjoittaja
     * @param tekstit tiedostoon kirjoitettavat tekstirivit listana
     * @param tiedostoNimi käyttäjän syöttämä tiedostonnimi, johon rivit kirjoitetaan
     * @param kasittelija käyttäjän syöttämä virheidenkäsittelijä
     */
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
