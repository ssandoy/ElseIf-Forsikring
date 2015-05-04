/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

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
 *
 * @author ssandoy
 */
public class Prosjektoppgave 
{

        private static  Kunderegister kregister = new Kunderegister();
        private static  Forsikringsregister fregister = new Forsikringsregister();
        private static  Skademeldingsregister sregister = new Skademeldingsregister();
    
    public static void main(String[] args) {
        // TODO code application logic here
        

        String[] navn = {"Mads","Sander","Chris","Per","Pål","Eddard","Daenerys","Arya","Melissandre","Jon"};
        String[] etternavn = {"Karlstad","Sandøy","Askeladd","Stark","Lannister","Snow","Sand","Stormborn"};
        String[] addresse = {"Kings landing","Pyke","Winterfell","Casterly Rock","Dorne","Oslo","Dragonstone","Brønnøy","Koppang"};
        int nr = 1;
        
        
        lesKunderegister();
        lesForsikringsregister();
        
       /* for(int i=0;i<1000;i++){
            int n = new Random().nextInt(navn.length);
            int e = new Random().nextInt(etternavn.length);
            int a = new Random().nextInt(addresse.length);
            
            nr++;
            String fnr = nr + "1";
            String name = navn[n];
            String last = etternavn[e];
            String adr = addresse[a];
            Forsikringskunde k = new Forsikringskunde(fnr,name,last,adr,adr);
            
            kregister.leggTil(k);
        }
        */
       HovedVindu vindu = new HovedVindu(kregister, fregister);
      
        vindu.addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        skrivKunderegister();
                        skrivForsikringsregister();
                        System.exit(0);
                    }
                });
        
    }
   
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
    
    public static void skrivKunderegister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("kundeliste.data"))) {
            utfil.writeObject(kregister);
            System.out.println("Kunderegister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Kunderegister ble ikke lagret!");
        }
    }
    
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
    
    public static void skrivForsikringsregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("forsikringsliste.data"))) {
            utfil.writeObject(fregister);
            System.out.println("Kunderegister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Kunderegister ble ikke lagret!");
        }
    }
    
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
    
    public static void skrivSkademeldingsregister() {
        try (ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("skademeldingsliste.data"))) {
            utfil.writeObject(sregister);
            System.out.println("Skademeldingsregister ble lagret!");
        } catch (IOException ioe) {
            System.out.println("Skademeldingsregister ble ikke lagret!");
        }
    }
        
}
    




