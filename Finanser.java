package prosjektoppgave;


import java.util.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

/**
 *  Klasse som er for å oprette et Finanser-objekt, 
 *  denne klassen beregner inntektene utgiftene og differensen 
 *  mellom begge for forsikringsselskapet
 * @author Amir
 */
public class Finanser {
    
    private double innbetalt;
    private double utbetalt;
    private double differanse;
    private Skademeldingsregister skademeldinger;
    private Forsikringsregister forsikringer;
    
    public Finanser(Skademeldingsregister skademeldinger, Forsikringsregister forsikringer)
    {
        this.skademeldinger = skademeldinger;
        this.forsikringer = forsikringer;
        innbetalt = 0.00;
        utbetalt = 0.00;
        differanse = 0.00;
    }
    
    public void beregnUtbetalt() //regner ut hvor mye forsikringsselskapet har utbetalt
    {
        utbetalt = 0.00;
        try
        {
            List<Skademelding> skademeldingliste = new LinkedList<>();


            for(Map.Entry<String, Skademelding> entry : skademeldinger.entrySet())
            {
                skademeldingliste.add((Skademelding) entry.getValue());
            }

            ListIterator<Skademelding> itr = skademeldingliste.listIterator();


            while(itr.hasNext())
            {
                Skademelding temp = (Skademelding) itr.next();
                utbetalt += temp.getErstatningsBelop();
            }
        }
        catch(NullPointerException npe){
            System.out.println( "Ingen skademeldinger er lagret" );
        }
        
        
        
        
    }
    
    public void beregnInnbetalt() //regner ut hvor mye forsikringsselskapet har fått innbetalt 
    {
        innbetalt = 0.00;
        for(Map.Entry<String, Insurance> entry: forsikringer.entrySet())
        {
            
            Insurance f = entry.getValue();
            innbetalt += f.getPremie();
            utbetalt += f.getErstatning();
            }
            for(Map.Entry<String,Skademelding> entry: skademeldinger.entrySet())
            {
            Skademelding s = entry.getValue();
            innbetalt += s.getEgenandel();
            }
        }
    
    //beregner differansen mellom de innbetalte og utbetalte verdiene
    public void beregnDifferanse() 
    {
        differanse = innbetalt - utbetalt;
    }
    
    @Override
    public String toString()
    {
        NumberFormat f = new DecimalFormat("#0.00");
        String tekst = "\nInnbetalt: " + f.format(innbetalt) + "\nUtbetalt: " 
                + f.format(utbetalt) + "\nDifferanse: " + f.format(differanse);
        return tekst;                
    }
    
}   