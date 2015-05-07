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
public class Reiseforsikring extends Insurance implements Serializable
{
    private String omraade;
    private String status;
    
    private Forsikringsregister fregister;
    
    
    public Reiseforsikring(Forsikringskunde kunde, Forsikringsregister fregister, String omraade, String status) 
    {
        super(kunde, fregister);
        this.omraade = omraade;
        this.status = status;
        
    }
    
    //Beregner premien ut i fra hvor forsikringen skal gjelde, og om du har krav på rabatt eller ikke
    public void beregnPremie()
    {
        double premie = 0.00;
        if(omraade.equals("INNLANDS"))
        {
            premie += 300.00;
        }else if(omraade.equals("SKANDINAVIA"))
        {
            premie += 500.00;
        }else if(omraade.equals("EUROPA"))
        {
            premie += 1000.00;
        }else if(omraade.equals("VERDEN"))
        {
            premie += 1500.00;
        }
        
        if(status.equals("Voksen"))
        {
            premie *= 1;
        }else if(status.equals("Student"))
        {
             premie *= 0.80;
        }else if(status.equals("Honnør"))
        {
            premie *= 0.75;
        }
            
            super.setPremie(premie);
            
            
    }
    
}
