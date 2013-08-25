/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tilastotJaTunnukset;

/**
 * Olennainen osa tilastoja, sisältää yksilöllisen pelaajan tilastot
 * @author Aapo
 */
public class Tunnus implements Comparable {
    private String tunnus;
    private int pelatutPelit;
    private int voitot;
    private int ristillaPelatutPelit;
    private double pelienKeskimaarainenPituus;
    private int vihjenapinKaytot;
    /**
     * Luodaan uusi tunnus tilastoarvoilla 0
     * @param tunnus 
     */
    public Tunnus(String tunnus) {
        this(tunnus, 0, 0.0, 0, 0, 0);
    }
    /**
     * Luodaan uusi tunnus tiedostosta luettujen arvojen perusteella
     * @param tunnusNimi
     * @param pelit
     * @param pelienKeskimaarainenPituus
     * @param ristillaPelatut
     * @param vihjenapinKaytot
     * @param voittoja 
     */
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
    
    void lisaaVihjenapinKaytto() {
        this.vihjenapinKaytot++;
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
    /**
     * Tunnusten luonnollinen vertailujärjestys liittyy niiden nimeen
     * @param t käyttäjän antama verrattava olio
     * @return 
     */
    @Override
    public int compareTo(Object t) {
        if (t.getClass() != Tunnus.class) {
            return 0;
        }
        Tunnus apu = (Tunnus) t;
        return this.tunnus.compareToIgnoreCase(apu.tunnus);
    }
    
    
}
