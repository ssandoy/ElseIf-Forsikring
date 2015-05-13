/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;

/**
 * Klasse som er for å opprette et Båtforsikrings-objekt og har en metode som beregner premien for den
 * @author Sander
 */
public class Baatforsikring extends Kjoretoyforsikring implements Serializable
{
    private int hestekrefter;
    private int fot;
    
    
    public Baatforsikring(Forsikringskunde k, String eier,  String registreringsnummer, 
            String type, int modell, int hestekrefter, int fot) 
    {
        super(k, eier, registreringsnummer, type, modell);
        this.hestekrefter = hestekrefter;
        this.fot = fot;
        
    }
 
    //Beregner premie ut i fra fotlengde og hestekrefter. Båter større enn 50 fot får samme pris
    public void beregnPremie()
    {
         if(fot <= 20)
        {
           super.setPremie(1000.00);
        } 
        else if(fot <= 30 && fot >= 21)
        {
            super.setPremie(2000.00);
        } 
        else if(fot <= 45 && fot >= 31)
        {
            super.setPremie(3000.00);
        }
        else if(fot <= 46)
        {
            super.setPremie(5000.00);
        }
        double premie = super.getPremie();
        double motorpremie = 0.00;
        
        if(hestekrefter <= 15)
        {
            premie += 500;
        }
        else if(hestekrefter >= 16 && hestekrefter <= 30)
        {
            premie += 1000;
        }
        else if(hestekrefter >= 31 && hestekrefter <= 50)
        {
            premie += 1500;
        }
        else if(hestekrefter >= 51)
        {
            premie += 2000;
        }
        
        super.setPremie(premie);  
    }
    
    @Override
    public String toString() 
    {
        String utskrift = "BÅT FORSIKRING\n" + super.toString() + "\nAntall hestekrefter: " + hestekrefter + "\nStørrelse: " + fot + " fot \n\n";
        return utskrift;
    }
    
    
}

