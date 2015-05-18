/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.util.Map;
import java.util.TreeMap;

/**
 * Register som inneholder alle Kundene som har blitt opprettet
 * @author Sander
 */

public class Kunderegister extends TreeMap<String, Forsikringskunde> implements Register{

    /*Metode for å legge en kunde i registeret.*/
    @Override
    public boolean leggTil(Object objekt) 
    {
        if(objekt instanceof Forsikringskunde)
        {
            if(!finnes(((Forsikringskunde)objekt).getPersonNr()))
            {
                put(((Forsikringskunde) objekt).getPersonNr(),(Forsikringskunde)objekt);
                return true;
                
            }
        }
        
        return false;
    }

    /*Metode som sjekker om objektet eksisterer i registeret med Key-verdien pnr*/
    @Override
    public boolean finnes(String pnr) 
    {
       return containsKey(pnr);
    }

     //Metode som sletter en kunde fra kunderegisteret. Tar personnummer(Key-verdien) som parameter
    @Override
    public boolean fjern(String pnr) 
    {
       if(finnes(pnr))
       {
           remove(pnr);
           return true;
       }
       return false;
    }
    

    //metode som returnerer en kunde med  parameter pnr   
    @Override
    public Object getObject(String pnr) 
    {
        if(finnes(pnr))
        {
            return get(pnr);
        }
        return null;
    }

    
    public Forsikringskunde getKunde(String pnr)
    {
       if(finnes(pnr))
       {
          return get(pnr);
       }
        return null;
    }
    
    //Genererer et unikt kundenummer hos kunden.
    @Override
    public String genererNummer()
{
    int max = 0;

    for(Map.Entry<String, Forsikringskunde> entry : this.entrySet())
    {
        int test = Integer.parseInt(entry.getValue().getKundenummer());
        if(test > max)
        {
            max = test;
        } 
    }
    return String.valueOf(max + 1);
}
    
    //metode som returnerer kunderegisteret
    @Override
    public Map getMap() 
    {
       return this;
    }
    
}
