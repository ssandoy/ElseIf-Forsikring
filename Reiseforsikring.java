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
    private String yrke;
    
    public Reiseforsikring(Forsikringskunde kunde, String omraade, String yrke) 
    {
        super(kunde);
        this.omraade = omraade;
        this.yrke = yrke;
        
    }
    
}
