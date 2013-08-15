/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

/**
 *
 * @author Aapo
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import kayttoliittymat.kuuntelijat.*;
import tiedostojenKasittely.VirheidenKasittelijaGraafinen;
import viidensuora.Laatu;
import tilastotJaTunnukset.Tunnus;

public class GraafinenKayttoliittyma extends Kayttoliittyma implements Runnable {

    private JFrame frame;
    
    public GraafinenKayttoliittyma() {
        super(new VirheidenKasittelijaGraafinen());
    }

    @Override
    public void run() {
        frame = new JFrame("Viidensuora");
        frame.setPreferredSize(new Dimension(1000, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentitValikko(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoTunnus(String tunnus) {
        if (tunnus == null || tunnus.length() > 20 || tunnus.length() < 3) {
            JOptionPane.showMessageDialog(frame, "Tunnuksen pituus taytyy olla 3-20 merkkia");
            return;
        }
        super.tilastot.lisaaTunnus(tunnus);
        super.tallennaTilastot();
    }
    
    public void palaaValikkoon() {
        frame.getContentPane().removeAll();
        luoKomponentitValikko(frame.getContentPane());
        frame.pack();
    }
    
    public void meneTilastoNakymaan() {
        frame.getContentPane().removeAll();
        luoKomponentitTilasto(frame.getContentPane());
        frame.pack();
    }
    
    public void meneTunnusTilastoihin() {
        frame.getContentPane().removeAll();
        frame.repaint();
        luoKomponentitTunnusTilasto(frame.getContentPane());
        frame.pack();
    }
    
    private void luoKomponentitTunnusTilasto(Container container) {
        container.setLayout(new BorderLayout());
        
        ScrollattavaPaneeli testi = new ScrollattavaPaneeli(new BorderLayout());
        testi.add(luoTunnusNappuloita(), BorderLayout.WEST);
        testi.add(tulostaTunnusTilastot());
        JScrollPane testi2 = new JScrollPane(testi);
        testi2.setPreferredSize(new Dimension(20, 700));
        testi2.setAutoscrolls(true);
        testi2.setWheelScrollingEnabled(true);
        testi2.setVisible(true);
        
        container.add(testi2, BorderLayout.EAST);
        
        container.add(testi);
    }
    
    private JPanel tulostaTunnusTilastot() {
        JPanel tunnusTilastoja = new JPanel();
        tunnusTilastoja.setLayout(new BoxLayout(tunnusTilastoja, BoxLayout.PAGE_AXIS));
        tunnusTilastoja.add(tulostaOtsikotTunnusTilastot());
        for (Tunnus tunnus : super.tilastot.getTunnukset()) {
            tunnusTilastoja.add(tulostaTunnusTilastot(tunnus));
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
    
    private JPanel luoTunnusNappuloita() {
        JPanel nappulat = new JPanel(new GridLayout(5, 1));
        JButton valikkoon = new JButton("takaisin valikkoon");
        JButton naytaYleiset = new JButton("nayta yleiset tilastot");
        JButton naytaTunnusParit = new JButton("nayta tunnusparien tilastot");
        
        valikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this));
        naytaYleiset.addActionListener(new TilastoNappulanKuuntelija(this));
        
        nappulat.add(valikkoon);
        nappulat.add(new JLabel());
        nappulat.add(naytaYleiset);
        nappulat.add(new JLabel());
        nappulat.add(naytaTunnusParit);
        return nappulat;
    }
    
    public void meneTunnuspariTilastoihin() {
        frame.getContentPane().removeAll();
        luoKomponentitTunnuspariTilasto(frame.getContentPane());
        frame.pack();
    }
    
    private void luoKomponentitTunnuspariTilasto(Container container) {
        
    }
    
    private void luoKomponentitTilasto(Container container) {
        container.setLayout(new GridLayout(1, 3));
        container.add(luoNappulat());
        container.add(tulostaTilastoOtsikot());
        container.add(tulostaTilastoja());
    }
    
    private JPanel luoNappulat() {
        JPanel nappulat = new JPanel(new GridLayout(5, 1));
        JButton valikkoon = new JButton("takaisin valikkoon");
        JButton naytaTunnukset = new JButton("nayta tunnus tilastot");
        JButton naytaTunnusParit = new JButton("nayta tunnusparien tilastot");
        
        valikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this));
        naytaTunnukset.addActionListener(new NaytaTunnuksetNappulanKuuntelija(this));
        
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

    public JFrame getFrame() {
        return frame;
    }
}
