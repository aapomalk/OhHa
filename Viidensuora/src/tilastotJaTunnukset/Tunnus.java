/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tilastotJaTunnukset;

/**
 *
 * @author Aapo
 */
public class Tunnus {
    private String tunnus;
    private int pelatutPelit;
    private int voitot;
    private int ristillaPelatutPelit;
    private double pelienKeskimaarainenPituus;
    private int vihjenapinKaytot;
    
    public Tunnus(String tunnus) {
        this(tunnus, 0, 0.0, 0, 0, 0);
    }
    
    public Tunnus(String tunnusNimi, int pelit, double pelienKeskimaarainenPituus, int ristillaPelatut, 
             int vihjenapinKaytot, int voittoja) {
    this.tunnus = tunnusNimi;
    this.pelatutPelit = pelit;
    this.voitot = voittoja;
    this.ristillaPelatutPelit = ristillaPelatut;
    this.pelienKeskimaarainenPituus = pelienKeskimaarainenPituus;
    this.vihjenapinKaytot = vihjenapinKaytot;
}
    
    private void lisaaPeli() {
        this.pelatutPelit++;
    }
    
    void lisaaVoitto() {
        this.voitot++;
    }
    
    void lisaaRistiPeli() {
        this.ristillaPelatutPelit++;
    }
    
    void syotaPelinPituus(double pelinPituus) {
        lisaaPeli();
        this.pelienKeskimaarainenPituus = (this.pelienKeskimaarainenPituus *
                (this.pelatutPelit - 1) + pelinPituus) / this.pelatutPelit;
    }
    
    public String getTunnus() {
        return this.tunnus;
    }

    /**
     * @return the pelatutPelit
     */
    public int getPelatutPelit() {
        return pelatutPelit;
    }

    /**
     * @return the voitot
     */
    public int getVoitot() {
        return voitot;
    }

    /**
     * @return the ristillaPelatutPelit
     */
    public int getRistillaPelatutPelit() {
        return ristillaPelatutPelit;
    }

    /**
     * @return the pelienKeskimaarainenPituus
     */
    public double getPelienKeskimaarainenPituus() {
        return pelienKeskimaarainenPituus;
    }

    /**
     * @return the vihjenapinKaytot
     */
    public int getVihjenapinKaytot() {
        return vihjenapinKaytot;
    }
    
    
}
