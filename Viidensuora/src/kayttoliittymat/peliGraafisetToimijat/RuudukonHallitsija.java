/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import viidensuora.Laatu;
import viidensuora.Merkki;
import viidensuora.RistiNollaMuistio;

/**
 * Nimensä mukaisesti hallitsee ruudukkoa, siirtää, päivittää ja rakentaa.
 *
 * @see RuutuNappula
 * @author Aapo
 */
public class RuudukonHallitsija {

    private ArrayList<ArrayList<RuutuNappula>> ruudukko;

    /**
     * Alustetaan ruudukko
     *
     * @param muistio käyttäjän syöttämä RistiNollaMuistio tallennetaan
     * RuutuNappuloihin
     */
    public RuudukonHallitsija(RistiNollaMuistio muistio, PeliHallitsija pelihallitsija) {
        this.ruudukko = new ArrayList<ArrayList<RuutuNappula>>();
        rakennaRuudukko(muistio, pelihallitsija);
    }

    private void rakennaRuudukko(RistiNollaMuistio muistio, PeliHallitsija pelihallitsija) {
        for (int y = 0; y < 15; y++) {
            ArrayList<RuutuNappula> riviNappuloita = new ArrayList<RuutuNappula>();
            for (int x = 0; x < 15; x++) {
                riviNappuloita.add(new RuutuNappula(x, y, muistio, this, pelihallitsija));
            }
            this.ruudukko.add(riviNappuloita);
        }
    }

    /**
     * Päivitetään RuutuNappuloiden tekstit vastaamaan nykyistä pelitilannetta
     *
     * @param merkit käyttäjän syöttämä lista merkeistä
     */
    public void paivitaRuudukonXjaOtilanteet(ArrayList<Merkki> merkit) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                boolean muuttuikoArvo = false;
                for (Merkki merkki : merkit) {
                    muuttuikoArvo = muutaKirjoitus(ruutuNappula, merkki);
                    if (muuttuikoArvo) {
                        break;
                    }
                }
                if (!muuttuikoArvo) {
                    ruutuNappula.muutaKirjoitus("");
                }
            }
        }
    }

    private boolean muutaKirjoitus(RuutuNappula ruutuNappula, Merkki merkki) {
        if (merkki.getX() == ruutuNappula.getX() && merkki.getY() == ruutuNappula.getY()) {
            if (merkki.getLaatu().equals(Laatu.RISTI)) {
                ruutuNappula.muutaKirjoitus("X");
                return true;
            } else if (merkki.getLaatu().equals(Laatu.NOLLA)) {
                ruutuNappula.muutaKirjoitus("O");
                return true;
            }
        }
        return false;
    }
    
    void muutetaankoKirjoitus(RuutuNappula nappula, Merkki merkki) {
        muutaKirjoitus(nappula, merkki);
    }

    /**
     * Siirretään ruudukkoa x-suunnassa
     *
     * @param dx käyttäjän syöttämä x-suuntaisen muutoksen suuruus
     */
    public void siirraRuudukkoaXsuunnassa(int dx) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                ruutuNappula.siirraXsuunnassa(dx);
            }
        }
    }

    /**
     * Siirretään ruudukkoa y-suunnassa
     *
     * @param dy käyttäjän syöttämä y-suuntaisen muutoksen suuruus
     */
    public void siirraRuudukkoaYsuunnassa(int dy) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                ruutuNappula.siirraYsuunnassa(dy);
            }
        }
    }

    /**
     * Luodaan RuutuNappuloiden JButtoneista uusi JPanel, joka palautetaan
     * käyttäjälle GraafistaKäyttöliittymää ajatellen
     *
     * @return ruudut nappuloina
     */
    public JPanel palautaRuudukkoJPanelina() {
        JPanel ruudut = new JPanel(new GridLayout(15, 1));
        for (int i = 0; i < ruudukko.size(); i++) {
            ruudut.add(rakennaRiviRuudukkoa(i));
        }
        return ruudut;
    }

    private JPanel rakennaRiviRuudukkoa(int rivi) {
        JPanel ruudut = new JPanel(new GridLayout(1, 15));
        for (int i = 0; i < ruudukko.get(rivi).size(); i++) {
            ruudut.add(ruudukko.get(rivi).get(i).getNappula());
        }
        return ruudut;
    }
    
    public void vaihdaVuoroa() {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                ruutuNappula.vaihdaSeuraavanLaatua();
            }
        }
    }
}
