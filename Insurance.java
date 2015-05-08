/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ssandoy
 */
public abstract class Insurance implements  Forsikring {
    
    private Forsikringskunde kunde;

    private String forsikringsnummer;
    private String forsikringstype;
    private double erstatning;
    private double egenandel;
    private Date startDato;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private double forsikringspremie;
    
    
    
    protected Insurance(Forsikringskunde kunde)
    {
        this.kunde = kunde;
        this.startDato = new Date();
        
        erstatning = 0.00;
        egenandel = 0.00;
        forsikringspremie = 0.00;
    } 
   
    
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
     
     
     
     @Override
     public double getEgenandel()
     {
         return egenandel;
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
     
    

    
    
    
}
