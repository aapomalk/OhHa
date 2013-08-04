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
    
    public VirheidenKasittelija(String kayttoliittymaNimi) {
        this.mikaKayttoliittyma = kayttoliittymaNimi;
    }
    
    public void virheTiedostoonKirjoittamisessa() {
        if (this.mikaKayttoliittyma.equals("TekstiKayttoliittyma")) {
            System.out.println("Tallentaminen ei onnistunut");
        }
    }
    
    public void virheFileWriterinLuomisessa() {
        if (this.mikaKayttoliittyma.equals("TekstiKayttoliittyma")) {
            System.out.println("Tallentamisen alustuksessa tapahtui virhe");
        }
    }
    
    public void virheScannerinJaTiedostonKanssa() {
        if (this.mikaKayttoliittyma.equals("TekstiKayttoliittyma")) {
            System.out.println("Tiedoston lataamisessa tapahtui virhe");
        }
    }
    
    public void tiedostoOliKorruptoitunut() {
        if (this.mikaKayttoliittyma.equals("TekstiKayttoliittyma")) {
            System.out.println("Tiedosto oli korruptoitunut, lataaminen epäonnistui");
        }
    }
}
