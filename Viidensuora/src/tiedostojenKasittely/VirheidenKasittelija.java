/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
public class VirheidenKasittelija {
    private String mikaKayttoliittyma;
    private String tekstiK;
    
    public VirheidenKasittelija(String kayttoliittymaNimi) {
        this.mikaKayttoliittyma = kayttoliittymaNimi;
        this.tekstiK = "TekstiKayttoliittyma";
    }
    
    public void virheTiedostoonKirjoittamisessa() {
        if (this.mikaKayttoliittyma.equals(this.tekstiK)) {
            System.out.println("Tallentaminen ei onnistunut");
        }
    }
    
    public void virheFileWriterinLuomisessa() {
        if (this.mikaKayttoliittyma.equals(this.tekstiK)) {
            System.out.println("Tallentamisen alustuksessa tapahtui virhe");
        }
    }
    
    public void virheScannerinJaTiedostonKanssa() {
        if (this.mikaKayttoliittyma.equals(this.tekstiK)) {
            System.out.println("Tiedoston lataamisessa tapahtui virhe");
        }
    }
    
    public void tiedostoOliKorruptoitunut() {
        if (this.mikaKayttoliittyma.equals(this.tekstiK)) {
            System.out.println("Tiedosto oli korruptoitunut, lataaminen epäonnistui");
        }
    }
    
    public void tilastojenTallennuksessaOngelmia() {
        if (this.mikaKayttoliittyma.equals(this.tekstiK)) {
            System.out.println("Tilastojen tallennuksessa sattui virhe");
        }
    }
}
