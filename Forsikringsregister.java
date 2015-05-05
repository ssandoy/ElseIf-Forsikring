/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ssandoy
 */
public class Forsikringsregister extends TreeMap<String,Insurance> implements Serializable, Register 
{

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
    
    @Override
    public boolean finnes(String fnr) 
    {
        return containsKey(fnr);
    }
    
    

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

    @Override
    public Map getMap() 
    {
        return this;
    }

    
    
}
