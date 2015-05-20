/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;

/**
 * En abstrakt superklasse for reiseforsikrings klassen
 * @author Amir
 */

public abstract class Personforsikring extends Insurance implements Serializable{
    
    private String status;

    public Personforsikring(Forsikringskunde kunde, String status ) {
        super(kunde);
        this.status = status;
    }

    // set og get-metoder for status
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }


    @Override
    public String toString() {
        String utskrift = super.toString() + "\nStatus: " + status;
        return utskrift;
    }
     
}