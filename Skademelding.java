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
    private Date skadedato;
    private String skadeNr;
    private int snr;
    private String skadeType;
    
    private String skadeBeskrivelse;
    private String kontaktInfo;
    private double takseringsBelop;
    private double erstatningsBelop;
    private Image bilde;
    private int nesteNr = 1;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public Skademelding(Forsikringskunde kunde, String skadeType, String skadeBeskrivelse )
    {
        this.kunde = kunde;
        this.skadedato = new Date();
        snr = nesteNr++;
        skadeNr = snr+"";
        this.skadeType = skadeType;
        this.skadeBeskrivelse = skadeBeskrivelse;    
        
    }
    
    public String getNr(){
        return skadeNr;
    }
    
    public String getDate(){
        return dateFormat.format(skadedato);
    }
    
    public String getBeskrivelse(){
        return skadeBeskrivelse;
    }
    
    public Image getImage(){
        return bilde;
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
           LinkedList<Forsikring> forsikringer = kunde.getForsikringer();
           ListIterator it = forsikringer.listIterator();
            while(it.hasNext())
            {
                Forsikring f = (Forsikring) it.next();

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
    
    @Override
    public String toString(){
        String tekst = "Dato for skade: " + getDate() 
                       + "\nSkadenummer: " + skadeNr 
                       + "\nSkade Beskrivelse: " + skadeBeskrivelse
                       + "\nTakseringsbelop: " + takseringsBelop
                       + "\nUtbetalt Erstatningsbelop: " + erstatningsBelop;
        if( kontaktInfo != null )
            tekst += "\nKontakt Info: " + kontaktInfo;
        
        return tekst;
        
    }
}