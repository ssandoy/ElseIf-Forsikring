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

    private int registreringsaar;
    private int kjorelengde;
    
    
    public Bilforsikring(double forsikringspremie, String eier, String registreringsnummer, 
                         String type, String modell, int registreringsaar, int kjorelengde)
    {
        super(forsikringspremie,  eier, registreringsnummer, type, modell);
        this.registreringsaar = registreringsaar;
        this.kjorelengde = kjorelengde;
        
    }
    
}
