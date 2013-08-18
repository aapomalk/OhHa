/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 * Tekstikäyttöliittymän ikioma virheidenkäsittelijä, tulostaa virheilmoituksen
 * virheen sattuessa
 * @see kayttoliittymat.TekstiKayttoliittyma
 * @author Aapo
 */
public class VirheidenKasittelijaTeksti implements VirheidenKasittelija {
    /**
     * Ilmoitetaan virheestä, kun tallentaminen ei onnistunut
     */
    @Override
    public void virheTiedostoonKirjoittamisessa() {
        System.out.println("Tallentaminen ei onnistunut");
    }
    /**
     * Ilmoitetaan virheestä, kun tallentaminen ei päässyt edes alkamaan
     */
    @Override
    public void virheFileWriterinLuomisessa() {
        System.out.println("Tallentamisen alustuksessa tapahtui virhe");

    }
    /**
     * Ilmoitetaan virheestä, kun tiedoston lataaminen epäonnistuu
     */
    @Override
    public void virheScannerinJaTiedostonKanssa() {
        System.out.println("Tiedoston lataamisessa tapahtui virhe");
    }
    /**
     * Ilmoitetaan virheestä, kun lataaminen epäonnistuu tiedoston sisällöstä johtuen
     */
    @Override
    public void tiedostoOliKorruptoitunut() {
        System.out.println("Tiedosto oli korruptoitunut, lataaminen epäonnistui");
    }
    /**
     * Ilmoitetaan virheestä, kun tilastojen tallentaminen epäonnistuu
     */
    @Override
    public void tilastojenTallennuksessaOngelmia() {
        System.out.println("Tilastojen tallennuksessa sattui virhe");
    }
}
