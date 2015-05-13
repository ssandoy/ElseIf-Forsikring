/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface klasse for forsikringsklassene
 * @author Sander, Amir
 */

public interface Forsikring extends Serializable
{
    
    /*get-metoder*/
    public String getFNummer();
    public Forsikringskunde getKunde();
    public double getPremie();
    public Date getStartDato();
    public double getErstatning();
    public double getEgenandel();
    public String getType();
    
    /*set-metoder*/
    public void setPremie(double premie);
    public void setErstatning(double erstatning);
    public void setType(String type);
    
    
    
}
