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
    private LinkedList<Skademelding> skademeldinger;

    
    public Forsikringskunde(int personnummer, String fornavn, String etternavn,  
           String adresse, String fakturaadresse) 
    {
        super(personnummer, fornavn, etternavn, adresse);
        this.fakturaadresse = fakturaadresse;

        kundenummer = nestenummer++;
        
        
        forsikringer = new LinkedList();
        skademeldinger = new LinkedList();
        totalkunde = false; //ikke nødvendig
        aarligpremie = 0;
        erstatninger = 0;
    }
    
    
   /*Metode som sjekker om kunden er totalkunde*/
   /* public boolean TotalKunde()
    {
        < sjekker om kunden er totalkunde >
    }*/
    
   /*Metode som legger en forsikring til hos kunden*/
    public void addForsikring(Forsikring f)
    {
        forsikringer.add(f);
    }
    
    /*Metode som legger til en skademelding hos kunden*/
    public void addSkademelding(Skademelding m)
    {
        skademeldinger.add(m);
    }
     
    public LinkedList getForsikringer()
    {
        return forsikringer;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nFakturaadresse: " + fakturaadresse + "\nKundenummer:  " + kundenummer;
    }
    
}
