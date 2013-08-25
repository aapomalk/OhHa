/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.peliGraafisetToimijat;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kayttoliittymat.GraafinenKayttoliittyma;
import kayttoliittymat.kuuntelijat.ValikkoonNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.RuudukonsiirtoNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.TallennaNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.VihjeNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.VuoronperumisNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.peli.VuoronvaihtoNappulanKuuntelija;
import tilastotJaTunnukset.*;
import viidensuora.Laatu;
import viidensuora.RistiNollaMuistio;
import viidensuora.Suunta;

/**
 * Jottei kaikki koodi olisi kayttoliittymassa
 *
 * @author aapomalk
 */
public class PeliHallitsija {

    private RuudukonHallitsija ruudukko;
    private TunnusPari pari;
    private JPanel naytto;
    private Tunnus ristiPelaaja;
    private RistiNollaMuistio muistio;
    private JFrame frame;
    private GraafinenKayttoliittyma liittyma;
    private JLabel infoteksti;

    /**
     * Alustetaan ruudukko, tunnuspari ja JPanel.
     *
     * @param pari
     * @param muistio
     */
    public PeliHallitsija(TunnusPari pari, RistiNollaMuistio muistio, JFrame frame, GraafinenKayttoliittyma liittyma) {
        this.pari = pari;
        this.muistio = muistio;
        ruudukko = new RuudukonHallitsija(muistio, this);
        naytto = new JPanel(new BorderLayout());
        this.frame = frame;
        this.liittyma = liittyma;
        this.infoteksti = new JLabel("tahan tulee jotain infoa pelista");
    }

    public PeliHallitsija(RistiNollaMuistio muistio, JFrame frame, GraafinenKayttoliittyma liittyma) {
        this(null, muistio, frame, liittyma);
    }
    
    public void onkoVihjePainettu(boolean onkoVihjePainettu) {
        RuudukonsiirtoNappulanKuuntelija.asetaVihje(onkoVihjePainettu);
    }
    
    public void paivitaInfoteksti() {
        String teksti = "";
        if (this.pari == null) {
            teksti += "Pikapeli";
        } else {
            teksti += this.pari.getTunnus1().getTunnus();
            boolean olikoTunnus1ristipelaaja = false;
            if (this.ristiPelaaja.equals(this.pari.getTunnus1())) {
                olikoTunnus1ristipelaaja = true;
                teksti += " (X)";
            } else {
                teksti += " (O)";
            }
            teksti += " vs. " + this.pari.getTunnus2().getTunnus();
            if (olikoTunnus1ristipelaaja) {
                teksti += " (O)";
            } else {
                teksti += " (X)";
            }
        }
        teksti += "; merkkia yrittaa sijoittaa ";
        if (this.ruudukko.seuraavanLaatuRisti()) {
            teksti += "X-pelaaja";
        } else {
            teksti += "O-pelaaja";
        }
        teksti += "; edellisen merkin laittoi ";
        if (this.muistio.getEdellinenMerkkiRisti()) {
            teksti += "X-pelaaja";
        } else {
            teksti += "O-pelaaja";
        }
        this.infoteksti.repaint();
        this.infoteksti.setText(teksti);
        this.infoteksti.repaint();
    }

    private void kysyRistiPelaaja() {
        String[] tunnukset = new String[2];
        tunnukset[0] = pari.getTunnus1().getTunnus();
        tunnukset[1] = pari.getTunnus2().getTunnus();
        String tunnusNimi = (String) JOptionPane.showInputDialog(naytto, "Valitse ristilla pelaava tunnus", "Ristipelaaja",
                JOptionPane.PLAIN_MESSAGE, null, tunnukset, tunnukset[0]);
        if (tunnusNimi != null) {
            if (tunnusNimi.equals(pari.getTunnus1().getTunnus())) {
                this.ristiPelaaja = pari.getTunnus1();
            } else {
                this.ristiPelaaja = pari.getTunnus2();
            }
        }
    }

    public JPanel kaynnistaPeli(Tunnus pelaanRistilla) {
        if (this.pari != null && pelaanRistilla == null) {
            kysyRistiPelaaja();
        } else {
            this.ristiPelaaja = pelaanRistilla;
        }
        paivitaInfoteksti();
        naytto.add(infoteksti, BorderLayout.NORTH);
        naytto.add(lisaaNappulat(), BorderLayout.EAST);
        naytto.add(lisaaPelikenttaJaNuolet(), BorderLayout.CENTER);
        this.getRuudukko().paivitaRuudukonKirjoitusTilanteet(this.muistio.getMerkit());
        this.paivitaPelikentta();
        return naytto;
    }
    
    public JPanel kaynnistaPeli() {
        return this.kaynnistaPeli(null);
    }
    
    public void tallennaPeli() {
        String tiedostonNimi;
        if (this.ristiPelaaja == null) {
            tiedostonNimi = "Pikapeli.txt";
            this.liittyma.getTilastot().peliTallennettu();
        } else {
            this.liittyma.getTilastot().peliTallennettu(this.pari);
            tiedostonNimi = "" + this.pari.getTunnus1().getTunnus() + "_" + this.pari.getTunnus2().getTunnus() + ".txt";
        }
        this.liittyma.getPeliSave().tallennaPelitilanne(this.muistio.getMerkit(), tiedostonNimi, this.liittyma.getKasittelija(), this.ristiPelaaja);
        this.liittyma.tallennaTilastot();
    }
    
    public void lisaaVihjekertaTunnukselle() {
        if (this.kummanVuoroonJai() == Laatu.RISTI) {
            this.liittyma.getTilastot().vihjeNappiaPainettu(ristiPelaaja);
        } else {
            Tunnus nollapelaaja;
            if (this.ristiPelaaja.equals(this.pari.getTunnus1())) {
                nollapelaaja = this.pari.getTunnus2();
            } else {
                nollapelaaja = this.pari.getTunnus1();
            }
            this.liittyma.getTilastot().vihjeNappiaPainettu(nollapelaaja);
        }
        this.liittyma.tallennaTilastot();
    }

    private JPanel lisaaPelikenttaJaNuolet() {
        JPanel pelikentta = new JPanel(new BorderLayout());

        JButton ylos = new JButton("A");
        JButton alas = new JButton("V");
        JButton oikealle = new JButton(">");
        JButton vasemmalle = new JButton("<");
        
        ylos.addActionListener(new RuudukonsiirtoNappulanKuuntelija(this.muistio, this, this.getRuudukko(), Suunta.ALAS));
        oikealle.addActionListener(new RuudukonsiirtoNappulanKuuntelija(this.muistio, this, this.getRuudukko(), Suunta.VASEMMALLE));
        alas.addActionListener(new RuudukonsiirtoNappulanKuuntelija(this.muistio, this, this.getRuudukko(), Suunta.YLOS));
        vasemmalle.addActionListener(new RuudukonsiirtoNappulanKuuntelija(this.muistio, this, this.getRuudukko(), Suunta.OIKEALLE));
        
        pelikentta.add(ylos, BorderLayout.NORTH);
        pelikentta.add(alas, BorderLayout.SOUTH);
        pelikentta.add(oikealle, BorderLayout.EAST);
        pelikentta.add(vasemmalle, BorderLayout.WEST);
        pelikentta.add(this.getRuudukko().palautaRuudukkoJPanelina(), BorderLayout.CENTER);

        return pelikentta;
    }

    public void paivitaPelikentta() {
        this.naytto.remove(this.naytto.getComponent(2));
        this.naytto.repaint();
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
            lopetaPeli();
        }
    }

    private void lopetaPeli() {
        if (this.pari == null) {
            this.liittyma.lisaaTilastoihinPikapeli(laskePelinPituus(), kummanVuoroonJai());
        } else {
            this.liittyma.lisaaTilastoihinKaksinpeli(laskePelinPituus(), kummanVuoroonJai(), kumpiSiisVoitti(), pari);
        }
        this.liittyma.tallennaTilastot();
        this.liittyma.palaaValikkoon();
    }
    
    private Tunnus kumpiSiisVoitti() {
        if (muistio.getEdellinenMerkkiRisti()) {
            return this.ristiPelaaja;
        } else {
            if (this.ristiPelaaja.equals(this.pari.getTunnus1())) {
                return this.pari.getTunnus2();
            } else {
                return this.pari.getTunnus1();
            }
        }
    }

    private Laatu kummanVuoroonJai() {
        if (muistio.getEdellinenMerkkiRisti()) {
            return Laatu.RISTI;
        } else {
            return Laatu.NOLLA;
        }
    }

    private double laskePelinPituus() {
        return 0.5 * (this.muistio.nollienMaara() + this.muistio.ristienMaara());
    }

    private JPanel lisaaNappulat() {
        JPanel nappaimet = new JPanel(new GridLayout(5, 1));

        JButton vuoronVaihto = new JButton("Vuoron vaihto");
        JButton vihjeNappula = new JButton("vihje");
        JButton tallennaPeli = new JButton("tallenna");
        JButton takaisinValikkoon = new JButton("Takaisin valikkoon");
        JButton peruSiirto = new JButton("Peru siirto");

        vuoronVaihto.addActionListener(new VuoronvaihtoNappulanKuuntelija(this.getRuudukko(), this));
        takaisinValikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this.liittyma, true));
        peruSiirto.addActionListener(new VuoronperumisNappulanKuuntelija(this.muistio, this, this.getRuudukko()));
        tallennaPeli.addActionListener(new TallennaNappulanKuuntelija(this));
        vihjeNappula.addActionListener(new VihjeNappulanKuuntelija(this.getRuudukko(), this, this.muistio));

        nappaimet.add(vuoronVaihto);
        nappaimet.add(vihjeNappula);
        nappaimet.add(tallennaPeli);
        nappaimet.add(takaisinValikkoon);
        nappaimet.add(peruSiirto);

        return nappaimet;
    }

    /**
     * @return the ruudukko
     */
    public RuudukonHallitsija getRuudukko() {
        return ruudukko;
    }
}
