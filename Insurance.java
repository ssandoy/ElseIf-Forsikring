/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * En abstrakt super-superklasse for forsikringene
 * @author Sander
 */

public abstract class Insurance implements  Forsikring {
    
    private Forsikringskunde kunde;

    private String forsikringsnummer;
    private String forsikringstype;
    private double erstatning;
    private Date startDato;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private double forsikringspremie;
    
    
    
    protected Insurance(Forsikringskunde kunde)
    {
        this.kunde = kunde;
        this.startDato = new Date();
        
        erstatning = 0.00;
        forsikringspremie = 0.00;
    } 
   
    //get-metoder
    @Override
    public String getFNummer() 
    {
        return forsikringsnummer;
    }
    
    public Object getObject()
    {
        return this;
    }
    
    public Forsikringskunde getKunde()
    {
        return kunde;
    }

    @Override
    public double getPremie() 
    {
        return forsikringspremie;
    }

    @Override
    public Date getStartDato() 
    {
      return startDato;
    }

    @Override
    public double getErstatning() 
    {
        return erstatning;
    }
    
    @Override
    public String getType()
    {
        return forsikringstype;
    }
    
    
    //set-metoder
    @Override
     public void setPremie(double premie)
     {
         forsikringspremie = premie;
     }
    
     @Override
     public void setErstatning(double erstatning)
     {
         this.erstatning = erstatning;
     }
     
     

    public void setForsikringsnummer(String forsikringsnummer)
    {
        this.forsikringsnummer = forsikringsnummer;
    }
    
    @Override
    public void setType(String forsikringstype) 
    {
        this.forsikringstype = forsikringstype;
    }
     
    

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String utskrift = "Start Dato: " + dateFormat.format(startDato) + "\nForsikringsnummer: " + forsikringsnummer
                           + "\nForsikringstype: " + forsikringstype + "\nErstatning: " + df.format(erstatning) 
                           + "\nForsikringspremie: " + df.format(forsikringspremie);
        return utskrift;
    }
    
    
}
