package prosjektoppgave;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

/**
 * Klasse som oppretter et Statistikk objekt som er for å skrive ut all
 statistikk om selskapets kunder, forsikringer og skademeldinger
 author Amir
 */

public class Statistikk 
{
    private Forsikringsregister fregister;
    private Kunderegister kregister;
    private Skademeldingsregister sregister;
    
    private int antallKunder = 0;
    private int antallForsikringer = 0;
    private int antallSkademeldinger = 0;
    private int antKunderForsikring = 0;
    private int antKunderSkademelding = 0;
    private int antTotalKunder = 0;
    
    public Statistikk(Forsikringsregister fregister, Kunderegister kregister, Skademeldingsregister sregister)
    {
        this.fregister = fregister;
        this.kregister = kregister;
        this.sregister = sregister;
        antallKunder = 0;
        antallForsikringer = 0;
        antallSkademeldinger = 0;
        antKunderForsikring = 0;
        antKunderSkademelding = 0;
        antTotalKunder = 0;
    }
    
    public void tell() //Metode som teller opp alle typer kunder, forsikringer, og skademelding
    {
        antallKunder = kregister.size();
        antallForsikringer = fregister.size();
        antallSkademeldinger = sregister.size();
        
         antKunderForsikring = 0;
         antKunderSkademelding = 0;
         antTotalKunder = 0;
        
        
        for(Map.Entry<String, Forsikringskunde> entry: kregister.entrySet())
        {
            Forsikringskunde kunde = entry.getValue();
            if(!kunde.getForsikringer().isEmpty())
                antKunderForsikring++;
            if(!kunde.getSkademeldinger().isEmpty())
                antKunderSkademelding++;
            if(kunde.getTotalKunde().equals("JA"))
                antTotalKunder++;
            
        }
    }
    
    public int getAntallKunder()
    {
        return antallKunder;
    }
    
    public int getAntallForsikringer()
    {
        return antallForsikringer;
    }
    
    public int getAntallSkademeldinger()
    {
        return antallSkademeldinger;
    }
    
    
    public String skrivStatistikk()
    {
        String stjerne = "*";
        String utskrift = "STATISTIKK: \n\nAntall kunder: " + antallKunder
                          + "\nAntall forsikringer: " + antallForsikringer
                          + "\nAntall skademeldinger: " + antallSkademeldinger
                          + "\nAntall kunder som eier forsikring(er): " + antKunderForsikring
                          + "\nAntall kunder som har skrevet skademelding(er): " + antKunderSkademelding
                          + "\nAntall totalkunder: " + antTotalKunder;
        
        return utskrift;
    }
    
    
    
}