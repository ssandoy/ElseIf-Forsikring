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
    private int byggeaar;
    private String boligtype;
    private int boareal;
    private String byggematerial;
    
    
    
    protected Boligforsikring( String adresse, int byggeaar, 
                            String boligtype, int boareal, String byggematerial)
    {
        this.adresse = adresse;
        this.byggeaar = byggeaar;
        this.boligtype = boligtype;
        this.boareal = boareal;
        
        this.byggematerial = byggematerial;
        
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

    //Beregner premien på arealet av boligen
    public void beregnArealPremie()
    {
      
         if(boareal >= 30 && boareal <= 50)
         {
             super.setPremie(12000.00);
         }
         else if(boareal >= 51 && boareal <= 80)
         {
             super.setPremie(20000.00);
         }
         else if(boareal >= 81 && boareal <= 110)
         {
             super.setPremie(20000.00);
         }
         else if(boareal >= 111 && boareal <= 140)
         {
             super.setPremie(20000.00);
         }
        }


    public void beregnMaterialPremie()
       {
           double premie = super.getPremie();
           if(byggematerial == "MUR")
           {
               premie += 1000.00;
           }
           else if(byggematerial == "TRE")
           {
               premie += 3000.00;
           }
           super.setPremie(premie);
       }
    
    //Metode som sluttberegner boligpremien ut i fra hvor gammelt huset er
    
    public void beregnBoligPremie()
     {
         double premie = super.getPremie();
         if(byggeaar <= x && byggeaar >= x)
         {
             
         }
         else if
     }

}
