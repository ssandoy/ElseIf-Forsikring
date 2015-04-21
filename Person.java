/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

/**
 *
 * @author ssandoy
 */
public abstract class Person implements Menneske
{
    private final int personnummer;
    private String fornavn;
    private String etternavn;
    private String adresse;
    
   
    protected Person(int personnummer, String fornavn, String etternavn, String adresse)
    {
        this.personnummer = personnummer;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
    }
    
    
    
    @Override
    public int getPersonNr() {
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
      return "PERSONNUMMER: " + personnummer + "Fornavn: " + fornavn + "\nEtternavn: " + etternavn + "\nAdresse: " + adresse;
    }
    
    
}

