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
 *
 * @author ssandoy
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

   //genererer et unikt nummer, henter ut maks-verdi og legger til 1.
    @Override
    public String genererNummer()
    { //funker fordi iterer gjennom key-verdiene
        int maks=1;
        for(Map.Entry<String, Insurance> entry : this.entrySet())
        {
         if(entry.getValue().getFNummer().equals(String.valueOf(maks)))
            {
                maks++;
            }   
        }
        return String.valueOf(maks);
    }
    
}
