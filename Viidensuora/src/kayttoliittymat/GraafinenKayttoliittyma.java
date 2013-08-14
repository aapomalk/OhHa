/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

/**
 *
 * @author Aapo
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import kayttoliittymat.kuuntelijat.*;
import tiedostojenKasittely.VirheidenKasittelijaGraafinen;

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
    
    private void luoKomponentitTilasto(Container container) {
        container.setLayout(new GridLayout(1, 3));
        container.add(luoNappulat());
        container.add(tulostaTilastoOtsikot());
        container.add(tulostaTilastoja());
    }
    
    private JPanel luoNappulat() {
        JPanel nappulat = new JPanel(new GridLayout(5, 1));
        JButton valikkoon = new JButton("takaisin valikkoon");
        
        valikkoon.addActionListener(new ValikkoonNappulanKuuntelija(this));
        
        nappulat.add(valikkoon);
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
