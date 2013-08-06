/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tilastotJaTunnukset;

/**
 *
 * @author Aapo
 */
import viidensuora.Laatu;

public class TunnusPari {

    private Tunnus tunnus1;
    private Tunnus tunnus2;
    private int pelatutPelit;
    private int tunnus1nVoitot;
    private int tunnus1nRistiPelit;
    private int tunnus2nVoitot;
    private int tunnus2nRistiPelit;
    private double pelienKeskimaarainenPituus;
    private int tallennustenLukumaara;

    public TunnusPari(Tunnus tunnus1, Tunnus tunnus2) {
        this(tunnus1, tunnus2, 0, 0, 0, 0, 0, 0.0, 0);
    }

    public TunnusPari(Tunnus tunnus1, Tunnus tunnus2, int pelatutPelit, int tunnus1nVoitot,
            int tunnus1nRistiPelit, int tunnus2nVoitot, int tunnus2nRistiPelit,
            double pelienKeskimaarainenPituus, int tallennustenLukumaara) {
        this.tunnus1 = tunnus1;
        this.tunnus2 = tunnus2;
        this.pelatutPelit = pelatutPelit;
        this.tunnus1nVoitot = tunnus1nVoitot;
        this.tunnus1nRistiPelit = tunnus1nRistiPelit;
        this.tunnus2nVoitot = tunnus2nVoitot;
        this.tunnus2nRistiPelit = tunnus2nRistiPelit;
        this.pelienKeskimaarainenPituus = pelienKeskimaarainenPituus;
        this.tallennustenLukumaara = tallennustenLukumaara;
    }

    private void lisaaPeli() {
        this.pelatutPelit++;
    }

    private void kerroPelinPituus(double pelinPituus) {
        lisaaPeli();
        getTunnus1().syotaPelinPituus(pelinPituus);
        getTunnus2().syotaPelinPituus(pelinPituus);
        this.pelienKeskimaarainenPituus = (this.getPelienKeskimaarainenPituus() * (this.getPelatutPelit() - 1)
                + pelinPituus) / this.getPelatutPelit();
    }

    void kerroKumpiVoitti(Tunnus tunnus, Laatu laatu, double pelinPituus) {
        if (tunnus.equals(this.getTunnus1()) || tunnus.equals(this.getTunnus2())) {
            kerroPelinPituus(pelinPituus);
            if (tunnus.equals(this.getTunnus1())) {
                this.getTunnus1().lisaaVoitto();
                this.tunnus1nVoitot++;
                if (laatu.equals(Laatu.RISTI)) {
                    tunnus1RistiPeli();
                } else {
                    tunnus2RistiPeli();
                }
            } else {
                this.getTunnus2().lisaaVoitto();
                this.tunnus2nVoitot++;
                if (laatu.equals(Laatu.RISTI)) {
                    tunnus2RistiPeli();
                } else {
                    tunnus1RistiPeli();
                }
            }
        }
    }

    private void tunnus1RistiPeli() {
        this.tunnus1.lisaaRistiPeli();
        this.tunnus1nRistiPelit++;
    }

    private void tunnus2RistiPeli() {
        this.tunnus2.lisaaRistiPeli();
        this.tunnus2nRistiPelit++;
    }

    void lisaaTallennus() {
        this.tallennustenLukumaara++;
    }

    /**
     * @return the tunnus1
     */
    public Tunnus getTunnus1() {
        return tunnus1;
    }

    /**
     * @return the tunnus2
     */
    public Tunnus getTunnus2() {
        return tunnus2;
    }

    /**
     * @return the pelatutPelit
     */
    public int getPelatutPelit() {
        return pelatutPelit;
    }

    /**
     * @return the tunnus1nVoitot
     */
    public int getTunnus1nVoitot() {
        return tunnus1nVoitot;
    }

    /**
     * @return the tunnus1nRistiPelit
     */
    public int getTunnus1nRistiPelit() {
        return tunnus1nRistiPelit;
    }

    /**
     * @return the tunnus2nVoitot
     */
    public int getTunnus2nVoitot() {
        return tunnus2nVoitot;
    }

    /**
     * @return the tunnus2nRistiPelit
     */
    public int getTunnus2nRistiPelit() {
        return tunnus2nRistiPelit;
    }

    /**
     * @return the pelienKeskimaarainenPituus
     */
    public double getPelienKeskimaarainenPituus() {
        return pelienKeskimaarainenPituus;
    }

    /**
     * @return the tallennustenLukumaara
     */
    public int getTallennustenLukumaara() {
        return tallennustenLukumaara;
    }
}