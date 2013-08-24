/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kayttoliittymat.kuuntelijat.ValikkoonNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.tilastot.AlasNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.tilastot.JarjestaTunnusNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.tilastot.NaytaTunnuksetNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.tilastot.NaytaTunnusparitNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.tilastot.TunnusTilastoKategoriat;
import kayttoliittymat.kuuntelijat.tilastot.YlosNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.valikko.LopetaNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.valikko.PikapeliNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.valikko.TilastoNappulanKuuntelija;
import kayttoliittymat.kuuntelijat.valikko.TunnusNappulanKuuntelija;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;
import tiedostojenKasittely.VirheidenKasittelijaGraafinen;
import tilastotJaTunnukset.Tunnus;
import tilastotJaTunnukset.TunnusPari;
import viidensuora.Laatu;

/**
 * Jottei merkkejä tarvitsisi syöttää koordinaattikerrallaan, toisinkuin teksti-
 * versiossa.
 * @see TekstiKayttoliittyma
 * @author Aapo
 */
public class GraafinenKayttoliittyma extends Kayttoliittyma implements Runnable {

    private JFrame frame;
    /**
     * alustetaan yliluokka virheidenkäsittelijällä(graafinen)
     */
    public GraafinenKayttoliittyma() {
        super(new VirheidenKasittelijaGraafinen());
    }
    /**
     * rajapinnan Runnable vaatima metodi, jolla käynnistetään käyttöliittymä
     * @see Runnable
     */
    @Override
    public void run() {
        frame = new JFrame("Viidensuora");
        frame.setPreferredSize(new Dimension(1000, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentitValikko(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    /**
     * luodaan tunnus, mikäli tekstin pituus on 3-20 merkkiä ja ilman välilyöntejä,
     * lisäksi tunnus ei saa olla jo olemassa, eli ei samannimisiä tunnuksia
     * @param tunnus käyttäjän syöttämä ehdotus uudeksi tunnukseksi
     */
    public void luoTunnus(String tunnus) {
        if (tunnus == null || tunnus.length() > 20 || tunnus.length() < 3 || tunnus.contains(" ")) {
            JOptionPane.showMessageDialog(frame, "Tunnuksen pituus taytyy olla 3-20 merkkia ilman valia");
            return;
        }
        boolean onnistuiko = super.tilastot.lisaaTunnus(tunnus);
        if (onnistuiko) {
            super.tallennaTilastot();
        } else {
            JOptionPane.showMessageDialog(frame, "Kirjoittamasi tunnus oli jo olemassa,\nlisaaminen epaonnistui");
        }
    }
    /**
     * palataan takaisin alkuvalikkoon
     */
    public void palaaValikkoon() {
        frame.getContentPane().removeAll();
        luoKomponentitValikko(frame.getContentPane());
        frame.pack();
    }
    /**
     * mennään tilastojen alkunäkymään
     */
    public void meneTilastoNakymaan() {
        frame.getContentPane().removeAll();
        luoKomponentitTilasto(frame.getContentPane());
        frame.pack();
    }
    /**
     * mennään tilastoissa tunnusosioon
     */
    public void meneTunnusTilastoihin() {
        frame.getContentPane().removeAll();
        frame.repaint();
        luoKomponentitTunnusTilasto(frame.getContentPane(), 0);
        frame.pack();
    }
    /**
     * luodaan tunnustilastojen komponentit, jos lähtöindeksi on tasan nolla,
     * luodaan myös nappulat (joita ei haluta luoda uudestaan), jos lähtöindeksi
     * ei ole nolla, poistetaan vanhat tilastotulostukset uusien tieltä
     * @param container käyttäjän syöttämä Container
     * @param lahtoIndeksi käyttäjän syöttämä lähtöindeksi, jolla on suuri merkitys
     */
    public void luoKomponentitTunnusTilasto(Container container, int lahtoIndeksi) {
        if (lahtoIndeksi != 0 && container.getComponentCount() >= 0) {
            container.remove(container.getComponentCount() - 1);
        }
        if (lahtoIndeksi == 0) {
            container.setLayout(new BorderLayout());
            container.add(luoTunnusNappuloita(true), BorderLayout.WEST);
            container.add(jarjestyksenVaihtajaNappulat(), BorderLayout.SOUTH);
            container.add(jarjestyksenSelausNappulat(lahtoIndeksi, true), BorderLayout.NORTH);
        } else if (lahtoIndeksi < 0) {
            lahtoIndeksi = 0;
        }
        container.add(tulostaTunnusTilastoja(lahtoIndeksi), BorderLayout.CENTER);
    }

    private JPanel jarjestyksenSelausNappulat(int tilastoIndeksi, boolean trueTunnuksilleFalsePareille) {
        JPanel nappulat = new JPanel(new GridLayout(1, 6));
        nappulat.add(new JLabel(" "));
        nappulat.add(new JLabel(" "));

        JButton alasRullaa = new JButton("V");
        JButton ylosRullaa = new JButton("A");
        
        int listaMax;
        if (trueTunnuksilleFalsePareille) {
            listaMax = this.tilastot.getTunnukset().size();
        } else {
            listaMax = this.tilastot.getTunnusParit().size();
        }
        
        YlosNappulanKuuntelija ylosKuuntelija = new YlosNappulanKuuntelija(this, tilastoIndeksi, listaMax, trueTunnuksilleFalsePareille);
        ylosRullaa.addActionListener(new AlasNappulanKuuntelija(this, tilastoIndeksi, ylosKuuntelija, trueTunnuksilleFalsePareille));

        alasRullaa.addActionListener(ylosKuuntelija);

        nappulat.add(alasRullaa);
        nappulat.add(new JLabel(" "));
        nappulat.add(ylosRullaa);
        nappulat.add(new JLabel(" "));

        return nappulat;
    }

    private JPanel jarjestyksenVaihtajaNappulat() {
        JPanel nappulat = new JPanel(new GridLayout(1, 7));
        nappulat.add(new JLabel(" "));

        JButton tunnus = new JButton("Tunnus");
        JButton pelienLkm = new JButton("Pelien lkm");
        JButton voitot = new JButton("Voitot");
        JButton ristilla = new JButton("Ristilla");
        JButton pituuksienKa = new JButton("Pituuksien ka");
        JButton vihjeNappi = new JButton("Vihjenappi");

        tunnus.addActionListener(new JarjestaTunnusNappulanKuuntelija(this));
        pelienLkm.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.PELIT));
        voitot.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.VOITOT));
        ristilla.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.RISTIT));
        pituuksienKa.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.PITUUS));
        vihjeNappi.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.VIHJEET));

        nappulat.add(tunnus);
        nappulat.add(pelienLkm);
        nappulat.add(voitot);
        nappulat.add(ristilla);
        nappulat.add(pituuksienKa);
        nappulat.add(vihjeNappi);

        return nappulat;
    }

    private JPanel tulostaTunnusTilastoja(int lahtoIndeksi) {
        JPanel tunnusTilastoja = new JPanel();
        tunnusTilastoja.setLayout(new BoxLayout(tunnusTilastoja, BoxLayout.PAGE_AXIS));
        tunnusTilastoja.add(tulostaOtsikotTunnusTilastot());
        for (int i = lahtoIndeksi; i < super.tilastot.getTunnukset().size(); i++) {
            tunnusTilastoja.add(tulostaTunnusTilastot(super.tilastot.getTunnukset().get(i)));
        }

        return tunnusTilastoja;
    }

    private JPanel tulostaOtsikotTunnusTilastot() {
        JPanel otsikot = new JPanel(new GridLayout(1, 6));

        otsikot.add(new JLabel("Tunnukset:"));
        otsikot.add(new JLabel("Pelatut pelit:"));
        otsikot.add(new JLabel("Voitot:"));
        otsikot.add(new JLabel("Ristilla:"));
        otsikot.add(new JLabel("Pituuksien ka:"));
        otsikot.add(new JLabel("Vihjenappi:"));

        return otsikot;
    }

    private JPanel tulostaTunnusTilastot(Tunnus tunnus) {
        JPanel tunnusTilastot = new JPanel(new GridLayout(1, 6));

        tunnusTilastot.add(new JLabel(tunnus.getTunnus()));
        tunnusTilastot.add(new JLabel(tunnus.getPelatutPelit() + ""));
        tunnusTilastot.add(new JLabel(tunnus.getVoitot() + ""));
        tunnusTilastot.add(new JLabel(tunnus.getRistillaPelatutPelit() + ""));
        tunnusTilastot.add(new JLabel("" + tunnus.getPelienKeskimaarainenPituus()));
        tunnusTilastot.add(new JLabel("" + tunnus.getVihjenapinKaytot()));

        return tunnusTilastot;
    }

    private JPanel luoTunnusNappuloita(boolean trueTunnustilastotFalseTunnuspari) {
        JPanel nappulat = new JPanel(new GridLayout(5, 1));
        JButton valikkoon = new JButton("takaisin valikkoon");
        valikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this));
        nappulat.add(valikkoon);

        if (!trueTunnustilastotFalseTunnuspari) {
            JButton naytaTunnukset = new JButton("nayta tunnus tilastot");
            naytaTunnukset.addActionListener(new NaytaTunnuksetNappulanKuuntelija(this));
            nappulat.add(new JLabel());
            nappulat.add(naytaTunnukset);
        }

        JButton naytaYleiset = new JButton("nayta yleiset tilastot");
        naytaYleiset.addActionListener(new TilastoNappulanKuuntelija(this));
        nappulat.add(new JLabel());
        nappulat.add(naytaYleiset);

        if (trueTunnustilastotFalseTunnuspari) {
            JButton naytaTunnusParit = new JButton("nayta tunnusparien tilastot");
            naytaTunnusParit.addActionListener(new NaytaTunnusparitNappulanKuuntelija(this));
            nappulat.add(new JLabel());
            nappulat.add(naytaTunnusParit);
        }

        return nappulat;
    }
    /**
     * mennään tilastoissa tunnuspari osioon
     */
    public void meneTunnuspariTilastoihin() {
        frame.getContentPane().removeAll();
        frame.repaint();
        luoKomponentitTunnuspariTilasto(frame.getContentPane());
        frame.pack();
    }
    
    private void tyhjennaRistiNollaMuistio() {
        while (super.muistio.ristienMaara() > 0) {
            super.muistio.peruSiirto();
        }
    }
    
    public void menePikapeliin() {
        frame.getContentPane().removeAll();
        frame.repaint();
        tyhjennaRistiNollaMuistio();
        PeliHallitsija hallitsija = new PeliHallitsija(super.muistio, this.frame, this);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(hallitsija.kaynnistaPeli());
        frame.pack();
    }

    private void luoKomponentitTunnuspariTilasto(Container container) {

        container.setLayout(new BorderLayout());
        container.add(luoTunnusNappuloita(false), BorderLayout.WEST);
        container.add(jarjestyksenVaihtajaNappulatPareille(), BorderLayout.SOUTH);
        container.add(jarjestyksenSelausNappulat(0, false), BorderLayout.NORTH);

        tulostaTunnuspariTilastoja(0, container);
    }

    private JPanel jarjestyksenVaihtajaNappulatPareille() {
        JPanel nappulat = new JPanel(new GridLayout(1, 7));
        nappulat.add(new JLabel(" "));

        JButton tunnus1 = new JButton("Tunnus1");
        JButton tunnus2 = new JButton("Tunnus2");
        JButton pelienLkm = new JButton("Pelien lkm");
        JButton voitot1 = new JButton("1Voitot");
        JButton ristilla1 = new JButton("1Ristilla");
        JButton voitot2 = new JButton("2Voitot");
        JButton ristilla2 = new JButton("2Ristilla");
        JButton pituuksienKa = new JButton("Pituus ka");
        JButton tallennukset = new JButton("Tallennuksia");

        tunnus1.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS1_PARI));
        tunnus2.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS2_PARI));
        pelienLkm.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.PELIT_PARI));
        voitot1.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS1_VOITOT));
        ristilla1.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS1_RISTIT));
        voitot2.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS2_VOITOT));
        ristilla2.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TUNNUS2_RISTIT));
        pituuksienKa.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.PITUUS_PARI));
        tallennukset.addActionListener(new JarjestaTunnusNappulanKuuntelija(this, TunnusTilastoKategoriat.TALLENNUKSET_PARI));

        nappulat.add(tunnus1);
        nappulat.add(tunnus2);
        nappulat.add(pelienLkm);
        nappulat.add(voitot1);
        nappulat.add(ristilla1);
        nappulat.add(voitot2);
        nappulat.add(ristilla2);
        nappulat.add(pituuksienKa);
        nappulat.add(tallennukset);

        return nappulat;
    }
    /**
     * Tulostetaan tunnusparitilastot, jos lähtöindeksi ei ole nolla, poistetaan
     * vanhat tulostukset uusien tieltä. Nappulat luotiin aikaisemmin, joten
     * niiden uudelleenluomisen vaaraa ei ole
     * @param lahtoIndeksi käyttäjän syöttämä lähtöindeksi, edelleen tärkeä merkitys
     * onko tasan nolla vai jotain muuta, myös negatiivinen on hyväksyttävä
     * @param container käyttäjän syöttämä Container
     */
    public void tulostaTunnuspariTilastoja(int lahtoIndeksi, Container container) {
        if (lahtoIndeksi != 0 && container.getComponentCount() >= 0) {
            container.remove(container.getComponentCount() - 1);
            if (lahtoIndeksi < 0) {
                lahtoIndeksi = 0;
            }
        }
        JPanel tunnusPariTilastoja = new JPanel();
        tunnusPariTilastoja.setLayout(new BoxLayout(tunnusPariTilastoja, BoxLayout.PAGE_AXIS));
        tunnusPariTilastoja.add(tulostaOtsikotTunnusPariTilastot());
        for (int i = lahtoIndeksi; i < super.tilastot.getTunnusParit().size(); i++) {
            tunnusPariTilastoja.add(tulostaTunnusPariTilastot(super.tilastot.getTunnusParit().get(i)));
        }

        container.add(tunnusPariTilastoja);
    }

    private JPanel tulostaOtsikotTunnusPariTilastot() {
        JPanel otsikot = new JPanel(new GridLayout(1, 9));

        otsikot.add(new JLabel("Tunnus1"));
        otsikot.add(new JLabel("Tunnus2"));
        otsikot.add(new JLabel("Pelit"));
        otsikot.add(new JLabel("1Voitot"));
        otsikot.add(new JLabel("1Ristilla"));
        otsikot.add(new JLabel("2Voitot"));
        otsikot.add(new JLabel("2Ristilla"));
        otsikot.add(new JLabel("Pituus ka"));
        otsikot.add(new JLabel("Tallennuksia"));

        return otsikot;
    }

    private JPanel tulostaTunnusPariTilastot(TunnusPari tunnusPari) {
        JPanel tunnusPariTilastot = new JPanel(new GridLayout(1, 9));

        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus1().getTunnus()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus2().getTunnus()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getPelatutPelit()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus1nVoitot()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus1nRistiPelit()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus2nVoitot()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTunnus2nRistiPelit()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getPelienKeskimaarainenPituus()));
        tunnusPariTilastot.add(new JLabel("" + tunnusPari.getTallennustenLukumaara()));

        return tunnusPariTilastot;
    }

    private void luoKomponentitTilasto(Container container) {
        container.setLayout(new GridLayout(1, 5));
        container.add(luoNappulat());
        container.add(new JLabel(" "));
        container.add(tulostaTilastoOtsikot());
        container.add(tulostaTilastoja());
        container.add(new JLabel(" "));
    }

    private JPanel luoNappulat() {
        JPanel nappulat = new JPanel(new GridLayout(5, 1));
        JButton valikkoon = new JButton("takaisin valikkoon");
        JButton naytaTunnukset = new JButton("nayta tunnus tilastot");
        JButton naytaTunnusParit = new JButton("nayta tunnusparien tilastot");

        valikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this));
        naytaTunnukset.addActionListener(new NaytaTunnuksetNappulanKuuntelija(this));
        naytaTunnusParit.addActionListener(new NaytaTunnusparitNappulanKuuntelija(this));

        nappulat.add(valikkoon);
        nappulat.add(new JLabel());
        nappulat.add(naytaTunnukset);
        nappulat.add(new JLabel());
        nappulat.add(naytaTunnusParit);
        return nappulat;
    }

    private JPanel tulostaTilastoja() {
        JPanel tilastoja = new JPanel(new GridLayout(6, 1));
        tilastoja.add(new JLabel());
        tilastoja.add(new JLabel("" + super.tilastot.getPelienMaara()));
        tilastoja.add(new JLabel("" + super.tilastot.getRistienVoitot()));
        tilastoja.add(new JLabel("" + super.tilastot.getNollienVoitot()));
        tilastoja.add(new JLabel("" + super.tilastot.getPelienKeskimaarainenPituus()));
        tilastoja.add(new JLabel("" + super.tilastot.getPelienTallennustenMaara()));
        return tilastoja;
    }

    private JPanel tulostaTilastoOtsikot() {
        JPanel otsikot = new JPanel(new GridLayout(6, 1));
        otsikot.add(new JLabel("Tilastot:"));
        otsikot.add(new JLabel("Pelien maara:"));
        otsikot.add(new JLabel("Ristien voitot:"));
        otsikot.add(new JLabel("Nollien voitot:"));
        otsikot.add(new JLabel("Pelien pituus:"));
        otsikot.add(new JLabel("Tallennusten lkm:"));
        return otsikot;
    }

    private void luoKomponentitValikko(Container container) {
        container.setLayout(new GridLayout(3, 1));
        container.add(new JLabel());
        container.add(luoValikko());
        container.add(new JLabel());
    }

    private JPanel luoValikko() {
        JPanel valikko = new JPanel(new GridLayout(2, 5));
        JButton pikapeli = new JButton("pikapeli");
        JButton kaksinpeli = new JButton("kaksinpeli");
        JButton tilastotNappula = new JButton("tilastot");
        JButton luoTunnus = new JButton("luo tunnus");
        JButton lopeta = new JButton("lopeta");
        
        pikapeli.addActionListener(new PikapeliNappulanKuuntelija(this));
        TilastoNappulanKuuntelija tilastoKuuntelija = new TilastoNappulanKuuntelija(this);
        tilastotNappula.addActionListener(tilastoKuuntelija);
        luoTunnus.addActionListener(new TunnusNappulanKuuntelija(this));
        lopeta.addActionListener(new LopetaNappulanKuuntelija());

        valikko.add(pikapeli);
        valikko.add(new JLabel());
        valikko.add(kaksinpeli);
        valikko.add(new JLabel());
        valikko.add(tilastotNappula);
        valikko.add(new JLabel());
        valikko.add(luoTunnus);
        valikko.add(new JLabel());
        valikko.add(lopeta);
        return valikko;
    }
    
    public void lisaaTilastoihinPikapeli(double pelinPituus, Laatu laatu) {
        super.tilastot.peliPelattu(pelinPituus, laatu);
    }
    
    public void lisaaTilastoihinKaksinpeli(double pelinPituus, Laatu laatu, Tunnus voittajaTunnus, TunnusPari tunnusPari) {
        super.tilastot.peliPelattu(pelinPituus, laatu, voittajaTunnus, tunnusPari);
    }

    public JFrame getFrame() {
        return frame;
    }

    public ArrayList<Tunnus> getTunnukset() {
        return super.tilastot.getTunnukset();
    }

    public ArrayList<TunnusPari> getTunnusParit() {
        return super.tilastot.getTunnusParit();
    }
}
