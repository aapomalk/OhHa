/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

/**
 *
 * @author aapomalk
 */
import java.util.Scanner;
import tiedostojenKasittely.*;
import viidensuora.*;

public class TekstiKayttoliittyma extends Kayttoliittyma {
    private Scanner lukija;

    public TekstiKayttoliittyma() {
        super(new VirheidenKasittelijaTeksti());
        lukija = new Scanner(System.in);
        tulostaTilastot();
    }
    
    private void tulostaTilastot() {
        System.out.println("Pelien maara: " + tilastot.getPelienMaara());
        System.out.println("Pelien keskimaarainen pituus: " + tilastot.getPelienKeskimaarainenPituus());
        System.out.println("Ristien voitot: " + tilastot.getRistienVoitot());
        System.out.println("Nollien voitot: " + tilastot.getNollienVoitot());
        System.out.println("Pelien tallennusten maara: " + tilastot.getPelienTallennustenMaara());
    }

    public void kaynnista() {
        while (true) {
            kysyKoordinaatteja();
            
            if (!jatketaan) {
                break;
            }
            tulostaKentta();
            if (tarkistaVoitto()) {
                break;
            }
        }
        
        super.tallennaTilastot();
        System.out.println("Kiitos pelista");
    }
    
    private void annaKomento() {
        System.out.println("Komentoja ovat:");
        System.out.println("lopeta - lopettaa pelin kierroksen jalkeen");
        System.out.println("tallenna - tallentaa pelin");
        System.out.println("lataa - lataa pelin");
        System.out.println("tilastot - tulostaa yleiset tilastot");
        System.out.println("ruudukko - tulostaa pelitilanteen");
        tulkitseKomento();
    }
    
    private void tulkitseKomento() {
        System.out.print("anna komento: ");
        String komento = lukija.nextLine();
        if (komento.equals("tallenna")) {
            tilastot.peliTallennettu();
            peliSave.tallennaPelitilanne(muistio.getMerkit(), "tallennus.txt", kasittelija);
        } else if (komento.equals("lataa")) {
            peliLoad.lataaPelitilanne("tallennus.txt", kasittelija, muistio);
        } else if (komento.equals("tilastot")) {
            tulostaTilastot();
        } else if (komento.equals("ruudukko")) {
            tulostaKentta();
        } else if (komento.equals("lopeta")) {
            this.jatketaan = false;
        }
    }

    private void kysyKoordinaatteja() {
        String ristiTaiNolla = "risti";
        if (muistio.getEdellinenMerkkiRisti()) {
            ristiTaiNolla = "nolla";
        }
        System.out.print(ristiTaiNolla + " anna x-koordinaatti: ");
        String xTeksti = (lukija.nextLine());
        System.out.print(ristiTaiNolla + " anna y-koordinaatti: ");
        String yTeksti = (lukija.nextLine());
        int x;
        int y;
        if (xTeksti.matches("(|-)[0-9]+") && yTeksti.matches("(|-)[0-9]+")) {
            x = Integer.parseInt(xTeksti);
            y = Integer.parseInt(yTeksti);
            lisaaKoordinaatit(x, y);
        } else {
            System.out.println("Syota koordinaatit oikein");
            annaKomento();
            kysyKoordinaatteja();
        }
    }

    private void lisaaKoordinaatit(int x, int y) {
        int merkkienMaara = muistio.nollienMaara() + muistio.ristienMaara();
        if (muistio.getEdellinenMerkkiRisti()) {
            muistio.lisaaNolla(x, y);
        } else {
            muistio.lisaaRisti(x, y);
        }
        if (merkkienMaara == muistio.nollienMaara() + muistio.ristienMaara()) {
            System.out.println("Merkin lisaaminen epaonnistui");
            kysyKoordinaatteja();
        }
    }

    private void tulostaKentta() {
        rajaaja.etsiKoordinaatit(muistio.getMerkit());
        for (int i = rajaaja.getSuurinY(); i >= rajaaja.getPieninY(); i--) {
            for (int j = rajaaja.getPieninX(); j <= rajaaja.getSuurinX(); j++) {
                boolean loytyiko = false;
                for (Merkki merkki : muistio.getMerkit()) {

                    if (merkki.getX() == j && merkki.getY() == i) {
                        loytyiko = true;
                        if (merkki.getLaatu().equals(Laatu.RISTI)) {
                            System.out.print("X");
                        } else {
                            System.out.print("O");
                        }
                    }

                }
                if (!loytyiko) {
                    System.out.print(" ");
                }
            }
            System.out.println(i);
        }
        tulostaAlaKoordinaatit();
        
    }
    
    private void tulostaAlaKoordinaatit() {
        for (int i = rajaaja.getPieninX(); i <= rajaaja.getSuurinX(); i++) {
            if (i < 0) {
                if (i >= -10) {
                    if (i % 2 == 0) {
                        System.out.print("-");
                    } else {
                        System.out.print(Math.abs(i));
                    }
                } else if (i > -100) {
                    if (Math.abs(i) % 3 == 0) {
                        if (Math.abs(i)%10 == 0) {
                            System.out.print((Math.abs(i)/10-1));
                        } else {
                            System.out.print((Math.abs(i)/10));
                        }
                    } else if (Math.abs(i)%3 == 1) {
                        System.out.print("-");
                    } else {
                        System.out.print((Math.abs(i)-10*(Math.abs(i)/10)));
                    }
                } else {
                    System.out.print(" ");
                }

            } else {
                System.out.print(i);
            }

        }
        System.out.println("");
    }
}
