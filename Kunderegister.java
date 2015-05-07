/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ssandoy
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

     //Metode som sletter en søker fra kunderegisteret. Tar personnummer(Key-verdien) som parameter
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
   
    
    //metode som returnerer kunderegisteret
    @Override
    public Map getMap() 
    {
       return this;
    }
    
}
