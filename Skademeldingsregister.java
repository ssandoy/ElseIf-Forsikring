/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.util.*;


/**
 * Register som inneholder alle skademeldingene som har blitt skrevet
 * @author Amir
 */

public class Skademeldingsregister extends TreeMap<String, Skademelding> implements Register{
    
    /*Metode for å legge en skademelding i registeret.*/
     @Override
    public boolean leggTil(Object objekt) 
    {
        if(objekt instanceof Skademelding)
        {
        if(!finnes(((Skademelding)objekt).getNr()))
            {
                put(((Skademelding) objekt).getNr(),(Skademelding)objekt);
                return true;
            }
        }
        return false;
    }
    
    /*Metode som sjekker om objektet eksisterer i registeret med Key-verdien skademeldingsnr*/
    @Override
    public boolean finnes(String nr) 
    {
       return containsKey(nr);
    }
    
    //Metode som sletter en skademelding fra registeret. Tar Key-verdien som parameter
    @Override
    public boolean fjern(String nr) 
    {
       if(finnes(nr))
       {
           remove(nr);
           return true;
       }
       return false;
    }
    
    //metode som returnerer en skademelding med  parameter nr 
     @Override
    public Object getObject(String nr) 
    {
        if(finnes(nr))
        {
            return get(nr);
        }
        return null;
    }

    
    public Skademelding getSkademelding(String nr)
    {
       if(finnes(nr))
       {
          return get(nr);
       }
        return null;
    }

    @Override
    public Map getMap() {
        return this;
    }
   
    //Genererer et unikt skademeldingsnummer.
      @Override
    public String genererNummer()
{
    int max = 0;

    for(Map.Entry<String, Skademelding> entry : this.entrySet())
    {
        int test = Integer.parseInt(entry.getValue().getNr());
        if(test > max)
        {
            max = test;
        } 
    }
    return String.valueOf(max + 1);
}
    
    
   
}