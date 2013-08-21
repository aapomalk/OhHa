/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kayttoliittymat.kuuntelijat.peli.VuoronvaihtoNappulanKuuntelija;
import tilastotJaTunnukset.*;
import viidensuora.RistiNollaMuistio;
import kayttoliittymat.Kayttoliittyma;

/**
 * Jottei kaikki koodi olisi kayttoliittymassa
 * @author aapomalk
 */
public class PeliHallitsija {
    private RuudukonHallitsija ruudukko;
    private TunnusPari pari;
    private JPanel naytto;
    private Tunnus ristiPelaaja;
    private RistiNollaMuistio muistio;
    private JFrame frame;
    private Kayttoliittyma liittyma;
    /**
     * Alustetaan ruudukko, tunnuspari ja JPanel.
     * @param pari
     * @param muistio 
     */
    public PeliHallitsija(TunnusPari pari, RistiNollaMuistio muistio, JFrame frame, Kayttoliittyma liittyma) {
        this.pari = pari;
        this.muistio = muistio;
        ruudukko = new RuudukonHallitsija(muistio, this);
        naytto = new JPanel(new BorderLayout());
        this.frame = frame;
        this.liittyma = liittyma;
    }
    
    public PeliHallitsija(RistiNollaMuistio muistio, JFrame frame, Kayttoliittyma liittyma) {
        this(null, muistio, frame, liittyma);
    }
    
    private void kysyRistiPelaaja() {
        Tunnus[] tunnukset = new Tunnus[2];
        tunnukset[0] = pari.getTunnus1();
        tunnukset[1] = pari.getTunnus2();
        this.ristiPelaaja = (Tunnus) JOptionPane.showInputDialog(naytto, "Valitse ristilla pelaava tunnus", "Ristipelaaja", 
                JOptionPane.PLAIN_MESSAGE, null, tunnukset, tunnukset[0]);
    }
    
    public JPanel kaynistaPeli() {
        if (this.pari != null) {
            kysyRistiPelaaja();
        }
        naytto.add(lisaaNappulat(), BorderLayout.EAST);
        naytto.add(lisaaPelikenttaJaNuolet(), BorderLayout.CENTER);
        
        return naytto;
    }
    
    private JPanel lisaaPelikenttaJaNuolet() {
        JPanel pelikentta = new JPanel(new BorderLayout());
        
        JButton ylos = new JButton("A");
        JButton alas = new JButton("V");
        JButton oikealle = new JButton(">");
        JButton vasemmalle = new JButton("<");
        
        pelikentta.add(ylos, BorderLayout.NORTH);
        pelikentta.add(alas, BorderLayout.SOUTH);
        pelikentta.add(oikealle, BorderLayout.EAST);
        pelikentta.add(vasemmalle, BorderLayout.WEST);
        pelikentta.add(this.ruudukko.palautaRuudukkoJPanelina(), BorderLayout.CENTER);
        
        return pelikentta;
    }
    
    public void paivitaPelikentta() {
        this.naytto.remove(this.naytto.getComponent(1));
        this.naytto.add(lisaaPelikenttaJaNuolet());
        this.frame.pack();
    }
    
    public void paivitaKenttaJaTarkistaVoitto() {
        this.paivitaPelikentta();
        boolean voitto = this.liittyma.tarkistaVoitto();
        if (voitto) {
            String merkki;
            if (muistio.getEdellinenMerkkiRisti()) {
                merkki = "ristilla pelannut";
            } else {
                merkki = "nollalla pelannut";
            }
            JOptionPane.showMessageDialog(frame, "Peli paattyi, onnittelut " + merkki);
        }
    }
    
    private JPanel lisaaNappulat() {
        JPanel nappaimet = new JPanel(new GridLayout(5, 1));
        
        JButton vuoronVaihto = new JButton("Vuoron vaihto");
        JButton vihjeNappula = new JButton("vihje");
        JButton tallennaPeli = new JButton("tallenna");
        JButton takaisinValikkoon = new JButton("Takaisin valikkoon");
        JButton peruSiirto = new JButton("Peru siirto");
        
        vuoronVaihto.addActionListener(new VuoronvaihtoNappulanKuuntelija(this.ruudukko));
        
        nappaimet.add(vuoronVaihto);
        nappaimet.add(vihjeNappula);
        nappaimet.add(tallennaPeli);
        nappaimet.add(takaisinValikkoon);
        nappaimet.add(peruSiirto);
        
        return nappaimet;
    }
}