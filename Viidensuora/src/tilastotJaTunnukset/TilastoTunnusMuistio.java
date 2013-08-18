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
    
    public TilastoTunnusMuistio() {
        this(new ArrayList<Tunnus>(), new ArrayList<TunnusPari>(), 0, 0.0, 0, 0, 0);
    }
    
    public boolean lisaaTunnus(String tunnusNimi) {
        for (Tunnus tunnus : getTunnukset()) {
            if (tunnus.getTunnus().equals(tunnusNimi)) {
                return false;
            }
        }
        this.getTunnukset().add(new Tunnus(tunnusNimi));
        return true;
    }
    
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
    
    public void peliTallennettu() {
        this.pelienTallennustenMaara++;
    }
    
    public void peliTallennettu(TunnusPari tunnusPari) {
        peliTallennettu();
        tunnusPari.lisaaTallennus();
    }
    
    private void lisaaPelattuPeli() {
        this.pelienMaara++;
    }
    
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
