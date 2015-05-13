/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

/**
 * Person superklasse for kunde klassen, inneholder all informasjon om personen
 * @author Sander
 */

public abstract class Person implements Menneske
{
    private String personnummer;
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String telefonnummer;
    
   
    protected Person(String personnummer, String fornavn, String etternavn, String adresse, String telefonnummer)
    {
        this.personnummer = personnummer;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
    }
    
    
    /*get-metoder*/
    @Override
    public String getPersonNr() {
        return personnummer;
    }


    @Override
    public String getNavn()
    {
        return fornavn + " " + etternavn;
    }

    @Override
    public String getFornavn() 
    {
        return fornavn;
    }

    @Override
    public String getEtternavn() 
    {
        return etternavn;
    }
    
    @Override
    public String getAdresse()
    {
        return adresse;
    }
    
    @Override
    public String getTlf()
    {
        return telefonnummer;
    }
   
   
/*set-metoder*/
    @Override
    public void setFornavn(String fornavn) 
    {
        this.fornavn = fornavn;
    }

    @Override
    public void setEtternavn(String etternavn) 
    {
        this.etternavn = etternavn;
    }
    
    @Override 
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }
    
    @Override
    public String toString()
    {
      return "PERSONNUMMER: " + personnummer + "\nNavn: " + fornavn + " " + etternavn + "\nAdresse: " + adresse;
    }
    
    
}

