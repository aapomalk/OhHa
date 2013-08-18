/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tilastotJaTunnukset;

import java.util.ArrayList;
import viidensuora.Laatu;

/**
 * Sanalla sanottuna tilastot, sisältää yleiset tilastot, listan kaikista
 * tunnuksista ja toisen listan tunnuspareista
 * @see tilastotJaTunnukset.Tunnus
 * @see tilastotJaTunnukset.TunnusPari
 * @author Aapo
 */
public class TilastoTunnusMuistio {
    private ArrayList<Tunnus> tunnukset;
    private ArrayList<TunnusPari> tunnusParit;
    private int pelienMaara;
    private int ristienVoitot;
    private int nollienVoitot;
    private double pelienKeskimaarainenPituus;
    private int pelienTallennustenMaara;
    /**
     * Luodaan uusi Tilasto tiedostosta luettujen arvojen perusteella
     * @param tunnukset
     * @param tunnusParit
     * @param nollienVoitot
     * @param pelienKeskimaarainenPituus
     * @param pelienMaara
     * @param pelienTallennustenMaara
     * @param ristienVoitot 
     */
    public TilastoTunnusMuistio(ArrayList<Tunnus> tunnukset, ArrayList<TunnusPari> tunnusParit,
            int nollienVoitot, double pelienKeskimaarainenPituus, int pelienMaara, int pelienTallennustenMaara,
            int ristienVoitot) {
        this.tunnukset = tunnukset;
        this.tunnusParit = tunnusParit;
        this.pelienMaara = pelienMaara;
        this.ristienVoitot = ristienVoitot;
        this.nollienVoitot = nollienVoitot;
        this.pelienKeskimaarainenPituus = pelienKeskimaarainenPituus;
        this.pelienTallennustenMaara = pelienTallennustenMaara;
    }
    /**
     * Luodaan uusi tyhjä tilasto, missä ei ole valmiiksi tunnuksia, tunnuspareja
     * tai yleisiä tilastojakaan (paitsi arvolla 0)
     */
    public TilastoTunnusMuistio() {
        this(new ArrayList<Tunnus>(), new ArrayList<TunnusPari>(), 0, 0.0, 0, 0, 0);
    }
    /**
     * Luodaan TilastoTunnusMuistioon uusi tunnus
     * @param tunnusNimi käyttäjän syöttämä teksti, josta tehdään tunnuksen nimi
     * @return true jos nimi lisättiin, false jos kyseinen nimi oli jo jollain tunnuksella
     */
    public boolean lisaaTunnus(String tunnusNimi) {
        for (Tunnus tunnus : getTunnukset()) {
            if (tunnus.getTunnus().equals(tunnusNimi)) {
                return false;
            }
        }
        this.getTunnukset().add(new Tunnus(tunnusNimi));
        return true;
    }
    /**
     * Luodaan olion sisälle uusi Tunnuspari käyttäen valmiita tunnuksia
     * @param tunnus1 käyttäjän syöttämä tunnus
     * @param tunnus2 käyttäjän syöttämä tunnus
     * @return true jos lisääminen tehtiin, false jos samoista tunnuksista muodostuva
     * tunnuspari oli jo olemassa, tai jos tunnukset olivat samat
     */
    public boolean lisaaTunnusPari(Tunnus tunnus1, Tunnus tunnus2) {
        for (TunnusPari tunnusPari : getTunnusParit()) {
            if ((tunnusPari.getTunnus1().equals(tunnus1) || tunnusPari.getTunnus2().equals(tunnus1))
                    && (tunnusPari.getTunnus1().equals(tunnus2) || tunnusPari.getTunnus2().equals(tunnus2))
                    && (!tunnus1.equals(tunnus2))) {
                return false;
            }
        }
        this.getTunnusParit().add(new TunnusPari(tunnus1, tunnus2));
        return true;
    }
    /**
     * Tallennettaessa peliä lisätään pelien tallennusten lukumäärää yhdellä
     */
    public void peliTallennettu() {
        this.pelienTallennustenMaara++;
    }
    /**
     * Tallennettaessa tunnusparin peliä, lisätään tallennusten lukumäärää
     * sekä yleisiin, että tunnusparin tilastoihin
     * @param tunnusPari 
     */
    public void peliTallennettu(TunnusPari tunnusPari) {
        peliTallennettu();
        tunnusPari.lisaaTallennus();
    }
    
    private void lisaaPelattuPeli() {
        this.pelienMaara++;
    }
    /**
     * Pelin päätyttyä lisätään yleisiin tilastoihin pelin pituus ja voittajan laatu
     * @param pelinPituus
     * @param voittajanLaatu 
     */
    public void peliPelattu(double pelinPituus, Laatu voittajanLaatu) {
        lisaaPelattuPeli();
        if (voittajanLaatu.equals(Laatu.RISTI)) {
            this.ristienVoitot++;
        } else {
            this.nollienVoitot++;
        }
        this.pelienKeskimaarainenPituus = ((this.getPelienKeskimaarainenPituus() * (this.getPelienMaara() - 1) 
                + pelinPituus) / this.getPelienMaara());
    }
    /**
     * Pelin päätyttyä muokataan sekä yleisiä tilastoja, että myös tunnusparin ja tunnusten
     * tilastoja
     * @param pelinPituus
     * @param voittajanLaatu
     * @param voittaja
     * @param tunnusPari 
     */
    public void peliPelattu(double pelinPituus, Laatu voittajanLaatu, Tunnus voittaja, TunnusPari tunnusPari) {
        peliPelattu(pelinPituus, voittajanLaatu);
        tunnusPari.kerroKumpiVoitti(voittaja, voittajanLaatu, pelinPituus);
    }

    /**
     * @return the tunnukset
     */
    public ArrayList<Tunnus> getTunnukset() {
        return tunnukset;
    }

    /**
     * @return the tunnusParit
     */
    public ArrayList<TunnusPari> getTunnusParit() {
        return tunnusParit;
    }

    /**
     * @return the pelienMaara
     */
    public int getPelienMaara() {
        return pelienMaara;
    }

    /**
     * @return the ristienVoitot
     */
    public int getRistienVoitot() {
        return ristienVoitot;
    }

    /**
     * @return the nollienVoitot
     */
    public int getNollienVoitot() {
        return nollienVoitot;
    }

    /**
     * @return the pelienKeskimaarainenPituus
     */
    public double getPelienKeskimaarainenPituus() {
        return pelienKeskimaarainenPituus;
    }

    /**
     * @return the pelienTallennustenMaara
     */
    public int getPelienTallennustenMaara() {
        return pelienTallennustenMaara;
    }
    
}
