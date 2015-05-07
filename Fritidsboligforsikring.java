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
    
    public Fritidsboligforsikring(Forsikringskunde k ,String adresse, int byggeaar, String boligtype, int boareal, String byggematerial, int antallManeder) {
    super(k, adresse, byggeaar, boareal);
        this.antallManeder = antallManeder;   
    }
    
    public void beregnPremie()
    {
        double premie = super.getPremie();
        if( antallManeder <= 2 )
         {
             premie += 100.00;
         }
         else if(antallManeder > 2 && antallManeder <= 4)
         {
             premie += 200.00;
         }
         else
         {
             premie += 300.00;
         }
        super.setPremie(premie);

    }
    
    
}
