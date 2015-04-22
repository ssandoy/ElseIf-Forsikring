/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ssandoy
 */
public abstract class Boligforsikring extends Insurance implements Serializable
{

    
    private String adresse;
    private String byggeaar;
    private String boligtype;
    private int boareal;
    
    
    
    
    protected Boligforsikring( String adresse, String byggeaar, 
                            String boligtype, int boareal)
    {
        this.adresse = adresse;
        this.byggeaar = byggeaar;
        this.boligtype = boligtype;
        this.boareal = boareal;
    }
    
    
    @Override
    public double getPremie() 
    {
       return forsikringspremie;
    }

    @Override
    public Date getStartDato() 
    {
      return startDato;
    }

    public double beregnPremie()
    {
        double premie = 0.00;
         if(boareal >= 30 && boareal <= 50)
         {
             super.setPremie(12000.00);
             return premie;
         }
         else if(boareal >= 51 && boareal <= 80)
         {
             super.setPremie(20000.00);
             return premie;
         }
         else if(boareal >= 81 && boareal <= 110)
         {
             super.setPremie(20000.00);
             return premie;
         }
         else if(boareal >= 11 && boareal <= 140)
         {
             super.setPremie(20000.00);
             return premie;
         }
         
   
}
