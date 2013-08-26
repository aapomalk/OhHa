/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import kayttoliittymat.peliGraafisetToimijat.PeliHallitsija;

/**
 *
 * @author Aapo
 */
public class TallennaNappulanKuuntelija implements ActionListener {
    
    private PeliHallitsija hallitsija;
    
    /**
     *
     * @param hallitsija
     */
    public TallennaNappulanKuuntelija(PeliHallitsija hallitsija) {
        this.hallitsija = hallitsija;
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti tallentaa pelin?" + 
                "\nPikapelissa ja kullakin tunnusparilla on vain yksi tallennuspaikka," + 
                "\njos nyt paatat tallentaa, mahdollinen aikaisempi tallennus ylikirjoitetaan") == 0) {
            hallitsija.tallennaPeli();
            JOptionPane.showMessageDialog(null, "Tallennustoiminto suoritettu");
        } else {
            JOptionPane.showMessageDialog(null, "Pelia ei tallennettu");
        }
    }
    
}
