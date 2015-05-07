/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.Image;
import java.io.Serializable;
import java.text.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 *
 * @author YAAKOUBD
 */
public class Skademelding implements Serializable {
    
    private Forsikringskunde kunde;  
    //private Date skadedato
    private final int snr;
    private static int nesteNr = 1;
    private String skadenummer;
    private String skadeType;
    
    private String skadeBeskrivelse;
    private String kontaktInfo;
    private double takseringsBelop;
    private double erstatningsBelop;
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public Skademelding(Forsikringskunde kunde, String skadeType, String skadeBeskrivelse )
    {
        this.kunde = kunde;
       // this.skadedato = new Date();
        snr = nesteNr++;
        skadenummer = snr+"";
        this.skadeType = skadeType;
        this.skadeBeskrivelse = skadeBeskrivelse;   
        takseringsBelop = 0.00;
        erstatningsBelop = 0.00;
        
        
    }
    
    public String getNr(){
        return skadenummer;
    }
    
    /*public String getDate(){
        return dateFormat.format(skadedato);
    }*/
    
    public String getBeskrivelse(){
        return skadeBeskrivelse;
    }
    
    
    public double getTakseringsBelop()
    {
        return takseringsBelop;
    }
    
    public double getErstatningsBelop()
    {
        return erstatningsBelop;
    }
    
    public void setKontaktInfo( String kontaktInfo ){
        this.kontaktInfo = kontaktInfo;
    }
    
    public void setTakseringsBelop( double takseringsBelop ){
        this.takseringsBelop = takseringsBelop;
    }
    
    public void setErstatningsBelop( double erstatningsBelop ){
        this.erstatningsBelop = erstatningsBelop;
    }
    
        /*Metode som sjekker om kunden har gyldig forsikring til skademeldingen*/
        public boolean sjekkDekning()
        {
           LinkedList<Insurance> forsikringer = kunde.getForsikringer();
           ListIterator it = forsikringer.listIterator();
            while(it.hasNext())
            {
                Insurance f = (Insurance) it.next();

                if(skadeType == "INNBO")
                {
                    if(f instanceof Innboforsikring)
                    {
                        return true;
                    }
                }
                else if(skadeType == "BIL")
                {
                    if(f instanceof Bilforsikring)
                    {
                        return true;
                    }
                }
                 else if(skadeType == "FRITID")
                {
                    if(f instanceof Fritidsboligforsikring)
                    {
                        return true;
                    }
                }
                 else if(skadeType == "BIL")
                {
                    if(f instanceof Bilforsikring)
                    {
                        return true;
                    }
                }

            }
            return false;
        }
        
        public void beregnErstatning()
        {
            
            
        }
    
    @Override
    public String toString(){
        String tekst = "\nSkadenummer: " + skadenummer 
                       + "\nSkade Beskrivelse: " + skadeBeskrivelse
                       + "\nTakseringsbelop: " + takseringsBelop
                       + "\nUtbetalt Erstatningsbelop: " + erstatningsBelop;
        
        
        return tekst;
        
    }
}