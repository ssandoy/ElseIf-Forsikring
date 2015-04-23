/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ssandoy
 */
public abstract class Kjoretoyforsikring extends Insurance implements Serializable
{

    
    private String eier;
    private String registreringsnummer;
    private String type;
    private int modell;

    
    
    protected Kjoretoyforsikring(String eier, String registreringsnummer, 
                                 String type, int modell)
    {
        this.eier = eier;
        this.registreringsnummer = registreringsnummer;
        this.type = type;
        this.modell = modell;
    }
    
    
    public String getEier()
    {
        return eier;
    }
    
    public String getRegNummer()
    {
        return registreringsnummer;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getModell()
    {
        return modell;
    }
    
    public void beregnPremie()
    {
        double premie = 0.00;
         if(modell >= 1950 && modell <= 1970)
         {
             super.setPremie(12000.00);
         }
         else if(modell >= 1971 && modell <= 1990)
         {
             super.setPremie(20000.00); 
         }
         else if(modell >= 1991 && modell <= 2005)
         {
             super.setPremie(30000.00);
         }
         else if(modell >= 2006 && modell <= 2015)
         {
             super.setPremie(40000.00);
         }

    }
    
}
