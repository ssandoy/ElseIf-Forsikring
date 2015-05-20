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
    
    //konstanter for beregning av årlig premie
    private static int FOT20KONSTANT = 7500;
    private static int FOT30KONSTANT = 2000;
    private static int FOT45KONSTANT = 3000;
    private static int MAKSFOTKONSTANT = 4000;
    private static int HESTER15KONSTANT = 500;
    private static int HESTER30KONSTANT = 1000;
    private static int HESTER50KONSTANT = 1200;
    private static int MAKSHESTERKONSTANT = 1500;
    
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
           super.setPremie(FOT20KONSTANT);
        } 
        else if(fot <= 30 && fot >= 21)
        {
            super.setPremie(FOT30KONSTANT);
        } 
        else if(fot <= 45 && fot >= 31)
        {
            super.setPremie(FOT45KONSTANT);
        }
        else if(fot <= 46)
        {
            super.setPremie(MAKSFOTKONSTANT);
        }
        double premie = super.getPremie();
        double motorpremie = 0.00;
        
        if(hestekrefter <= 15)
        {
            premie += HESTER15KONSTANT;
        }
        else if(hestekrefter >= 16 && hestekrefter <= 30)
        {
            premie += HESTER30KONSTANT;
        }
        else if(hestekrefter >= 31 && hestekrefter <= 50)
        {
            premie += HESTER50KONSTANT;
        }
        else if(hestekrefter >= 51)
        {
            premie += MAKSHESTERKONSTANT;
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

