/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

/**
 * Klasse som er for å opprette et Innboforsikrings-objekt og 
 * inneholder en metode som beregner premien for den
 * @author Sander
 */

public class Innboforsikring extends Boligforsikring 
{
    private String boligtype;
    private String byggematerial;
    
    private Forsikringsregister fregister;
    
    //konstanter for beregning av premie
    private static int MURKONSTANT = 200;
    private static int TREKONSTANT = 300;
    private static int LEILIGHETKONSTANT = 100;
    private static int ENEBOLIGKONSTANT = 200;
    private static int MINAREALKONSTANT = 500;
    private static int MID1AREALKONSTANT = 1000;
    private static int MID2AREALKONSTANT = 1300;
    private static int MAKSAREALKONSTANT = 1600;
    private static int BYGGEAAR40KONSTANT = 100;
    private static int BYGGEAAR60KONSTANT = 200;
    private static int BYGGEAAR80KONSTANT = 300;
    private static int BYGGEAAR00KONSTANT = 400;
    private static int MAKSBYGGEAARKONSTANT = 500;
    
    
    
    
    public Innboforsikring(Forsikringskunde k,String adresse, int byggeaar, int boareal, String boligtype, String byggematerial) 
    {
        super(k, adresse, byggeaar, boareal);
        
        this.boligtype = boligtype;
        this.byggematerial = byggematerial;
        
    }
    
     //Beregner hvor mye den årlige forsikringspremien blir på
    public void beregnPremie()
    {
        //beregner premien ut fra arealet
         if(super.getAreal() >= 30 && super.getAreal() <= 50)
         {
             super.setPremie(MINAREALKONSTANT);
         }
         else if(super.getAreal() >= 51 && super.getAreal() <= 80)
         {
             super.setPremie(MID1AREALKONSTANT);
         }
         else if(super.getAreal() >= 81 && super.getAreal() <= 110)
         {
             super.setPremie(MID2AREALKONSTANT);
         }
         else if(super.getAreal() >= 111 && super.getAreal() <= 150)
         {
             super.setPremie(MAKSAREALKONSTANT);
         }
        
         //beregner premien ut i fra husmaterial
           double premie = super.getPremie();
           if(byggematerial == "MUR")
           {
               premie += MURKONSTANT;
           }
           else if(byggematerial == "TRE")
           {
               premie += TREKONSTANT;
           }
          
           //Beregner premien ut i fra hvilken hustype
            if(boligtype == "LEILIGHET")
            {
            premie += LEILIGHETKONSTANT;
            }
            else if(boligtype ==  "ENEBOLIG")
            {
            premie += ENEBOLIGKONSTANT;
            }  
            
           //beregner boligpremien ut i fra hvor gammelt huset er
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
        String utskrift = "INNBOFORSIKRING\n" + super.toString() + "\nBoligtype: " + boligtype + "\nByggematerial: " + byggematerial + "\n\n";
        return utskrift;
    }
    
}
