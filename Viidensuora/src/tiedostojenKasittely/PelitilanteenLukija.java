/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import java.util.ArrayList;
import java.util.Scanner;
import viidensuora.RistiNollaMuistio;

/**
 * Tiedostostalukijaa hyväksikäyttäen lataa pelitilanteen tiedostosta
 * @see TiedostostaLukija
 * @author Aapo
 */
public class PelitilanteenLukija {
    /**
     * @see PelitilanteenLukija
     * @param tiedostonNimi käyttäjän syöttämä tiedoston nimi, josta tiedot pitäisi löytyä
     * @param kasittelija käyttäjän syöttämä virheidenkäsittelijä
     * @param muistio käyttäjän syöttämä RistiNollaMuistio, jonka sisältö korvataan tiedostosta löytyvällä
     */
    public void lataaPelitilanne(String tiedostonNimi, VirheidenKasittelija kasittelija, RistiNollaMuistio muistio) {
        tyhjennaMuistio(muistio);
        lueTiedosto(muistio, kasittelija, tiedostonNimi);
    }

    private void lueTiedosto(RistiNollaMuistio muistio, VirheidenKasittelija kasittelija, String tiedostonNimi) {
        TiedostostaLukija lukija = new TiedostostaLukija();
        ArrayList<String> merkitStringina = lukija.lueTiedosto(tiedostonNimi, kasittelija);
        //salauksen purku, jos jaksan tehda
        for (String string : merkitStringina) {
            paloitteleString(string, muistio, kasittelija);
        }
    }

    private void paloitteleString(String string, RistiNollaMuistio muistio, VirheidenKasittelija kasittelija) {
        if (!string.matches("(RISTI|NOLLA) ((|-)[0-9]+ ){2}")) {
            kasittelija.tiedostoOliKorruptoitunut();
            return;
        }
        Scanner paloittelija = new Scanner(string);

        String laatu = paloittelija.next();
        int x = Integer.parseInt(paloittelija.next());
        int y = Integer.parseInt(paloittelija.next());
        lisaaMerkki(muistio, laatu, x, y, kasittelija);
    }

    private void lisaaMerkki(RistiNollaMuistio muistio, String laatu, int x, int y, VirheidenKasittelija kasittelija) {
        if (laatu.equals("RISTI")) {
            muistio.lisaaRisti(x, y);
        } else if (laatu.equals("NOLLA")) {
            muistio.lisaaNolla(x, y);
        } else {
            kasittelija.tiedostoOliKorruptoitunut();
        }
    }

    protected void tyhjennaMuistio(RistiNollaMuistio muistio) {
        while (muistio.ristienMaara() > 0) {
            muistio.peruSiirto();
        }
    }
}
