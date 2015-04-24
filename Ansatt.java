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
public class Ansatt extends Person implements Serializable
{
    private int ansattnummer;
    
    
    
    public Ansatt(int personnummer, String fornavn, String etternavn, String adresse) 
    {
        super(personnummer, fornavn, etternavn, adresse);
        
        
    }
    
}
