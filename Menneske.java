/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;



/**
 * Interface Menneske for Person-superklassen
 * @author Sander, Amir 
 */

public interface Menneske extends Serializable
{
    /*get-metoder for navn*/
    
    public String getNavn();
    public String getFornavn();
    public String getEtternavn();
    public String getAdresse();
    public String getTlf();
    public String getPersonNr();
    
    
    /*set-metoder for navn*/
    public void setFornavn(String fornavn);
    public void setEtternavn(String etternavn); 
    public void setAdresse(String adresse);
   
    
    
}
