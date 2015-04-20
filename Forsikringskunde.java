/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author ssandoy
 */
public class Forsikringskunde extends Person implements Serializable
{
    
    private Date startDato;
    private String fakturaadresse;
    private int kundenummer;
    private static int nestenummer = 1;
    private int aarligpremie;
    private int erstatninger;
    private boolean totalkunde;
    private LinkedList<Forsikring> forsikringer;
    // LinkedList<Skademelding> skademeldinger;

    
    public Forsikringskunde(String personnummer, String fornavn, String etternavn,  
           String adresse, String fakturaadresse) 
    {
        super(personnummer, fornavn, etternavn, adresse);
        this.fakturaadresse = fakturaadresse;

        kundenummer = nestenummer++;
        //nestenummer++;
        
        forsikringer = new LinkedList();
       // skademeldinger = new LinkedList();
        totalkunde = false; //ikke nødvendig
        aarligpremie = 0;
        erstatninger = 0;
    }
    
    
   /*Metode som sjekker om kunden er totalkunde*/
   /* public boolean TotalKunde()
    {
        < sjekker om kunden er totalkunde >
    }*/
    
   
    public void addForsikring(Forsikring f)
    {
        forsikringer.add(f);
    }
    
    public void addSkademelding(Skademelding m)
    {
        skademeldinger.add(m);
    }
    
    public String toString()
    {
        return super.toString() + "\nFakturaadresse: " + fakturaadresse + "\nKundenummer" + kundenummer;
    }
    
}
