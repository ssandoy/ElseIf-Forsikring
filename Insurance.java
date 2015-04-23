/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ssandoy
 */
public abstract class Insurance implements Serializable, Forsikring {

    private final int forsikringsnummer;
    private static int nestenummer = 1;
    private double erstatning;
    private double egenandel;
    private Date startDato;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private double forsikringspremie;
    
    
    protected Insurance()
    {
        this.startDato = new Date();
        forsikringsnummer = nestenummer++;
        erstatning = 0.00;
        egenandel = 0.00;
        forsikringspremie = 0.00;
    } 
    
    
    @Override
    public int getFNummer() 
    {
        return forsikringsnummer;
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
    public double getErstatning() 
    {
        return erstatning;
    }
    
    @Override
     public void setPremie(double premie)
     {
         forsikringspremie = premie;
     }
    
     @Override
     public void setErstatning(double erstatning)
     {
         this.erstatning = erstatning;
     }
    
    
}
