/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

import java.util.ArrayList;
import tiedostojenKasittely.PelitilanteenLukija;
import tiedostojenKasittely.PelitilanteenTallentaja;
import tiedostojenKasittely.TilastojenJaTunnustenTallentaja;
import tiedostojenKasittely.TilastojenLukija;
import tiedostojenKasittely.VirheidenKasittelija;
import tilastotJaTunnukset.TilastoTunnusMuistio;
import viidensuora.Laatu;
import viidensuora.MerkkienJononLoytaja;
import viidensuora.ReunimmaisetKoordinaatit;
import viidensuora.RistiNollaMuistio;

/**
 * Jotta siirtymä käyttöliittymien välillä olisi helpompi, ja jotta copy-paste
 * pysyisi minimissään.
 * @see GraafinenKayttoliittyma
 * @see TekstiKayttoliittyma
 * @author aapomalk
 */
public class Kayttoliittyma {
    
    protected RistiNollaMuistio muistio;
    protected MerkkienJononLoytaja loytaja;
    protected ArrayList<Laatu> viisiRistia;
    protected ArrayList<Laatu> viisiNollaa;
    protected ReunimmaisetKoordinaatit rajaaja;
    protected PelitilanteenTallentaja peliSave;
    protected PelitilanteenLukija peliLoad;
    protected VirheidenKasittelija kasittelija;
    protected TilastoTunnusMuistio tilastot;
    protected boolean jatketaan;
    /**
     * alustetaan molempien käyttöliittymien tarvittavat oliomuuttujat ja otetaan
     * talteen käyttäjän syöttämä virheidenkäsittelijä
     * @param kasittelija käyttäjän syöttämä virheidenkäsittelijä, joko graafinen tai teksti
     */
    public Kayttoliittyma(VirheidenKasittelija kasittelija) {
        loytaja = new MerkkienJononLoytaja();
        jatketaan = true;
        this.kasittelija = kasittelija;
        TilastojenLukija aloitus = new TilastojenLukija();
        tilastot = aloitus.lataaTilastot("Tilastot.txt", kasittelija);
        peliSave = new PelitilanteenTallentaja();
        peliLoad = new PelitilanteenLukija();
        muistio = new RistiNollaMuistio();
        loytajienAlustus();
        rajaaja = new ReunimmaisetKoordinaatit();
    }
    
    public void tallennaTilastot() {
        TilastojenJaTunnustenTallentaja lopetus = new TilastojenJaTunnustenTallentaja();
        lopetus.tallennaTiedostoon(tilastot, "Tilastot.txt", kasittelija);
    }
    
    private void loytajienAlustus() {
        this.viisiRistia = new ArrayList<Laatu>();
        this.viisiNollaa = new ArrayList<Laatu>();
        for (int i = 0; i < 5; i++) {
            viisiRistia.add(Laatu.RISTI);
            viisiNollaa.add(Laatu.NOLLA);
        }
        
    }
    
    public boolean tarkistaVoitto() {
        if (muistio.getEdellinenMerkkiRisti()) {
            if (this.loytaja.tarkastaViimeinen(muistio.getMerkit(), viisiRistia)) {
                return true;
            }
        } else {
            if (this.loytaja.tarkastaViimeinen(muistio.getMerkit(), viisiNollaa)) {
                return true;
            }
        }
        return false;
    }
}
