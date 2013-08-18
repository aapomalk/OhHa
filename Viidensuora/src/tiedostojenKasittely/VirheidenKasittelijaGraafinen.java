/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 * Graafisen käyttöliittymän ikioma virheidenkäsittelijä
 * @author aapomalk
 */
public class VirheidenKasittelijaGraafinen implements VirheidenKasittelija {

    @Override
    public void virheTiedostoonKirjoittamisessa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void virheFileWriterinLuomisessa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void virheScannerinJaTiedostonKanssa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void tiedostoOliKorruptoitunut() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void tilastojenTallennuksessaOngelmia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
