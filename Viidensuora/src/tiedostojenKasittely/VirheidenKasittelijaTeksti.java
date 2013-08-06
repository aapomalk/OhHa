/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
public class VirheidenKasittelijaTeksti implements VirheidenKasittelija {

    public VirheidenKasittelijaTeksti(String kayttoliittymaNimi) {
    }

    @Override
    public void virheTiedostoonKirjoittamisessa() {
        System.out.println("Tallentaminen ei onnistunut");
    }

    @Override
    public void virheFileWriterinLuomisessa() {
        System.out.println("Tallentamisen alustuksessa tapahtui virhe");

    }

    @Override
    public void virheScannerinJaTiedostonKanssa() {
        System.out.println("Tiedoston lataamisessa tapahtui virhe");
    }

    @Override
    public void tiedostoOliKorruptoitunut() {
        System.out.println("Tiedosto oli korruptoitunut, lataaminen epäonnistui");
    }

    @Override
    public void tilastojenTallennuksessaOngelmia() {
        System.out.println("Tilastojen tallennuksessa sattui virhe");
    }
}
