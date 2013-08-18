/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Yksinkertaisuudessaan lukee tiedostoja kansiosta pelit_ja_tilastot/
 * . Virheidenkäsittelijä käsittelee mahdollisen virhetilanteen joko graafisesti
 * tai tekstimuodossa, riippuen käytettävästä käyttöliittymästä
 * @see VirheidenKasittelija
 * @author Aapo
 */
public class TiedostostaLukija {

    public ArrayList<String> lueTiedosto(String tiedostonNimi, VirheidenKasittelija kasittelija) {
        ArrayList<String> palautettava = new ArrayList<String>();
        try {
            File tiedosto = new File("pelit_ja_tilastot/" + tiedostonNimi);
            Scanner lukija = new Scanner(tiedosto);
            while (lukija.hasNextLine()) {
                palautettava.add(lukija.nextLine());
            }
            lukija.close();
        } catch (Exception e) {
            kasittelija.virheScannerinJaTiedostonKanssa();
        }
        return palautettava;
    }
}
