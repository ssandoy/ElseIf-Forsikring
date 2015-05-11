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
public class Fritidsboligforsikring extends Boligforsikring implements Serializable 
{   
    private int antallManeder; //Antall måneder man bor i hytta

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
             premie += 200.00;
         }
         else if(super.getAreal() >= 51 && super.getAreal() <= 80)
         {
             premie += 300.00;
         }
         else if(super.getAreal() >= 81 && super.getAreal() <= 110)
         {
             premie += 400.00;
         }
         else if(super.getAreal() >= 111 && super.getAreal() <= 150)
         {
             premie += 500.00;
         }
        
        
        
        if( antallManeder <= 2 )
         {
             premie += 100.00;
         }
         else if(antallManeder > 2 && antallManeder <= 4)
         {
             premie += 200.00;
         }
         else if(antallManeder == 5)
         {
             premie += 300.00;
         }
        
        
          //beregner fritidsboligpremien ut i fra hvor gammel boligen er
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
    
    
}
