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
             super.setPremie(500.00);
         }
         else if(super.getAreal() >= 51 && super.getAreal() <= 80)
         {
             super.setPremie(1000.00);
         }
         else if(super.getAreal() >= 81 && super.getAreal() <= 110)
         {
             super.setPremie(1300.00);
         }
         else if(super.getAreal() >= 111 && super.getAreal() <= 150)
         {
             super.setPremie(1600.00);
         }
        
         //beregner premien ut i fra husmaterial
           double premie = super.getPremie();
           if(byggematerial == "MUR")
           {
               premie += 200.00;
           }
           else if(byggematerial == "TRE")
           {
               premie += 300.00;
           }
          
           //Beregner premien ut i fra hvilken hustype
            if(boligtype == "LEILIGHET")
            {
            premie += 100.00;
            }
            else if(boligtype ==  "ENEBOLIG")
            {
            premie += 200.00;
            }  
            
           //beregner boligpremien ut i fra hvor gammelt huset er
         if(super.getByggeaar() <= 1940)
         {
             premie += 100.00;
         }
         else if(super.getByggeaar() > 1940 && super.getByggeaar() <= 1960)
         {
             premie += 200.00;
         }
         else if(super.getByggeaar() > 1960 && super.getByggeaar() <= 1980)
         {
             premie += 300.00;
         }
         else if(super.getByggeaar() > 1980 && super.getByggeaar() <= 2000)
         {
             premie += 400.00;
         }
         else if(super.getByggeaar() > 2000)
         {
             premie += 500.00;
         }
         super.setPremie(premie);
     }

    @Override
    public String toString() {
        String utskrift = "INNBO FORSIKRING\n" + super.toString() + "\nBoligtype: " + boligtype + "\nByggematerial: " + byggematerial + "\n\n";
        return utskrift;
    }
    
}
