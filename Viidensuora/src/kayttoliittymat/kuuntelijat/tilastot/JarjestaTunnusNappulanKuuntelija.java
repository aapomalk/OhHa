/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat.kuuntelijat.tilastot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import kayttoliittymat.GraafinenKayttoliittyma;
import tunnustenJaParienJarjestelijat.*;

/**
 * 'Minä' mahdollistan mitä monimuotoisimmat järjestykset tunnuksille ja
 * tunnuspareille, erityisesti siis tilastoja tarkasteltaessa.
 *
 * @author Aapo
 */
public class JarjestaTunnusNappulanKuuntelija implements ActionListener {

    private GraafinenKayttoliittyma liittyma;
    private boolean kumpiOliViimeksi;
    private TunnusTilastoKategoriat kategoria;

    /**
     * tallennetaan käyttöliittymän viite kuuntelijaan ja asetetaan kategoriaksi
     * tunnus
     *
     * @param liittyma käskytettävä käyttöliittymä
     */
    public JarjestaTunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma) {
        this.liittyma = liittyma;
        kumpiOliViimeksi = false;
        kategoria = TunnusTilastoKategoriat.TUNNUS;
    }

    /**
     * tallennetaan kättöliittymän viite ja asetetaan kategoriaksi käyttäjän
     * syöte
     *
     * @param liittyma käyttäjän syöttämä graafinen käyttöliittymä
     * @param kategoria käyttäjän syöttämä kategoria
     */
    public JarjestaTunnusNappulanKuuntelija(GraafinenKayttoliittyma liittyma, TunnusTilastoKategoriat kategoria) {
        this(liittyma);
        this.kategoria = kategoria;
    }

    /**
     * Järjestetään kategorian mukaiset tilastot kategorian mukaiseen
     * järjestykseen ja käsketään käyttöliittymää päivittämään tilastot näkymä
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (kumpiOliViimeksi) {
            jarjestaOikein();
            kumpiOliViimeksi = false;
        } else {
            jarjestaOikein();
            if (kategoria.getTrueTunnusFalsePari()) {
                Collections.reverse(liittyma.getTunnukset());
            } else {
                Collections.reverse(liittyma.getTunnusParit());
            }
            kumpiOliViimeksi = true;
        }
        if (kategoria.getTrueTunnusFalsePari()) {
            liittyma.luoKomponentitTunnusTilasto(this.liittyma.getFrame().getContentPane(), -1);
        } else {
            liittyma.tulostaTunnuspariTilastoja(-1, this.liittyma.getFrame().getContentPane());
        }

        liittyma.getFrame().pack();
    }

    private void jarjestaOikein() {
        if (kategoria.getTrueTunnusFalsePari()) {
            jarjestetaanTunnuksia();
        } else {
            jarjestetaanTunnuspareja();
        }
    }

    private void jarjestetaanTunnuspareja() {
        if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS1_PARI)) {
            Collections.sort(liittyma.getTunnusParit());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS2_PARI)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTunnus2MukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PELIT_PARI)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitPelienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS1_VOITOT)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTunnus1VoitotMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS1_RISTIT)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTunnus1RistienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS2_VOITOT)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTunnus2VoitotMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS2_RISTIT)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTunnus2RistienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PITUUS_PARI)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitPelipituudenMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.TALLENNUKSET_PARI)) {
            Collections.sort(liittyma.getTunnusParit(), new TunnusParitTallennustenMukaanJarjestykseen());
        }
    }

    private void jarjestetaanTunnuksia() {
        if (this.kategoria.equals(TunnusTilastoKategoriat.TUNNUS)) {
            Collections.sort(liittyma.getTunnukset());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PELIT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetPelienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.PITUUS)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetPelipituudenMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.RISTIT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetRistienMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.VIHJEET)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetVihjeidenMukaanJarjestykseen());
        } else if (this.kategoria.equals(TunnusTilastoKategoriat.VOITOT)) {
            Collections.sort(liittyma.getTunnukset(), new TunnuksetVoittojenMukaanJarjestykseen());
        }
    }
}