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
    private String kundenummer;
    
    private double aarligpremie;
    private double erstatninger;
    private boolean totalkunde;
    private LinkedList<Insurance> forsikringer;
    private LinkedList<Skademelding> skademeldinger;

    
    public Forsikringskunde(String personnummer, String fornavn, String etternavn,  
           String adresse, String telefonnummer) 
    {
        super(personnummer, fornavn, etternavn, adresse, telefonnummer);
        
        
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
            Insurance f = (Insurance) it.next();
            teller++;
        }
        
        if(teller >= 3)
        {
            totalkunde = true;
        }  
        return totalkunde;
    } 
    
   /*Metode som legger en forsikring til hos kunden*/
    public void addForsikring(Insurance f)
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
    
    public LinkedList getSkademeldinger()
    {
        return skademeldinger;
    }
    
    
    //Metode som sletter en forsikrikng hos kunden, om den eksisterer
    public boolean fjernForsikring(Insurance f)
    {
        if(forsikringer.contains(f))
        {
            forsikringer.remove(f);
            return true;
        }
        else 
            return false;
    }
    
    
    //Metode som beregner hvor mye kunden må betale i premie
    public void beregnPremie()
    {
        aarligpremie = 0.00;
        ListIterator<Insurance> it = forsikringer.listIterator();
        while(it.hasNext())
        {
            Insurance f = it.next();
            aarligpremie += f.getPremie();
        }
        
        if(totalkunde)
        {
            aarligpremie *= 0.10;
        }
    }
    
    public String getKundenummer()
    {
        return kundenummer;
    }

    public void setKundenummer(String kundenummer) 
    {
        this.kundenummer = kundenummer;
    }
    

    public double getPremie()
    {
        return aarligpremie;
    }
    
    
    @Override
    public String toString()
    {
        return super.toString() + "\nKundenummer:  " + kundenummer
                + "\n Forsikringer:" + getForsikringer();
    }
    
}
