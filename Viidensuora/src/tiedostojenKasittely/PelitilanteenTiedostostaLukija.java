/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostojenKasittely;

/**
 *
 * @author Aapo
 */
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import viidensuora.RistiNollaMuistio;

public class PelitilanteenTiedostostaLukija {
    private File tiedosto;
    private Scanner lukija;
    
    public void lataaPelitilanne(String tiedostonNimi, VirheidenKasittelija kasittelija, RistiNollaMuistio muistio) {
        try {
            tiedosto = new File(tiedostonNimi);
            lukija = new Scanner(tiedosto);
            tyhjennaMuistio(muistio);
            lueTiedosto(muistio, kasittelija);
            lukija.close();
        } catch (Exception e) {
            kasittelija.virheScannerinJaTiedostonKanssa();
        }
    }
    
    private void lueTiedosto(RistiNollaMuistio muistio, VirheidenKasittelija kasittelija) {
        ArrayList<String> merkitStringina = new ArrayList<String>();
        while (lukija.hasNextLine()) {
            merkitStringina.add(lukija.nextLine());
        }
        //salauksen purku, jos jaksan tehda
        for (String string : merkitStringina) {
            paloitteleString(string, muistio, kasittelija);
        }
    }
    
    private void paloitteleString(String string, RistiNollaMuistio muistio, VirheidenKasittelija kasittelija) {
        if (!string.matches("(RISTI|NOLLA) ((|-)[0-9]+ ){2}")) {
            kasittelija.tiedostoOliKorruptoitunut();
            return;
        }
        Scanner paloittelija = new Scanner(string);
        
        String laatu = paloittelija.next();
        int x = Integer.parseInt(paloittelija.next());
        int y = Integer.parseInt(paloittelija.next());
        lisaaMerkki(muistio, laatu, x, y, kasittelija);
    }
    
    private void lisaaMerkki(RistiNollaMuistio muistio, String laatu, int x, int y, VirheidenKasittelija kasittelija) {
        if (laatu.equals("RISTI")) {
            muistio.lisaaRisti(x, y);
        } else if (laatu.equals("NOLLA")) {
            muistio.lisaaNolla(x, y);
        } else {
            kasittelija.tiedostoOliKorruptoitunut();
        }
    }
    
    protected void tyhjennaMuistio(RistiNollaMuistio muistio) {
        while (muistio.ristienMaara() > 0) {
            muistio.peruSiirto();
        }
    }
}
