/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
import java.io.FileWriter;
import java.util.ArrayList;
import tilastotJaTunnukset.*;

public class TilastojenJaTunnustenTallentaja {
    
    public void tallennaTiedostoon(TilastoTunnusMuistio muistio, String tiedosto, VirheidenKasittelija kasittelija) {
        TiedostoonKirjoittaja kirjoittaja = new TiedostoonKirjoittaja();
        ArrayList<String> muistioTekstina = new ArrayList<String>();
        muistioTekstina = yleisetTilastotTekstiksi(muistio, muistioTekstina);
        kirjoittaja.kirjoitaTiedostoon(muistioTekstina, ("Yleiset_tilastot_" + tiedosto), kasittelija);
        
        muistioTekstina = new ArrayList<String>();
        muistioTekstina = tunnuksetTekstiksi(muistio, muistioTekstina);
        kirjoittaja.kirjoitaTiedostoon(muistioTekstina, ("Tunnus_tilastot_" + tiedosto), kasittelija);
        
        muistioTekstina = new ArrayList<String>();
        muistioTekstina = tunnusparitTekstiksi(muistio, muistioTekstina);
        kirjoittaja.kirjoitaTiedostoon(muistioTekstina, ("Tunnuspari_tilastot_" + tiedosto), kasittelija);
    }
    
    private ArrayList<String> tunnusparitTekstiksi(TilastoTunnusMuistio muistio, ArrayList<String> lista) {
        for (TunnusPari tp : muistio.getTunnusParit()) {
            lista.add(tp.getTunnus1().getTunnus() + " " + tp.getTunnus2().getTunnus() + " " +
                    tp.getPelatutPelit() + " " + tp.getPelienKeskimaarainenPituus() + " " +
                    tp.getTallennustenLukumaara() + " " + tp.getTunnus1nRistiPelit() + " " +
                    tp.getTunnus1nVoitot() + " " + tp.getTunnus2nRistiPelit() + " " + tp.getTunnus2nVoitot());
        }
        return lista;
    }
    
    private ArrayList<String> tunnuksetTekstiksi(TilastoTunnusMuistio muistio, ArrayList<String> lista) {
        for (Tunnus tunnus : muistio.getTunnukset()) {
            lista.add(tunnus.getTunnus() + " " + tunnus.getPelatutPelit() + " " + 
                    tunnus.getPelienKeskimaarainenPituus() + " " +
                    tunnus.getRistillaPelatutPelit() + " " + tunnus.getVihjenapinKaytot() + " " +
                    tunnus.getVoitot());
        }
        return lista;
    }
    
    private ArrayList<String> yleisetTilastotTekstiksi(TilastoTunnusMuistio muistio, ArrayList<String> lista) {
        lista.add("Tilastot");
        lista.add(muistio.getNollienVoitot() + " " + muistio.getPelienKeskimaarainenPituus() + " " +
                muistio.getPelienMaara() + " " + muistio.getPelienTallennustenMaara() + " " +
                muistio.getRistienVoitot());
        return lista;
    }
}
