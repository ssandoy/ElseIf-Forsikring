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
    
    private Forsikringsregister fregister;
    
    
    protected Boligforsikring(Forsikringskunde k,String adresse, int byggeaar, 
                            int boareal)
    {
        super(k);
        this.adresse = adresse;
        this.byggeaar = byggeaar;
       
        this.boareal = boareal;
    }
    
    //get-metoder
    public int getAreal()
    {
        return boareal;
    }
    
    public int getByggeaar()
    {
        return byggeaar;
    }
    
    @Override
    public String toString() {
        
        String utskrift = super.toString() + "\nBolig adresse: " + adresse + "\nByggeår: " + byggeaar 
                          + "\nBoareal: " + boareal + " m²";
        return utskrift;
    }

   
}
