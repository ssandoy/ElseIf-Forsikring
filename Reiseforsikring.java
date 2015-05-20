/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;

/**
 * Klasse som oppretter et reiseforsikringsobjekt og beregner forsikring for den
 * @author Sander, Amir
 */

public class Reiseforsikring extends Personforsikring implements Serializable
{
    private String omraade;
    
    private Forsikringsregister fregister;
    
    //beregningskonstanter for årlig premie
    private static int INNLANDSKONSTANT = 300;
    private static int SKANDINAVIAKONSTANT = 500;
    private static int EUROPAKONSTANT = 1000;
    private static int VEDENKONSTANT = 1500;
    
    private static double VOKSENRABATT = 1.00;
    private static double STUDENTRABATT = 0.80;
    private static double HONNORRABBATT = 0.75;
    
    public Reiseforsikring(Forsikringskunde kunde, String status, String omraade) 
    {
        super(kunde, status);
        this.omraade = omraade;
        
        
    }
    
    //Beregner premien ut i fra hvor forsikringen skal gjelde, og om du har krav på rabatt eller ikke
    public void beregnPremie()
    {
        double premie = 0.00;
        if(omraade.equals("INNLANDS"))
        {
            premie += INNLANDSKONSTANT;
        }else if(omraade.equals("SKANDINAVIA"))
        {
            premie += SKANDINAVIAKONSTANT;
        }else if(omraade.equals("EUROPA"))
        {
            premie += EUROPAKONSTANT;
        }else if(omraade.equals("VERDEN"))
        {
            premie += VEDENKONSTANT;
        }
        
        if(getStatus().equals("Voksen"))
        {
            premie *= VOKSENRABATT;
        }else if(getStatus().equals("Student"))
        {
             premie *= STUDENTRABATT;
        }else if(getStatus().equals("Honnør"))
        {
            premie *= HONNORRABBATT;
        }
            
            super.setPremie(premie);
            
            
    }

    @Override
    public String toString() {
        String utskrift = "REISE FORSIKRING\n" + super.toString() + "\nOmråde: " + omraade + "\n\n";
        return utskrift;
    }
    
}//slutt på klasse