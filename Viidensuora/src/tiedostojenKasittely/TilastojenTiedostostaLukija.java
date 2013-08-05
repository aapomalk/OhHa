/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import tilastotJaTunnukset.*;

public class TilastojenTiedostostaLukija {

    private File tiedosto;
    private Scanner lukija;

    public TilastoTunnusMuistio lataaTilastot(String tiedostoNimi, VirheidenKasittelija kasittelija) {
        TilastoTunnusMuistio muistio = new TilastoTunnusMuistio();
        try {
            tiedosto = new File(tiedostoNimi);
            lukija = new Scanner(tiedosto);
            muistio = lueTiedosto();
        } catch (Exception e) {
            kasittelija.virheScannerinJaTiedostonKanssa();
        }
        return muistio;
    }

    private TilastoTunnusMuistio lueTiedosto() {
        ArrayList<String> luettu = new ArrayList<String>();
        while (lukija.hasNextLine()) {
            luettu.add(lukija.nextLine());
        }
        return kasitteleTiedostoa(luettu);
    }
    
    private TilastoTunnusMuistio kasitteleTiedostoa(ArrayList<String> tiedot) {
        if (tiedot.size() <= 1) {
            return new TilastoTunnusMuistio();
        }
        ArrayList<ArrayList<String>> enemmanPaloiksi = new ArrayList<ArrayList<String>>();
        for (int i = 1; i < tiedot.size(); i++) {
            Scanner paloittelija = new Scanner(tiedot.get(i));
            ArrayList<String> apu = new ArrayList<String>();
            while (paloittelija.hasNext()) {
                apu.add(paloittelija.next());
            }
            enemmanPaloiksi.add(apu);
        }
        return kokoaPaloista(enemmanPaloiksi);
    }
    
    private TilastoTunnusMuistio kokoaPaloista(ArrayList<ArrayList<String>> palat) {
        if (palat.size() <= 2 || palat.get(0).size() < 5) {
            return new TilastoTunnusMuistio();
        }
        int nollat = tarkistaOnkoNumero(palat.get(0).get(0));
        double pituus = tarkistaOnkoDouble(palat.get(0).get(1));
        int maara = tarkistaOnkoNumero(palat.get(0).get(2));
        int tallennukset = tarkistaOnkoNumero(palat.get(0).get(3));
        int ristit = tarkistaOnkoNumero(palat.get(0).get(4));
        ArrayList<Tunnus> tunnukset = kokoaTunnukset(palat);
        ArrayList<TunnusPari> parit = kokoaParit(palat, tunnukset);
        
        return new TilastoTunnusMuistio(tunnukset, parit, nollat, pituus, maara, tallennukset, ristit);
    }
    
    private ArrayList<TunnusPari> kokoaParit(ArrayList<ArrayList<String>> palat, ArrayList<Tunnus> tunnukset) {
        ArrayList<TunnusPari> parit = new ArrayList<TunnusPari>();
        for (int i = 0; i < palat.size(); i++) {
            if (palat.get(i).size() < 9) {
                break;
            }
            Tunnus tunnus1 = null;
            Tunnus tunnus2 = null;
            for (Tunnus t : tunnukset) {
                if (t.getTunnus().equals(palat.get(i).get(0))) {
                    tunnus1 = t;
                } else if (t.getTunnus().equals(palat.get(i).get(1))) {
                    tunnus2 = t;
                }
            }
            int pelit = tarkistaOnkoNumero(palat.get(i).get(2));
            double pituus = tarkistaOnkoDouble(palat.get(i).get(3));
            int tallennus = tarkistaOnkoNumero(palat.get(i).get(4));
            int t1risti = tarkistaOnkoNumero(palat.get(i).get(5));
            int t1voitto = tarkistaOnkoNumero(palat.get(i).get(6));
            int t2risti = tarkistaOnkoNumero(palat.get(i).get(7));
            int t2voitto = tarkistaOnkoNumero(palat.get(i).get(8));
            
            parit.add(new TunnusPari(tunnus1, tunnus2, pelit, t1voitto, t1risti, t2voitto, t2risti, pituus, tallennus));
        }
        return parit;
    }
    
    private ArrayList<Tunnus> kokoaTunnukset(ArrayList<ArrayList<String>> palat) {
        int indeksi = 2;
        ArrayList<Tunnus> tunnukset = new ArrayList<Tunnus>();
        while (indeksi < palat.size()) {
            if (!palat.get(indeksi).isEmpty()) {
                if (palat.get(indeksi).get(0).equals(" ") || palat.get(indeksi).size() < 6) {
                    break;
                }
            } else {
                break;
            }
            String tunnus = palat.get(indeksi).get(0);
            int pelit = tarkistaOnkoNumero(palat.get(indeksi).get(1));
            double pituus = tarkistaOnkoDouble(palat.get(indeksi).get(2));
            int risti = tarkistaOnkoNumero(palat.get(indeksi).get(3));
            int vihje = tarkistaOnkoNumero(palat.get(indeksi).get(4));
            int voitto = tarkistaOnkoNumero(palat.get(indeksi).get(5));
            tunnukset.add(new Tunnus(tunnus, pelit, pituus, risti, vihje, voitto));
            indeksi++;
        }
        poistaKasitellytPalat(palat, indeksi);
        return tunnukset;
    }
    
    private void poistaKasitellytPalat(ArrayList<ArrayList<String>> palat, int indeksi) {
        for (int i = 0; i <= indeksi; i++) {
            palat.remove(0);
        }
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
