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
    
    public RuutuNappula(int x, int y, RistiNollaMuistio muistio) {
        this.x = x;
        this.y = y;
        this.seuraavaLaatu = Laatu.RISTI;
        this.muistio = muistio;
        this.nappula = new JButton();
        this.nappula.addActionListener(new RuutuNappulanKuuntelija(this));
    }
    
    public void siirraXsuunnassa(int dx) {
        this.x += dx;
    }
    
    public void siirraYsuunnassa(int dy) {
        this.y += dy;
    }
    
    public void vaihdaSeuraavanLaatua() {
        if (seuraavaLaatu.equals(Laatu.RISTI)) {
            seuraavaLaatu = Laatu.NOLLA;
        } else {
            seuraavaLaatu = Laatu.RISTI;
        }
    }
    
    public void muutaKirjoitus(String kirjoitus) {
        this.nappula = new JButton(kirjoitus);
        this.nappula.addActionListener(new RuutuNappulanKuuntelija(this));
    }
    
    public JButton getNappula() {
        return this.nappula;
    }
    
    void nappulaaPainettu() {
        if (seuraavaLaatu.equals(Laatu.RISTI)) {
            muistio.lisaaRisti(getX(), getY());
        } else {
            muistio.lisaaNolla(getX(), getY());
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
