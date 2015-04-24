/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

/**
 *
 * @author ssandoy
 */
public class Fritidsboligforsikring extends Boligforsikring 
{   
    private int antallManeder; //Antall måneder man bor i hytta

    public Fritidsboligforsikring(String adresse, int byggeaar, String boligtype, int boareal, String byggematerial, int antallManeder) {
        super(adresse, byggeaar, boligtype, boareal, byggematerial);
        this.antallManeder = antallManeder;   
    }
    
    public void beregnFritidsPremie()
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
