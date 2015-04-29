/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author YAAKOUBD
 */
public class Finanser {
    
    private double innbetalt;
    private double utbetalt;
    private double differanse;
    private Skademeldingsregister skademeldinger;
    private Forsikringsregister forsikringer;
    
    public Finanser(Skademeldingsregister skademeldinger)
    {
        this.skademeldinger = skademeldinger;
        innbetalt = 0.00;
        utbetalt = 0.00;
        differanse = 0.00;
    }
    
    public void beregnUtbetalt() //regner ut hvor mye forsikringsselskapet har betalt
    {
        
        Iterator itr = skademeldinger.entrySet().iterator();
        
        while(itr.hasNext())
        {
            Skademelding temp = (Skademelding) itr.next();
            utbetalt += temp.getErstatningsBelop();
        }
        
        
        
        
    }
    
    public void beregnInnbetalt() //regner ut hvor mye forsikringsselskapet har fått innbetalt 
    {
        Iterator itrf = forsikringer.entrySet().iterator();
        while(itrf.hasNext())
        {
            Forsikring tempo = (Forsikring) itrf.next();
            innbetalt += tempo.getEgenandel();
            innbetalt += tempo.getPremie();
        }
    }
    
    public void beregnDifferanse()
    {
        differanse = innbetalt - utbetalt;
    }
    
    @Override
    public String toString()
    {
        NumberFormat f = new DecimalFormat("#0.00");
        String tekst = "FINANSER:\nInnbetalt: " + f.format(innbetalt) + "\nUtbetalt: " 
                + f.format(utbetalt) + "\nDifferanse: " + f.format(differanse);
        return tekst;                
    }
    
}
