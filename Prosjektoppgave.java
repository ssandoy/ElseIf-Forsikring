/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Main klasse som er for å kjøre programet og inneholder metoder som er for å serialisere kunde, 
 * forsikring og skademeldingsregisterene
 * @author Sander, Amir
 */

public class Prosjektoppgave 
{

        private static  Kunderegister kregister = new Kunderegister();
        private static  Forsikringsregister fregister = new Forsikringsregister();
        private static  Skademeldingsregister sregister = new Skademeldingsregister();
        
        public static void main(String[] args) {
        // TODO code application logic here
            
       
            
        String[] navn = {"Mads","Sander","Chris","Per","Pål","Eddard","Daenerys","Arya","Melissandre","Jon","Amir"};
        String[] etternavn = {"Karlstad","Sandøy","Askeladd","Stark","Lannister","Snow","Sand","Stormborn","Yaakoubd"};
        String[] addresse = {"Kings landing","Pyke","Winterfell","Casterly Rock","Dorne","Oslo","Dragonstone","Brønnøy","Koppang"};
        int nr = 1;
        
        
        lesKunderegister();
        lesForsikringsregister();
        lesSkademeldingsregister();
        
        //om du vil legge til flere kunder!
        /*for(int i=0;i<10000;i++){
            int n = new Random().nextInt(navn.length);
            int e = new Random().nextInt(etternavn.length);
            int a = new Random().nextInt(addresse.length);
            
            nr++;
            String fnr = nr + "1";
            String name = navn[n];
            String last = etternavn[e];
            String adr = addresse[a];
            Forsikringskunde k = new Forsikringskunde(fnr,name,last,adr,fnr);
            String knr =  kregister.genererNummer();
            k.setKundenummer(knr);
            kregister.leggTil(k);
        }*/
       
       HovedVindu vindu = new HovedVindu(kregister, fregister, sregister);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int hoyde = skjerm.height;

        vindu.setSize((bredde /2)+15, hoyde*3/4);
        vindu.setLocation(skjerm.width / 2 - vindu.getSize().width / 2, skjerm.height / 2 - vindu.getSize().height / 2);
       
       
        vindu.addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        skrivKunderegister();
                        skrivForsikringsregister();
                        skrivSkademeldingsregister();
                        System.exit(0);
                    }
                });
        
    }
   
        //Leser kunderegisteret fra fil
    public static void lesKunderegister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("kundeliste.data"))) {
            kregister = (Kunderegister) innfil.readObject();
            System.out.println("Kunderegister ble lastet!");
        } catch (ClassNotFoundException cnfe) 
        {
            kregister = new Kunderegister();
            System.out.println("Nytt kunderegister ble opprettet!");
        } catch (IOException ioe) {
            kregister = new Kunderegister();
            System.out.println("Nytt kunderegister ble opprettet!");
        }
    }
    
    //skriver kunderegisteret til fil
    public static void skrivKunderegister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("kundeliste.data"))) {
            utfil.writeObject(kregister);
            System.out.println("Kunderegister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Kunderegister ble ikke lagret!");
        }
    }
    
    //leser forsikringsregisteret fra fil
    public static void lesForsikringsregister() 
    {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("forsikringsliste.data"))) 
        {
            fregister = (Forsikringsregister) innfil.readObject();
            System.out.println("Forsikringsregister ble lastet!");
        } catch (ClassNotFoundException cnfe) {

            fregister = new Forsikringsregister();
            System.out.println("Nytt forsikringsregister ble opprettet!");
        } catch (IOException ioe) 
        {
            fregister = new Forsikringsregister();
            System.out.println("Nytt forsikringsregister ble opprettet!");
        }
    }
    
        //skriver forsikringsregisteret til fil
    public static void skrivForsikringsregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("forsikringsliste.data"))) {
            utfil.writeObject(fregister);
            System.out.println("Forsikringsregister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Forsikringsregister ble ikke lagret!");
        }
    }
    
        //leser skademeldingsregisteret fra fil
    public static void lesSkademeldingsregister() {
        try (ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("skademeldingsliste.data"))) {
            sregister = (Skademeldingsregister) innfil.readObject();
            System.out.println("Skademeldingsregister ble lastet!");
        } catch (ClassNotFoundException cnfe) {

            sregister = new Skademeldingsregister();
            System.out.println("Nytt skademeldingsregister ble opprettet!");
        } catch (IOException ioe) {
            sregister = new Skademeldingsregister();
            System.out.println("Nytt skademeldingsregister ble opprettet!");
        }
    }
    
        //skriver skademeldingsregisteret fra fil
    public static void skrivSkademeldingsregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("skademeldingsliste.data"))) {
            utfil.writeObject(sregister);
            System.out.println("Skademeldingsregister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Skademeldingsregister ble ikke lagret!");
        }
    }   
}