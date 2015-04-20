/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sander, Amir, Jonatan
 */

public interface Forsikring extends Serializable
{
    
    /*get-metoder*/
    public int getFNummer();
    public double getPremie();
    public Date getStartDato();
    public double getErstatning();
    
    
}
