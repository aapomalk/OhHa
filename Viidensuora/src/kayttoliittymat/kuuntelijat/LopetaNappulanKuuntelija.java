/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Haluatko varmasti lopettaa? Tämä on viimeinen kysymys, jos vastaat myöntävästi.
 * @author aapomalk
 */
public class LopetaNappulanKuuntelija implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa?") == 0) {
            System.exit(0);
        }
    }
    
}