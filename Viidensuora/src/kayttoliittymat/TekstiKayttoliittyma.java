/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymat;

/**
 *
 * @author aapomalk
 */
import java.util.ArrayList;
import java.util.Scanner;
import viidensuora.*;

public class TekstiKayttoliittyma {

    private RistiNollaMuistio muistio;
    private MerkkienJononLoytaja viisiRistia;
    private MerkkienJononLoytaja viisiNollaa;
    private ReunimmaisetKoordinaatit rajaaja;
    private Scanner lukija;

    public TekstiKayttoliittyma() {
        muistio = new RistiNollaMuistio();
        loytajienAlustus();
        rajaaja = new ReunimmaisetKoordinaatit();
        lukija = new Scanner(System.in);
    }

    private void loytajienAlustus() {
        ArrayList<Laatu> ristit = new ArrayList<Laatu>();
        ArrayList<Laatu> nollat = new ArrayList<Laatu>();
        for (int i = 0; i < 5; i++) {
            ristit.add(Laatu.RISTI);
            nollat.add(Laatu.NOLLA);
        }
        viisiRistia = new MerkkienJononLoytaja(ristit);
        viisiNollaa = new MerkkienJononLoytaja(nollat);
    }

    public void kaynnista() {
        while (true) {
            kysyKoordinaatteja();

            tulostaKentta();
            if (tarkistaVoitto()) {
                break;
            }
        }
        System.out.println("Kiitos pelista");
    }

    private void kysyKoordinaatteja() {
        String ristiTaiNolla = "risti";
        if (muistio.getEdellinenMerkkiRisti()) {
            ristiTaiNolla = "nolla";
        }
        System.out.print(ristiTaiNolla + " anna x-koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.print(ristiTaiNolla + " anna y-koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        lisaaKoordinaatit(x, y);
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

    private boolean tarkistaVoitto() {
        if (muistio.getEdellinenMerkkiRisti()) {
            if (!viisiRistia.tarkasta(muistio.getMerkit()).isEmpty()) {
                System.out.println("Risti voitti");
                return true;
            }
        } else {
            if (!viisiNollaa.tarkasta(muistio.getMerkit()).isEmpty()) {
                System.out.println("Nolla voitti");
                return true;
            }
        }
        return false;
    }
}
