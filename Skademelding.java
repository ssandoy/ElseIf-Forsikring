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
    private int skadeNr;
    private String skadeType;
    
    private String skadeBeskrivelse;
    private String kontaktInfo;
    private int takseringsBelop;
    private int erstatningsBelop;
    private Image bilde;
    private int nesteNr = 1;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public Skademelding(Forsikringskunde kunde, String skadeType, String skadeBeskrivelse )
    {
        this.kunde = kunde;
        this.skadedato = new Date();
        skadeNr = nesteNr++;
        this.skadeType = skadeType;
        this.skadeBeskrivelse = skadeBeskrivelse;    
        
    }
    
    public int getNr(){
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
    
    public void setKontaktInfo( String kontaktInfo ){
        this.kontaktInfo = kontaktInfo;
    }
    
    public void setTakseringsBelop( int takseringsBelop ){
        this.takseringsBelop = takseringsBelop;
    }
    
    public void setErstatningsBelop( int erstatningsBelop ){
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