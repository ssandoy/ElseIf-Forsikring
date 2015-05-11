package prosjektoppgave;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 
 * &author YAAKOUBD
 */

public class Historikk 
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
    
    public Historikk(Forsikringsregister fregister, Kunderegister kregister, Skademeldingsregister sregister)
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
        
        Collection c = kregister.values();

    
        Iterator itr = c.iterator();

        //iterate through TreeMap values iterator
        while(itr.hasNext())
        {
            Forsikringskunde kunde = (Forsikringskunde)itr.next();
            if(!kunde.getForsikringer().isEmpty())
                antKunderForsikring++;
            if(!kunde.getSkademeldinger().isEmpty())
                antKunderForsikring++;
            if(kunde.TotalKunde())
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
    
    /*public String skrivKunder()
    {
        String utskrift = "";
        Collection c = kregister.values();
        Iterator itr = c.iterator();
        
        while(itr.hasNext())
        {
            utskrift += itr.next().toString() + "\n";
        }
        return utskrift;
    }*/
    
    public String skrivHistorikk()
    {
        String stjerne = "*";
        String utskrift = "HISTORIKK: \n\nAntall kunder: " + antallKunder
                          + "\nAntall forsikringer: " + antallForsikringer
                          + "\nAntall skademeldinger: " + antallSkademeldinger
                          + "\nAntall kunder som eier forsikring(er): " + antKunderForsikring
                          + "\nAntall kunder som har skrevet skademelding(er): " + antKunderSkademelding
                          + "\nAntall totalkunder: " + antTotalKunder;
        
        return utskrift;
    }
    
    
    
}