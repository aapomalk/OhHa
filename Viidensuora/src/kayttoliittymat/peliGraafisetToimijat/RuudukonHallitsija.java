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
 * @see RuutuNappula
 * @author Aapo
 */
public class RuudukonHallitsija {
    private ArrayList<ArrayList<RuutuNappula>> ruudukko;
    
    
    public RuudukonHallitsija(RistiNollaMuistio muistio) {
        this.ruudukko = new ArrayList<ArrayList<RuutuNappula>>();
        rakennaRuudukko(muistio);
    }
    
    private void rakennaRuudukko(RistiNollaMuistio muistio) {
        for (int y = 0; y < 15; y++) {
            ArrayList<RuutuNappula> riviNappuloita = new ArrayList<RuutuNappula>();
            for (int x = 0; x < 15; x++) {
                riviNappuloita.add(new RuutuNappula(x, y, muistio));
            }
            this.ruudukko.add(riviNappuloita);
        }
    }
    
    public void paivitaRuudukonXjaOtilanteet(ArrayList<Merkki> merkit) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                boolean muuttuikoArvo = false;
                for (Merkki merkki : merkit) {
                    if (merkki.getX() == ruutuNappula.getX() && merkki.getY() == ruutuNappula.getY()) {
                        if (merkki.getLaatu().equals(Laatu.RISTI)) {
                            ruutuNappula.muutaKirjoitus("X");
                            muuttuikoArvo = true;
                        } else if (merkki.getLaatu().equals(Laatu.NOLLA)) {
                            ruutuNappula.muutaKirjoitus("O");
                            muuttuikoArvo = true;
                        }
                        break;
                    }
                }
                if (!muuttuikoArvo) {
                    ruutuNappula.muutaKirjoitus("");
                }
            }
        }
    }
    
    public void siirraRuudukkoaXsuunnassa(int dx) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                ruutuNappula.siirraXsuunnassa(dx);
            }
        }
    }
    
    public void siirraRuudukkoaYsuunnassa(int dy) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                ruutuNappula.siirraYsuunnassa(dy);
            }
        }
    }
    
    public JPanel palautaRuudukkoJPanelina() {
        JPanel ruudut = new JPanel(new GridLayout(1, 15));
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
}
