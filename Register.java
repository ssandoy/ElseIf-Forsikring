/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;


import java.io.Serializable;
import java.util.Map;

/**
 * Interface for alle register klassene
 * @author Sander
 */

public interface Register extends Serializable 
{
    /*Metoder for å legge til objekt, og fjerne/sjekke om finnes etter nøkkelverdi*/
    
    public boolean leggTil(Object objekt);
    public boolean finnes(String nr); 
    public boolean fjern(String nr);

    public String genererNummer(); //Genererer et unikt nummer for hvert register-objekt
    
    //henter et objekt fra lista
    public Object getObject(String nr);
    
    //returnerer lista
    public Map getMap();
    
    
    
}
