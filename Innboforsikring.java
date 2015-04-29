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
public class Innboforsikring extends Boligforsikring 
{
    private String boligtype;
    private String byggematerial;
    

    public Innboforsikring(Forsikringskunde k, String adresse, int byggeaar, int boareal, String boligtype, String byggematerial) 
    {
        super(k, adresse, byggeaar, boareal);
        
        this.boligtype = boligtype;
        this.byggematerial = byggematerial;
        
    }
    
     //Beregner premien på arealet av boligen
    public void beregnArealPremie()
    {
      
         if(boareal >= 30 && boareal <= 50)
         {
             super.setPremie(1000.00);
         }
         else if(boareal >= 51 && boareal <= 80)
         {
             super.setPremie(1500.00);
         }
         else if(boareal >= 81 && boareal <= 110)
         {
             super.setPremie(2000.00);
         }
         else if(boareal >= 111 && boareal <= 140)
         {
             super.setPremie(2200.00);
         }
        }


    public void beregnMaterialPremie()
       {
           double premie = super.getPremie();
           if(byggematerial == "MUR")
           {
               premie += 200.00;
           }
           else if(byggematerial == "TRE")
           {
               premie += 300.00;
           }
           super.setPremie(premie);
       }
    
    //metode som beregner premien ut i fra hvilket type hus det er
        public void beregnTypePremie()
        {
            double premie = 0.0;
            if(boligtype == "LEILIGHET")
            {
            premie += 100.00;
            }
            else if(boligtype ==  "ENEBOLIG")
            {
            premie += 200.00;
            }
            super.setPremie(premie);
        }
    
    //Metode som sluttberegner boligpremien ut i fra hvor gammelt huset er
    
    public void beregnBoligPremie()
     {
         double premie = super.getPremie();
         if(byggeaar <= 1940)
         {
             premie += 100.00;
         }
         else if(byggeaar > 1940 && byggeaar <= 1960)
         {
             premie += 200.00;
         }
         else if(byggeaar > 1960 && byggeaar <= 1980)
         {
             premie += 300.00;
         }
         else if(byggeaar > 1980 && byggeaar <= 2000)
         {
             premie += 400.00;
         }
         else
         {
             premie += 500.00;
         }
         super.setPremie(premie);
     }

}
