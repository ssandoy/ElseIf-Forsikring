/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;

/**
 * Klasse for å opprette et fritidsboligforsikringsobjekt og 
 * inneholder en metode for å beregne premien dens
 * @author Sander
 */

public class Fritidsboligforsikring extends Boligforsikring implements Serializable 
{   
    private int antallManeder; //Antall måneder man bor i hytta

    //Konstanter for beregning av årlig premie
    private static int MINAREALKONSTANT = 200;
    private static int MID1AREALKONSTANT = 300;
    private static int MID2AREALKONSTANT = 400;
    private static int MAKSAREALKONSTANT = 500;
    private static int BYGGEAAR40KONSTANT = 100;
    private static int BYGGEAAR60KONSTANT = 200;
    private static int BYGGEAAR80KONSTANT = 300;
    private static int BYGGEAAR00KONSTANT = 400;
    private static int MAKSBYGGEAARKONSTANT = 500;
    private static int TOMNDKONSTANT = 100;
    private static int TREMNDKONSTANT = 200;
    private static int MAKSMNDKONSTANT = 300;
    
    private Forsikringsregister fregister;
    
    public Fritidsboligforsikring(Forsikringskunde k ,String adresse, int byggeaar, int boareal, int antallManeder) {
    super(k, adresse, byggeaar, boareal);
        this.antallManeder = antallManeder;   
    }
    
    
    public void beregnPremie() //metoder som beregner premien ut i fra antall måneder man bor i hytta
    {
        double premie = 0.00;
         //beregner premien ut fra arealet
         if(super.getAreal() >= 30 && super.getAreal() <= 50)
         {
             premie += MINAREALKONSTANT;
         }
         else if(super.getAreal() >= 51 && super.getAreal() <= 80)
         {
             premie += MID1AREALKONSTANT;
         }
         else if(super.getAreal() >= 81 && super.getAreal() <= 110)
         {
             premie += MID2AREALKONSTANT;
         }
         else if(super.getAreal() >= 111 && super.getAreal() <= 150)
         {
             premie += MAKSAREALKONSTANT;
         }
        
        
        //beregner premien ut i fra hvor lenge man bor i boligen
        if( antallManeder <= 2 )
         {
             premie += TOMNDKONSTANT;
         }
         else if(antallManeder > 2 && antallManeder <= 4)
         {
             premie += TREMNDKONSTANT;
         }
         else if(antallManeder == 5)
         {
             premie += MAKSMNDKONSTANT;
         }
        
        
          //beregner fritidsboligpremien ut i fra hvor gammel boligen er
         if(super.getByggeaar() <= 1940)
         {
             premie += BYGGEAAR40KONSTANT;
         }
         else if(super.getByggeaar() > 1940 && super.getByggeaar() <= 1960)
         {
             premie += BYGGEAAR60KONSTANT;
         }
         else if(super.getByggeaar() > 1960 && super.getByggeaar() <= 1980)
         {
             premie += BYGGEAAR80KONSTANT;
         }
         else if(super.getByggeaar() > 1980 && super.getByggeaar() <= 2000)
         {
             premie += BYGGEAAR00KONSTANT;
         }
         else if(super.getByggeaar() > 2000)
         {
             premie += MAKSBYGGEAARKONSTANT;
         }
        super.setPremie(premie);

    }
    
    @Override
    public String toString() {
        String utskrift = "HYTTE FORSIKRING\n" + super.toString() + "\nAntall måneder: " + antallManeder + "\n\n";
        return utskrift;
    }
    
}
