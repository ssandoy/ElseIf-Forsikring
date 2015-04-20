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
    private String modell;
    
    
    protected Kjoretoyforsikring(double forsikringspremie
                               , String eier, String registreringsnummer, 
                                String type, String modell)
    {
        super(forsikringspremie);
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
    
    public String getModell()
    {
        return modell;
    }
    
}
