/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;


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

    private Forsikringsregister fregister;
    
    protected Kjoretoyforsikring(Forsikringskunde k, String eier, String registreringsnummer, 
                                 String type, int modell)
    {
        super(k);
        this.eier = eier;
        this.registreringsnummer = registreringsnummer;
        this.type = type;
        this.modell = modell;
    }
    
    /*get-metoder*/
    public String getEier()
    {
        return eier;
    }
    
    public String getRegNummer()
    {
        return registreringsnummer;
    }
    
    public String getKjoretoyType()
    {
        return type;
    }
    
    public int getModell()
    {
        return modell;
    }
    
    
    
}
