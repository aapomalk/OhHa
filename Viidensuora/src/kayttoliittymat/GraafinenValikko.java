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
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GraafinenValikko implements Runnable {

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("Viidensuora");
        frame.setPreferredSize(new Dimension(1000, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new GridLayout(3, 1));
        container.add(new JLabel());
        container.add(luoValikko());
        container.add(new JLabel());
    }
    
    private JPanel luoValikko() {
        JPanel valikko = new JPanel(new GridLayout(2, 5));
        JButton pikapeli = new JButton("pikapeli");
        JButton kaksinpeli = new JButton("kaksinpeli");
        JButton tilastot = new JButton("tilastot");
        JButton luoTunnus = new JButton("luo tunnus");
        JButton lopeta = new JButton("lopeta");
        
        valikko.add(pikapeli);
        valikko.add(new JLabel());
        valikko.add(kaksinpeli);
        valikko.add(new JLabel());
        valikko.add(tilastot);
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
