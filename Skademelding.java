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


/** Klasse for å opprette et skademeldingsıobjekt og inneholder metoder som sjekker om kunden er dekket
 *  for den spesifikke typen forsikring og beregner erstatnings beløpet
 *
 * @author Amir
 */
public class Skademelding implements Serializable {
    
    private Forsikringskunde kunde;  
    private Insurance forsikring;
    //private Date skadedato
    private String skadenummer;
    private String forsikringstype;
    
    private String skadeBeskrivelse;
    private String kontaktInfo;
    private double egenandelsbelop;
    private double takseringsBelop;
    private double erstatningsBelop;
    
    //konstanter for hva en maks kan få i erstatning for de forskjellige forsikringene
    private final double MAXBILERSTATNING = 100000.00;
    private final double MAXBOLIGERSTATNING = 100000.00;
    private final double MAXBAATERSTATNING = 50000.00;
    private final double MAXHYTTEERSTATNING = 70000.00;
    private final double MAXREISEERSTATNING = 20000.00;
    
    //konstanter for hva en må betale (i prosent av totalprisen) i egenandel for å få dekket forsikringen
    private final double BILEGENANDELKONSTANT = 0.2;
    private final double BOLIGEGENANDELKONSTANT = 0.1;
    private final double BAATEGENANDELKONSTANT = 0.2;
    private final double HYTTEEGENANDELKONSTANT = 0.2;
    private final double REISEEGENANDELKONSTANT = 0.3;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    
    public Skademelding(Forsikringskunde kunde, Insurance forsikring, String forsikringstype, String skadeBeskrivelse,
                        double takseringsBeløp)
    {
        this.kunde = kunde;
        this.forsikring = forsikring; 
       //this.skadedato = new Date();
      
        this.forsikringstype = forsikringstype;
        this.skadeBeskrivelse = skadeBeskrivelse; 
        this.takseringsBelop = takseringsBelop;
        erstatningsBelop = 0.00;
        
        
    }

    /*get-metoder*/
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
    
    public double getEgenandel()
    {
        return egenandelsbelop;
    }
    
    //set-metoder
    public void setSkadenummer(String skadenummer)
    {
        this.skadenummer = skadenummer;
    }
    public void setKontaktInfo( String kontaktInfo ){
        this.kontaktInfo = kontaktInfo;
    }

    public void setEgenandelsbelop(double egenandelsbelop) {
        this.egenandelsbelop = egenandelsbelop;
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

                if(forsikring.getType().equals(forsikringstype))
                {
                    if(forsikring instanceof Innboforsikring)
                        return true;
                    else if(forsikring instanceof Bilforsikring)
                        return true;
                    else if(forsikring instanceof Baatforsikring)
                        return true;
                    else if(forsikring instanceof Fritidsboligforsikring)
                        return true;
                    else if(forsikring instanceof Reiseforsikring)
                        return true;
                    else
                        return false;  
                }  
                else 
                    return false;
                
        }
        
        //sjekker om eier og forsikring stemmer overens
        public boolean sjekkEier()
        {
            if(forsikring.getKunde().getPersonNr().equals(kunde.getPersonNr()))
                return true;
            else
                return false;
        }
        
        
   //Metode som beregner erstatning og egenandelsbeløpet for skaden ut i fra takstering og konstanter
        public void beregnErstatning()
        {
             //double premie = forsikring.getPremie();
                double erstatning = 0;
             if(forsikring instanceof Innboforsikring)
             {
                if(takseringsBelop < MAXBOLIGERSTATNING)
                    erstatning = takseringsBelop;
                
                else
                    erstatning = MAXBOLIGERSTATNING; 
                
                setEgenandelsbelop(erstatning*BOLIGEGENANDELKONSTANT);
                erstatning = erstatning - egenandelsbelop;
                setErstatningsBelop(erstatning);
             } 
             
             else if(forsikring instanceof Fritidsboligforsikring)
             {
              if(takseringsBelop < MAXHYTTEERSTATNING)
                    erstatning = takseringsBelop;
                
                else
                    erstatning = MAXHYTTEERSTATNING;
              
                setEgenandelsbelop(erstatning*HYTTEEGENANDELKONSTANT);
                erstatning = erstatning - egenandelsbelop;
                setErstatningsBelop(erstatning);
             }
             
             else if(forsikring instanceof Bilforsikring)
             {
                 if(takseringsBelop < MAXBILERSTATNING)
                    erstatning = takseringsBelop;
                
                else
                    erstatning = MAXBILERSTATNING;
                 
                setEgenandelsbelop(erstatning*BILEGENANDELKONSTANT);
                erstatning = erstatning - egenandelsbelop;
                setErstatningsBelop(erstatning);
             }
             
             else if(forsikring instanceof Baatforsikring)
             {
                 if(takseringsBelop < MAXBAATERSTATNING)
                    erstatning = takseringsBelop;
                
                else
                    erstatning = MAXBAATERSTATNING;
                 
                 setEgenandelsbelop(erstatning*BAATEGENANDELKONSTANT);
                erstatning = erstatning - egenandelsbelop;
                setErstatningsBelop(erstatning);
             }
             
             else if(forsikring instanceof Reiseforsikring)
             {
                if(takseringsBelop < MAXREISEERSTATNING)
                    erstatning = takseringsBelop;
                
                else
                    erstatning = MAXREISEERSTATNING;
                
                setEgenandelsbelop(erstatning*REISEEGENANDELKONSTANT);
                erstatning = erstatning - egenandelsbelop;
                setErstatningsBelop(erstatning);
             }              
        } //slutt på metode
    
    @Override
    public String toString(){
        String tekst = "\nSkadenummer: " + skadenummer 
                       + "\nSkade Beskrivelse: " + skadeBeskrivelse
                       + "\nTakseringsbelop: " + takseringsBelop
                       + "\nUtbetalt Erstatningsbelop: " + erstatningsBelop;
        
        
        return tekst;
        
    }
}