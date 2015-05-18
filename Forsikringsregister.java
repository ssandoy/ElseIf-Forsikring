/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Register som inneholder alle Forsikringene som har blitt opprettet
 * @author Sander
 */
public class Forsikringsregister extends TreeMap<String,Insurance> implements Serializable, Register 
{

    //legger til et forsikrings-objekt i lista
    @Override
    public boolean leggTil(Object objekt) 
    {
     if(objekt instanceof Insurance)
        {
        if(!finnes(((Insurance)objekt).getFNummer()))
            {
                put(((Insurance) objekt).getFNummer(),(Insurance)objekt);
                return true;
            }
        }
        return false;
    }
    
    //sjekker om inkommen parameter allerede eksisterer som key-verdi
    @Override
    public boolean finnes(String fnr) 
    {
        return containsKey(fnr);
    }
    
    
  //fjerner et objekt i fra lista, hvis det finnes
    @Override
    public boolean fjern(String fnr) 
    {
         if(finnes(fnr))
       {
           remove(fnr);
           return true;
       }
       return false;
    }
    
   

    @Override
    public Object getObject(String fnr) 
    {
     if(finnes(fnr))
        {
            return get(fnr);
        }
        return null;  
    }

    public Insurance getForsikring(String fnr)
    {
        if(finnes(fnr))
        {
            return get(fnr);
        }
        return null;  
    }
    
    @Override
    public Map getMap() 
    {
        return this;
    }

  //Genererer et unikt forsikringsnummer hos kunden.
    @Override
    public String genererNummer()
{
    int max = 0;

    for(Map.Entry<String, Insurance> entry : this.entrySet())
    {
        int test = Integer.parseInt(entry.getValue().getFNummer());
        if(test > max)
        {
            max = test;
        } 
    }
    return String.valueOf(max + 1);
    
}

}