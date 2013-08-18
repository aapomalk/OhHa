/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import java.util.ArrayList;
import java.util.Scanner;
import tilastotJaTunnukset.*;

/**
 * Tiedostostalukijan avulla käy läpi kolme tekstitiedostoa, jotka sisältävät
 * kaikki tilastot, ja tallentaa ne muistioon
 * @see tilastotJaTunnukset.TilastoTunnusMuistio
 * @see TiedostostaLukija
 * @author Aapo
 */
public class TilastojenLukija {
    /**
     * @see TilastojenLukija
     * @param tiedostoNimi käyttäjän syöttämä tiedoston nimi
     * @param kasittelija käyttäjän syöttämä virheidenkäsittelijä
     * @return palautetaan joko tyhjä tai täytetty TilastoTunnusMuistio
     */
    public TilastoTunnusMuistio lataaTilastot(String tiedostoNimi, VirheidenKasittelija kasittelija) {
        TiedostostaLukija lukija = new TiedostostaLukija();
        
        ArrayList<String> luettu = new ArrayList<String>();
        luettu.addAll(lukija.lueTiedosto("Yleiset_tilastot_" + tiedostoNimi, kasittelija));
        ArrayList<ArrayList<String>> yleisetTilastot = paloitteleRivit(luettu);
        
        luettu = new ArrayList<String>();
        luettu.addAll(lukija.lueTiedosto("Tunnus_tilastot_" + tiedostoNimi, kasittelija));
        ArrayList<ArrayList<String>> tunnusTilastot = paloitteleRivit(luettu);
        
        luettu = new ArrayList<String>();
        luettu.addAll(lukija.lueTiedosto("Tunnuspari_tilastot_" + tiedostoNimi, kasittelija));
        ArrayList<ArrayList<String>> tunnusPariTilastot = paloitteleRivit(luettu);
        
        return kokoaPaloista(yleisetTilastot, tunnusTilastot, tunnusPariTilastot);
    }

    private ArrayList<ArrayList<String>> paloitteleRivit(ArrayList<String> tiedot) {
        
        ArrayList<ArrayList<String>> enemmanPaloiksi = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < tiedot.size(); i++) {
            Scanner paloittelija = new Scanner(tiedot.get(i));
            ArrayList<String> apu = new ArrayList<String>();
            while (paloittelija.hasNext()) {
                apu.add(paloittelija.next());
            }
            enemmanPaloiksi.add(apu);
        }
        return enemmanPaloiksi;
    }

    private TilastoTunnusMuistio kokoaPaloista(ArrayList<ArrayList<String>> yleiset,
            ArrayList<ArrayList<String>> tunnuksia, ArrayList<ArrayList<String>> tunnusParit) {
        if (yleiset.size() <= 1 || yleiset.get(1).size() < 5) {
            return new TilastoTunnusMuistio();
        }
        int nollat = tarkistaOnkoNumero(yleiset.get(1).get(0));
        double pituus = tarkistaOnkoDouble(yleiset.get(1).get(1));
        int maara = tarkistaOnkoNumero(yleiset.get(1).get(2));
        int tallennukset = tarkistaOnkoNumero(yleiset.get(1).get(3));
        int ristit = tarkistaOnkoNumero(yleiset.get(1).get(4));
        ArrayList<Tunnus> tunnukset = kokoaTunnukset(tunnuksia);
        ArrayList<TunnusPari> parit = kokoaParit(tunnusParit, tunnukset);

        return new TilastoTunnusMuistio(tunnukset, parit, nollat, pituus, maara, tallennukset, ristit);
    }

    private ArrayList<TunnusPari> kokoaParit(ArrayList<ArrayList<String>> tunnusParit, ArrayList<Tunnus> tunnukset) {
        ArrayList<TunnusPari> parit = new ArrayList<TunnusPari>();
        for (ArrayList<String> lista : tunnusParit) {
            if (lista.size() < 9) {
                continue;
            }
            Tunnus tunnus1 = null;
            Tunnus tunnus2 = null;
            for (Tunnus t : tunnukset) {
                if (t.getTunnus().equals(lista.get(0))) {
                    tunnus1 = t;
                } else if (t.getTunnus().equals(lista.get(1))) {
                    tunnus2 = t;
                }
            }
            if (tunnus1 == null || tunnus2 == null) {
                continue;
            }
            int pelit = tarkistaOnkoNumero(lista.get(2));
            double pituus = tarkistaOnkoDouble(lista.get(3));
            int tallennus = tarkistaOnkoNumero(lista.get(4));
            int t1risti = tarkistaOnkoNumero(lista.get(5));
            int t1voitto = tarkistaOnkoNumero(lista.get(6));
            int t2risti = tarkistaOnkoNumero(lista.get(7));
            int t2voitto = tarkistaOnkoNumero(lista.get(8));

            parit.add(new TunnusPari(tunnus1, tunnus2, pelit, t1voitto, t1risti, t2voitto, t2risti, pituus, tallennus));
        }
        return parit;
    }

    private ArrayList<Tunnus> kokoaTunnukset(ArrayList<ArrayList<String>> tunnuksia) {
        int indeksi = 0;
        ArrayList<Tunnus> tunnukset = new ArrayList<Tunnus>();
        for (ArrayList<String> lista : tunnuksia) {
            if (tunnuksia.get(indeksi).size() < 6) {
                continue;
            }
            String tunnus = lista.get(0);
            int pelit = tarkistaOnkoNumero(lista.get(1));
            double pituus = tarkistaOnkoDouble(lista.get(2));
            int risti = tarkistaOnkoNumero(lista.get(3));
            int vihje = tarkistaOnkoNumero(lista.get(4));
            int voitto = tarkistaOnkoNumero(lista.get(5));
            tunnukset.add(new Tunnus(tunnus, pelit, pituus, risti, vihje, voitto));
        }
        return tunnukset;
    }

    private int tarkistaOnkoNumero(String lukuko) {
        if (lukuko.matches("[0-9]+")) {
            return Integer.parseInt(lukuko);
        }
        return 0;
    }

    private double tarkistaOnkoDouble(String lukuko) {
        if (lukuko.matches("[0-9]+.[0-9]+")) {
            return Double.parseDouble(lukuko);
        }
        return 0.0;
    }
}
