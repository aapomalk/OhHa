/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import vihjeToiminto.Vihje;
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
    private Vihje vihje;
    private boolean vihjettaPainettu;

    /**
     * Alustetaan ruudukko
     *
     * @param muistio käyttäjän syöttämä RistiNollaMuistio tallennetaan
     * RuutuNappuloihin
     */
    public RuudukonHallitsija(RistiNollaMuistio muistio, PeliHallitsija pelihallitsija) {
        this.ruudukko = new ArrayList<ArrayList<RuutuNappula>>();
        rakennaRuudukko(muistio, pelihallitsija);
        this.vihje = new Vihje();
        this.vihjettaPainettu = false;
    }
    
    public boolean seuraavanLaatuRisti() {
        return Laatu.RISTI == (seuraavanLaatu());
    }
    
    private Laatu seuraavanLaatu() {
        return ruudukko.get(0).get(0).getSeuraavaLaatu();
    }
    
    public boolean etsiVihjeet(ArrayList<Merkki> merkit, Laatu kummanVuoro) {
        if (kummanVuoro.equals(seuraavanLaatu())) {
            vihje.lisaaVihjeetListoihin(merkit, kummanVuoro);
            vihjettaPainettu = true;
            RuutuNappula.vihjettaPainettu(true);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "vihjeen voi saada vain omalla vuorolla");
            return false;
        }
    }

    public void paivitaVihjeRuudukot() {
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHairitseVastustajaa(), "s");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getEhkaHyodyllinen(), "s");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHyodyllistaJaHairintaa(), "S");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getRakennaHyokkays(), "h");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVaroVastustajaa(), "v");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHyokkaystaJaVaromista(), "z");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVarmaVoitto(), "H");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getTaytyyEstaa(), "V");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVoittamistaJaEstamista(), "Z");
    }
    
    private void tyhjennaVihjeRuudukot() {
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHairitseVastustajaa(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getEhkaHyodyllinen(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHyodyllistaJaHairintaa(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVaroVastustajaa(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getRakennaHyokkays(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getHyokkaystaJaVaromista(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getTaytyyEstaa(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVarmaVoitto(), "");
        this.paivitaRuudukonKirjoitusTilanteet(vihje.getVoittamistaJaEstamista(), "");
    }

    private void rakennaRuudukko(RistiNollaMuistio muistio, PeliHallitsija pelihallitsija) {
        for (int y = 0; y < 15; y++) {
            ArrayList<RuutuNappula> riviNappuloita = new ArrayList<RuutuNappula>();
            for (int x = 0; x < 15; x++) {
                riviNappuloita.add(new RuutuNappula(x, y, muistio, pelihallitsija));
            }
            this.ruudukko.add(riviNappuloita);
        }
    }

    /**
     * Päivitetään RuutuNappuloiden tekstit vastaamaan nykyistä pelitilannetta
     *
     * @param merkit käyttäjän syöttämä lista merkeistä
     */
    public void paivitaRuudukonKirjoitusTilanteet(ArrayList<Merkki> merkit, String vihjeMerkki) {
        for (ArrayList<RuutuNappula> arrayList : ruudukko) {
            for (RuutuNappula ruutuNappula : arrayList) {
                boolean muuttuikoArvo = false;
                for (Merkki merkki : merkit) {
                    muuttuikoArvo = muutaKirjoitus(ruutuNappula, merkki, vihjeMerkki);
                    if (muuttuikoArvo) {
                        break;
                    }
                }
                if (!muuttuikoArvo) {
                    if (vihjeMerkki.isEmpty()) {
                        ruutuNappula.muutaKirjoitus("");
                    }
                }
            }
        }
    }

    public void paivitaRuudukonKirjoitusTilanteet(ArrayList<Merkki> merkit) {
        this.paivitaRuudukonKirjoitusTilanteet(merkit, "");
    }

    private boolean muutaKirjoitus(RuutuNappula ruutuNappula, Merkki merkki, String teksti) {
        if (merkki.getX() == ruutuNappula.getX() && merkki.getY() == ruutuNappula.getY()) {
            if (merkki.getLaatu().equals(Laatu.RISTI)) {
                ruutuNappula.muutaKirjoitus("X");
                return true;
            } else if (merkki.getLaatu().equals(Laatu.NOLLA)) {
                ruutuNappula.muutaKirjoitus("O");
                return true;
            } else if (merkki.getLaatu().equals(Laatu.TYHJA)) {
                ruutuNappula.muutaKirjoitus(teksti);
                return true;
            }
        }
        return false;
    }

    void muutetaankoKirjoitus(RuutuNappula nappula, Merkki merkki) {
        muutaKirjoitus(nappula, merkki, "");
        if (this.vihjettaPainettu) {
            this.vihjettaPainettu = false;
            this.tyhjennaVihjeRuudukot();
        }
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

    public int getRuudukonSuurinX() {
        return this.ruudukko.get(0).get(this.ruudukko.get(0).size() - 1).getX();
    }

    public int getRuudukonPieninX() {
        return this.ruudukko.get(0).get(0).getX();
    }

    public int getRuudukonPieninY() {
        return this.ruudukko.get(0).get(0).getY();
    }

    public int getRuudukonSuurinY() {
        return this.ruudukko.get(this.ruudukko.size() - 1).get(0).getY();
    }
}
