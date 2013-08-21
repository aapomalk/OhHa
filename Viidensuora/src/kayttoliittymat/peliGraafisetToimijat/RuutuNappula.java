/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import javax.swing.JButton;
import viidensuora.Laatu;
import viidensuora.RistiNollaMuistio;

/**
 * Graafisen pelitoiminnon perusyksikkö, sama kuin paperilla pelattaessa ruutu.
 * Sisältää yhden JButton -tyyppisen nappulan, jolle oma kuuntelija.
 * @see RuutuNappulanKuuntelija
 * @author Aapo
 */
public class RuutuNappula {
    
    private int x;
    private int y;
    private Laatu seuraavaLaatu;
    private RistiNollaMuistio muistio;
    private JButton nappula;
    private PeliHallitsija pelihallitsija;
    /**
     * Konstruktori, alustetaan oliomuuttujat
     * @param x käyttäjän syöttämä alkuperäinen x-koordinaatti
     * @param y käyttäjän syöttämä alkuperäinen y-koordinaatti
     * @param muistio RistiNollaMuistio, johon nappula yrittää listä merkkiä
     * sitä paintettaessa
     */
    public RuutuNappula(int x, int y, RistiNollaMuistio muistio, PeliHallitsija pelihallitsija) {
        this.x = x;
        this.y = y;
        this.seuraavaLaatu = Laatu.RISTI;
        this.muistio = muistio;
        this.nappula = new JButton();
        this.nappula.addActionListener(new RuutuNappulanKuuntelija(this));
        this.pelihallitsija = pelihallitsija;
    }
    /**
     * muutetaan x-koordinaattia käyttäjän syötteen mukaisesti
     * @param dx käyttäjän syöttämä x-koordinaatin muutos
     */
    public void siirraXsuunnassa(int dx) {
        this.x += dx;
    }
    /**
     * muutetaan y-koordinaattia käyttäjän syötteen mukaisesti
     * @param dy käyttäjän syöttämä y-koordinaatin muutos
     */
    public void siirraYsuunnassa(int dy) {
        this.y += dy;
    }
    /**
     * Vuoro on juuri päättynyt, seuraavaksi yritetään syöttää muistiolle toisentyyppistä merkkiä
     */
    public void vaihdaSeuraavanLaatua() {
        if (seuraavaLaatu.equals(Laatu.RISTI)) {
            seuraavaLaatu = Laatu.NOLLA;
        } else {
            seuraavaLaatu = Laatu.RISTI;
        }
    }
    /**
     * Muutetaan nappulan päällä lukevaa kirjoitusta
     * @param kirjoitus käyttäjän syöttämä kirjoitus
     */
    public void muutaKirjoitus(String kirjoitus) {
        this.nappula = new JButton(kirjoitus);
        this.nappula.addActionListener(new RuutuNappulanKuuntelija(this));
    }
    
    public JButton getNappula() {
        return this.nappula;
    }
    
    void nappulaaPainettu() {
        boolean lisattiin;
        if (seuraavaLaatu.equals(Laatu.RISTI)) {
            lisattiin = muistio.lisaaRisti(getX(), getY());
        } else {
            lisattiin = muistio.lisaaNolla(getX(), getY());
        }
        if (lisattiin) {
            if (seuraavaLaatu.equals(Laatu.RISTI)) {
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
}
