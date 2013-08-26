/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.Color;
import javax.swing.JButton;
import kayttoliittymat.kuuntelijat.peli.RuudukonsiirtoNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.VuoronvaihtoNappulanKuuntelija;
import viidensuora.Laatu;
import viidensuora.RistiNollaMuistio;

/**
 * Graafisen pelitoiminnon perusyksikkö, sama kuin paperilla pelattaessa ruutu.
 * Sisältää yhden JButton -tyyppisen nappulan, jolle oma kuuntelija.
 *
 * @see RuutuNappulanKuuntelija
 * @author Aapo
 */
public class RuutuNappula {

    private static boolean vihjettaPainettu = false;

    /**
     *
     * @param onko
     */
    public static void vihjettaPainettu(boolean onko) {
        vihjettaPainettu = onko;
    }
    private int x;
    private int y;
    private Laatu seuraavaLaatu;
    private RistiNollaMuistio muistio;
    private JButton nappula;
    private PeliHallitsija pelihallitsija;
    private RuudukonHallitsija ruudukko;

    /**
     * Konstruktori, alustetaan oliomuuttujat
     *
     * @param x käyttäjän syöttämä alkuperäinen x-koordinaatti
     * @param y käyttäjän syöttämä alkuperäinen y-koordinaatti
     * @param muistio RistiNollaMuistio, johon nappula yrittää listä merkkiä
     * sitä paintettaessa
     * @param pelihallitsija  
     */
    public RuutuNappula(int x, int y, RistiNollaMuistio muistio, PeliHallitsija pelihallitsija, RuudukonHallitsija ruudukko) {
        this.x = x;
        this.y = y;
        this.seuraavaLaatu = Laatu.RISTI;
        this.muistio = muistio;
        this.nappula = new JButton();
        nappulaanLisukkeet();
        this.pelihallitsija = pelihallitsija;
        this.ruudukko = ruudukko;
    }
    
    private void nappulaanLisukkeet() {
        this.nappula.addActionListener(new RuutuNappulanKuuntelija(this));
        this.nappula.addKeyListener(new VuoronvaihtoNappulanKuuntelija(ruudukko, pelihallitsija));
    }

    /**
     * muutetaan x-koordinaattia käyttäjän syötteen mukaisesti
     *
     * @param dx käyttäjän syöttämä x-koordinaatin muutos
     */
    public void siirraXsuunnassa(int dx) {
        this.x += dx;
    }

    /**
     * muutetaan y-koordinaattia käyttäjän syötteen mukaisesti
     *
     * @param dy käyttäjän syöttämä y-koordinaatin muutos
     */
    public void siirraYsuunnassa(int dy) {
        this.y += dy;
    }

    /**
     * Vuoro on juuri päättynyt, seuraavaksi yritetään syöttää muistiolle
     * toisentyyppistä merkkiä
     */
    public void vaihdaSeuraavanLaatua() {
        if (getSeuraavaLaatu().equals(Laatu.RISTI)) {
            seuraavaLaatu = Laatu.NOLLA;
        } else {
            seuraavaLaatu = Laatu.RISTI;
        }
    }

    /**
     * Muutetaan nappulan päällä lukevaa kirjoitusta
     *
     * @param kirjoitus käyttäjän syöttämä kirjoitus
     */
    public void muutaKirjoitus(String kirjoitus) {
        this.pelihallitsija.paivitaInfoteksti();
        this.nappula = new JButton(kirjoitus);
        nappulaanLisukkeet();
        if (kirjoitus.contains("s")) {
            nappula.setBackground(new Color(200, 200, 255));
        } else if (kirjoitus.contains("S")) {
            nappula.setBackground(new Color(100, 100, 255));
        } else if (kirjoitus.contains("v")) {
            nappula.setBackground(new Color(255, 100, 100));
        } else if (kirjoitus.contains("V")) {
            nappula.setBackground(new Color(200, 0, 0));
        } else if (kirjoitus.contains("h")) {
            nappula.setBackground(new Color(100, 255, 100));
        } else if (kirjoitus.contains("H")) {
            nappula.setBackground(new Color(0, 200, 0));
        } else if (kirjoitus.contains("z")) {
            nappula.setBackground(new Color(255, 255, 150));
        } else if (kirjoitus.contains("Z")) {
            nappula.setBackground(new Color(255, 255, 0));
        } else if (!kirjoitus.isEmpty()) {
            nappula.setBackground(Color.LIGHT_GRAY);
        } else {
            nappula.setBackground(Color.WHITE);
        }
        nappula.setOpaque(true);
    }

    /**
     *
     * @return palauttaa nappulan
     */
    public JButton getNappula() {
        return this.nappula;
    }

    void nappulaaPainettu() {
        boolean lisattiin;
        if (getSeuraavaLaatu().equals(Laatu.RISTI)) {
            lisattiin = muistio.lisaaRisti(getX(), getY());
        } else {
            lisattiin = muistio.lisaaNolla(getX(), getY());
        }
        if (vihjettaPainettu) {
            vihjettaPainettu(false);
            RuudukonsiirtoNappulanKuuntelija.asetaVihje(false);
            this.pelihallitsija.getRuudukko().paivitaRuudukonKirjoitusTilanteet(this.muistio.getMerkit());
        }
        if (lisattiin) {
            if (getSeuraavaLaatu().equals(Laatu.RISTI)) {
                muutaKirjoitus("X");
            } else {
                muutaKirjoitus("O");
            }
            pelihallitsija.paivitaKenttaJaTarkistaVoitto();
        }

    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the seuraavaLaatu
     */
    public Laatu getSeuraavaLaatu() {
        return seuraavaLaatu;
    }
}
