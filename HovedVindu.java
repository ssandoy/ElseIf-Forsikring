/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author ssandoy
 */
public class HovedVindu extends JFrame implements ActionListener  
{
    
    private JTextField personnummer, fornavn, etternavn, adresse, fakturaadresse;
    private JButton regKunde;
    private JTextArea utskrift;
    
    private Kunderegister kunderegister;
    
    public HovedVindu()
    {
        super("Insurance-Browse!");
        
        kunderegister = new Kunderegister();
        personnummer = new JTextField(20);
        fornavn = new JTextField(20);
        etternavn = new JTextField(20);
        adresse = new JTextField(30);
        fakturaadresse = new JTextField(30);
        
        regKunde = new JButton("Registrer kunde!");
        utskrift = new JTextArea( 15, 45 );
        utskrift.setEditable( false );
        
        Container c = getContentPane();
        c.setLayout( new FlowLayout() );
        c.add(new JLabel("PERSONNUMMER"));
        c.add(personnummer);
        c.add( new JLabel( "Fornavn:" ) );
        c.add( fornavn );
        c.add( new JLabel( "Etternavn:" ) );
        c.add( etternavn );
        c.add( new JLabel( "Adresse:" ) );
        c.add( adresse );
        c.add( new JLabel( "Fakturaadresse:" ) );
        c.add( fakturaadresse );
        c.add(regKunde);
        c.add(utskrift);
        
        c.add( new JScrollPane( utskrift ) );
        
        Knappelytter lytter = new Knappelytter();
        regKunde.addActionListener(lytter);
        setSize( 600, 550 );
    setVisible( true );
        
        
    }
    
        public void registrerKunde()
        {
              int personnummer = Integer.parseInt(this.personnummer.getText());
              String fornavn = this.fornavn.getText();
              String etternavn = this.etternavn.getText();
              String adresse = this.adresse.getText();
              String fakturaadresse = this.fakturaadresse.getText();
              
              Forsikringskunde k = new Forsikringskunde(personnummer, fornavn, etternavn, adresse, fakturaadresse);
              
              
              kunderegister.leggTil(k);
              Forsikringskunde kunde = kunderegister.getKunde(personnummer);
              System.out.println(kunde.toString());
           
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
    
    private class Knappelytter implements ActionListener
  {
        @Override
    public void actionPerformed( ActionEvent e )
    {
      if(e.getSource() == regKunde)
          registrerKunde();
      
    }
  }
}