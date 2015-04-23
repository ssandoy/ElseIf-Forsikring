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
public class Baatforsikring extends Kjoretoyforsikring implements Serializable
{
    public String hestekrefter;
    
    public Baatforsikring(String eier,  String registreringsnummer, 
            String type, String modell, String hestekrefter) 
    {
        super(eier, registreringsnummer, type, modell);
        this.hestekrefter = hestekrefter;
        
    }
 
    
}
