/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

import javax.swing.JOptionPane;

/**
 * Graafisen käyttöliittymän ikioma virheidenkäsittelijä
 * @author aapomalk
 */
public class VirheidenKasittelijaGraafinen implements VirheidenKasittelija {

    @Override
    public void virheTiedostoonKirjoittamisessa() {
        JOptionPane.showMessageDialog(null, "Tiedostoon kirjoittaminen epaonnistui");
    }

    @Override
    public void virheFileWriterinLuomisessa() {
        JOptionPane.showMessageDialog(null, "Tiedostoon kirjoittaminen epaonnistui");
    }

    @Override
    public void virheScannerinJaTiedostonKanssa() {
        JOptionPane.showMessageDialog(null, "Tiedoston lukeminen epaonnistui");
    }

    @Override
    public void tiedostoOliKorruptoitunut() {
        JOptionPane.showMessageDialog(null, "Tiedosto oli korruptoitunut");
    }

    @Override
    public void tilastojenTallennuksessaOngelmia() {
        JOptionPane.showMessageDialog(null, "Tilastojen tallennus epaonnistui");
    }
    
}
