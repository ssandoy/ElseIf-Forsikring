/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ssandoy
 */
public abstract class Boligforsikring extends Insurance implements Serializable
{

    
    private String adresse;
    private String byggeaar;
    private String boligtype;
    private int boareal;
    
    
    
    
    protected Boligforsikring( double forsikringspremie, String adresse, 
                        String byggeaar, String boligtype, int boareal)
    {
        super(forsikringspremie);
        this.adresse = adresse;
        this.byggeaar = byggeaar;
        this.boligtype = boligtype;
        this.boareal = boareal;
    }
    
    
    @Override
    public double getPremie() 
    {
       return forsikringspremie;
    }

    @Override
    public Date getStartDato() 
    {
      return startDato;
    }

    @Override
    public double getBeloep()
    {
        return forsikringsbelop;
    }
    
}
