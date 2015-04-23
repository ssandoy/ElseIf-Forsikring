/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author ssandoy
 */
public class Forsikringskunde extends Person implements Serializable
{
    
    private Date startDato;
    private String fakturaadresse; //RADIOBUTTON SOM KAN KRYSSES AV slik at den fylles ut autmoatisk
    private int kundenummer;
    private static int nestenummer = 1;
    private double aarligpremie;
    private double erstatninger;
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
        aarligpremie = 0.00;
        erstatninger = 0.00;
    }
    
    
   /*Metode som sjekker om kunden er totalkunde*/
    public boolean TotalKunde()
    {
        int teller = 0;
        ListIterator it = forsikringer.listIterator();
        while(it.hasNext())
        {
            Forsikring f = (Forsikring) it.next();
            teller++;
        }
        
        if(teller >= 3)
        {
            totalkunde = true;
        }  
        return totalkunde;
    } 
    
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
    
    //Metode som beregner hvor mye kunden må betale i premie
    public void beregnPremie()
    {
        aarligpremie = 0.00;
        ListIterator it = forsikringer.listIterator();
        while(it.hasNext())
        {
            Forsikring f = (Forsikring) it.next();
            aarligpremie += f.getPremie();
        }
        
        if(totalkunde)
        {
            aarligpremie *= 0.10;
        }
    }
    
    //Metode som beregner hvor mye kunde får i erstatninger
   /* public void beregnErstatning()
    {
        
    }*/
    
    
    public double getPremie()
    {
        return aarligpremie;
    }
    
    
    @Override
    public String toString()
    {
        return super.toString() + "\nFakturaadresse: " + fakturaadresse + "\nKundenummer:  " + kundenummer;
    }
    
}
