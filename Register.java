/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;


import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author ssandoy
 */
public interface Register extends Serializable 
{
    /*Metoder for å legge til objekt, og fjerne/sjekke om finnes etter nøkkelverdi*/
    
    public boolean leggTil(Object objekt);
    public boolean finnes(int nr); //skriv kommentar i sluttrapport. Dette fordi enklere å bygge videre
    public boolean fjern(int nr);

    
    public Object getObject(int nr);
    
    //returnerer lista
    public Map getMap();
    
    
    
}
