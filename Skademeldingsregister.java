/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YAAKOUBD
 */
public class Skademeldingsregister extends TreeMap<Integer, Skademelding> implements Register{
    
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
    
    @Override
    public boolean finnes(int nr) 
    {
       return containsKey(nr);
    }
    
    @Override
    public boolean fjern(int nr) 
    {
       if(finnes(nr))
       {
           remove(nr);
           return true;
       }
       return false;
    }
    
     @Override
    public Object getObject(int nr) 
    {
        if(finnes(nr))
        {
            return get(nr);
        }
        return null;
    }

    
    public Skademelding getSkademelding(int nr)
    {
       if(finnes(nr))
       {
          return get(nr);
       }
        return null;
    }
   
    
   
}