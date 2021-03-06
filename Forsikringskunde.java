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
 *  Klasse for å opprette et Forsikringskunde-objekt, inneholder all info om kunden, 
 * metoder for å endre på dem
 *  og har to LinkedList-er som er for å lagre alle forsikringene og skademeldingene til kunden  
 * @author Sander
 */
public class Forsikringskunde extends Person implements Serializable
{
    
    private Date startDato;
    private String kundenummer;
    
    private double aarligpremie;
    private double erstatninger;
    private boolean totalkunde;
    private  LinkedList<Insurance> forsikringer;
    private  LinkedList<Skademelding> skademeldinger;

    
    public Forsikringskunde(String personnummer, String fornavn, String etternavn,  
           String adresse, String telefonnummer) 
    {
        super(personnummer, fornavn, etternavn, adresse, telefonnummer);
        
        forsikringer = new LinkedList<Insurance>();
        skademeldinger = new LinkedList<Skademelding>();
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
     
    //metode som henter ut forsikringene til kunden
    public LinkedList getForsikringer()
    {
        return forsikringer;
    }
    
        //metode som henter ut skademeldingene til kunden
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
    public double beregnTotalPremie()
    {
        aarligpremie = 0.00;
        ListIterator<Insurance> it = forsikringer.listIterator();
        while(it.hasNext())
        {
            Insurance f = it.next();
            aarligpremie += f.getPremie();
        }
        
        if(TotalKunde())
        {
            aarligpremie *= 0.90;
        }
        return aarligpremie;
    }
    
    //Metode som beregner som beregner hvor mye kunde har fått utbetalt i erstatning
    public double beregnTotalErstatning()
    {
        erstatninger = 0.00;
        ListIterator<Skademelding> it = skademeldinger.listIterator();
        while(it.hasNext())
        {
            Skademelding s = it.next();
            erstatninger += s.getErstatningsBelop();
        }
        return erstatninger;
    }
    
    //get- og set-metoder
    public String getKundenummer()
    {
        return kundenummer;
    }

    public void setKundenummer(String kundenummer) 
    {
        this.kundenummer = kundenummer;
    }
    
    public double getErstatning()
    {
        return erstatninger;
    }
    

    public double getPremie()
    {
        return aarligpremie;
    }
    
    public void setPremie(double aarligpremie)
    {
        this.aarligpremie = aarligpremie;
    }

    public void setErstatninger(double erstatninger) {
        this.erstatninger = erstatninger;
    }
    
    
    public String getTotalKunde()
    {
        if(TotalKunde())
        {
            return "JA";
        }
        else
        {
            return "NEI";
        }
    }
    
    public boolean eierForsikring(Insurance f)
    {
        return forsikringer.contains(f);
    }
    
    
    
    @Override
    public String toString()
    {
        return super.toString() + "\nKundenummer:  " + kundenummer
                + "\n Forsikringer:" + getForsikringer()
                + "\n Totalsum årlige forsikringspremier: " + beregnTotalPremie() + ",-";
    }
    
}
