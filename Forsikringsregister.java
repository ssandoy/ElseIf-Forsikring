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
public class Forsikringsregister extends TreeMap<Integer,Forsikring> implements Serializable, Register 
{

    @Override
    public boolean leggTil(Object objekt) 
    {
     if(objekt instanceof Forsikring)
        {
        if(!finnes(((Forsikring)objekt).getFNummer()))
            {
                put(((Forsikringskunde) objekt).getPersonNr(),(Forsikringskunde)objekt);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean finnes(String pnr) 
    {
        
    }

    @Override
    public boolean fjern(String pnr) 
    {
        
    }

    @Override
    public Object getObject(String nr) 
    {
       
    }

    @Override
    public Map getMap() 
    {
        
    }
    
}
