/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author aapomalk
 */
public interface VirheidenKasittelija {
    
    void virheTiedostoonKirjoittamisessa();
    void virheFileWriterinLuomisessa();
    void virheScannerinJaTiedostonKanssa();
    void tiedostoOliKorruptoitunut();
    void tilastojenTallennuksessaOngelmia();
}
