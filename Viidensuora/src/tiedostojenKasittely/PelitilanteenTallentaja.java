/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import java.util.ArrayList;
import tilastotJaTunnukset.Tunnus;
import viidensuora.Merkki;

/**
 * Tiedostoonkirjoittajaa hyväksikäyttäen tallentaa pelitilanteen tekstitiedostoon
 * @see TiedostoonKirjoittaja
 * @author Aapo
 */
public class PelitilanteenTallentaja {
    /**
     * @see PelitilanteenTallentaja
     * @param merkit käyttäjän syöttämät merkit, jotka tullaan tallentamaan tiedostoon
     * @param tiedosto käyttäjän syöttämä tiedoston nimi, johon aiotaan tallentaa
     * @param kasittelija käyttäjän syöttämä virheidenkäsittelijä
     */
    public void tallennaPelitilanne(ArrayList<Merkki> merkit, String tiedosto, VirheidenKasittelija kasittelija, Tunnus ristiTunnus) {
        if (merkit.isEmpty()) {
            return;
        }
        ArrayList<String> merkitKasitelty = new ArrayList<String>();
        merkitKasitelty.add(valikommentti(ristiTunnus));
        for (Merkki merkki : merkit) {
            merkitKasitelty.add(merkki.getLaatu() + " " + merkki.getX() + " " + merkki.getY() + " ");
        }
        //mahdollisesti salausluokan kaytto tahan valiin
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        kirjoittaja.kirjoitaTiedostoon(merkitKasitelty, tiedosto, kasittelija);
    }
    
    private String valikommentti(Tunnus tunnus) {
        if (tunnus == null) {
            return "Pikapeli";
        }
        return tunnus.getTunnus();
    }
    
    public void tallennaPelitilanne(ArrayList<Merkki> merkit, String tiedosto, VirheidenKasittelija kasittelija) {
        this.tallennaPelitilanne(merkit, tiedosto, kasittelija, null);
    }
}
