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

public class TilastojenJaTunnustenTiedostoonTallentaja {
    private FileWriter kirjoittaja;
    
    public void tallennaTiedostoon(TilastoTunnusMuistio muistio, String tiedosto, VirheidenKasittelija kasittelija) {
        ArrayList<String> muistioTekstina = new ArrayList<String>();
        muistioTekstina = yleisetTilastotTekstiksi(muistio, muistioTekstina);
        muistioTekstina.add(" ");
        muistioTekstina = tunnuksetTekstiksi(muistio, muistioTekstina);
        muistioTekstina.add(" ");
        muistioTekstina = tunnusparitTekstiksi(muistio, muistioTekstina);
        kirjoitaTiedostoon(muistioTekstina, tiedosto, kasittelija);
    }
    
    private void kirjoitaTiedostoon(ArrayList<String> muistio, String tiedosto, VirheidenKasittelija kasittelija) {
        try {
            kirjoittaja = new FileWriter(tiedosto);
            for (String string : muistio) {
                kirjoittaja.write(string + "\n");
            }
            kirjoittaja.close();
        } catch (Exception e) {
            kasittelija.tilastojenTallennuksessaOngelmia();
        }
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
