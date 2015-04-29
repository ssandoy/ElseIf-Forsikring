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
public class Bilforsikring extends Kjoretoyforsikring implements Serializable
{

    private int aarligkjorelengde;
    private int skadefri;
    private static final double BEREGNINGSKONSTANT = 0.1;
    private static final double PRIS_PR_KM = 1.50;

    
    public Bilforsikring(Forsikringskunde k, String eier, String registreringsnummer, 
                         String type, int modell, int aarligkjorelengde
                        , int skadefri)
    {
        super(k, eier, registreringsnummer, type, modell);
        this.aarligkjorelengde = aarligkjorelengde;
        this.skadefri = skadefri;
       
    }

    @Override
    public void setErstatning(double erstatning) 
    {
            
        
    }
    
///Metode som beregner premien ut i fra hvor gammel bilen er
    public void beregnPremie()
    {
         int modell = super.getModell();
        
         if(modell >= 1950 && modell <= 1970)
         {
             super.setPremie(5000.00);
         }
         else if(modell >= 1971 && modell <= 1990)
         {
             super.setPremie(7000.00); 
         }
         else if(modell >= 1991 && modell <= 2005)
         {
             super.setPremie(9000.00);
         }
         else if(modell >= 2006 && modell <= 2015)
         {
             super.setPremie(12000.00);
         }

    }
    
    
    //metode som beregner bil-premien ut i fra skadefrihet, kjørelengde og egenandel
    public void beregnBiPremie()
    {
        double premie = super.getPremie();
        premie += BEREGNINGSKONSTANT*aarligkjorelengde;
        double bonusprosent = 0.00;
        
       switch (skadefri) 
       {
            case 0:  bonusprosent = 0.00;
                     break;
            case 1:  bonusprosent = 0.15;
                     break;
            case 2:  bonusprosent = 0.30;
                     break;
            case 3:  bonusprosent = 0.45;
                     break;
            case 4:  bonusprosent = 0.60;
                     break;
            case 5:  bonusprosent = 0.75;
                     break;
       }
        
           premie = premie*(1 - bonusprosent);
           super.setPremie(premie);
    }
    
}
