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
public abstract class Insurance implements  Forsikring {
    
    private Forsikringskunde kunde;
    private final int fnr;
    private static int nestenummer;
    private String forsikringsnummer;
    private double erstatning;
    private double egenandel;
    private Date startDato;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private double forsikringspremie;
    
    private Forsikringsregister fregister;
    
    
    protected Insurance(Forsikringskunde kunde, Forsikringsregister fregister)
    {
        this.kunde = kunde;
        this.fregister = fregister;
        kunde.addForsikring(this);
        this.startDato = new Date();
        if(fregister.size() == 0)
        {
            nestenummer = 1;
        } else
        {
           nestenummer = fregister.getLastNumber();
        }
        nestenummer++;
        fnr = nestenummer;
        forsikringsnummer = fnr+"";
        System.out.println(forsikringsnummer);
        erstatning = 0.00;
        egenandel = 0.00;
        forsikringspremie = 0.00;
    } 
    
    
    @Override
    public String getFNummer() 
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
     
     @Override
     public double getEgenandel()
     {
         return egenandel;
     }

    
    
    
}
