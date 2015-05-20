/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel for å skrive en skademelding ved å fylle ut alle feltene
 * @author Amir
 */

public class RegistrerSkademeldingPanel extends JPanel implements ActionListener
{ 
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
   

    private JButton registrer;
    private JButton avbryt;
    
    private JLabel personnummer, beskrivelse, kInfo, tbelop, fnummer, overskrift, stype;
    
    private JComboBox<String> skadeType;
    private JTextField skadebeskrivelse, kontaktInfo;
    private JTextField personnummerfelt, forsikringsnummerfelt ,takseringsbelop;
    
    private String[] st = {"BOLIG-FORSIKRING", "HYTTE-FORSIKRING", "BIL-FORSIKRING", "REISE-FORSIKRING", "BÅT-FORSIKRING"};
    
    private HovedVindu forelder;
    private Kunderegister kregister;
    private Forsikringsregister fregister;
    private Skademeldingsregister sregister;

    
    public RegistrerSkademeldingPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister, Skademeldingsregister sregister) 
    {
        super(new BorderLayout());
        this.forelder = forelder;
        this.kregister = kregister;
        this.fregister = fregister;
        this.sregister = sregister;

        setGrensesnitt();
        
    }

    
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
        skadebeskrivelse = new JTextField(10);
        kontaktInfo = new JTextField(10);
        
      
        
        skadeType = new JComboBox<String>(st);
        
        personnummerfelt = new JTextField(10);
        forsikringsnummerfelt = new JTextField(10);
        takseringsbelop = new JTextField(10);
        
        personnummer = new JLabel("      Personnummer: ");
        fnummer = new JLabel("      Forsikringsnummer: ");
        kInfo = new JLabel("      Kontaktinfo: ");
        beskrivelse = new JLabel("      Skadebeskrivelse: ");
        tbelop = new JLabel("      Takseringsbeløp: ");
        stype = new JLabel("      Skadetype: ");
        overskrift = new JLabel("REGISTRER SKADEMELDING");
        Font font = new Font("Verdana", Font.BOLD, 16);
        overskrift.setFont(font);
        
        personnummer.setForeground(Color.WHITE);
        fnummer.setForeground(Color.WHITE);
        kInfo.setForeground(Color.WHITE);
        beskrivelse.setForeground(Color.WHITE);
        tbelop.setForeground(Color.WHITE);
        stype.setForeground(Color.WHITE);
        overskrift.setForeground(Color.WHITE);
        
        registrer = new JButton("Registrer skademelding");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(7, 2, 0, 0));
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskriftpanel.add(overskrift);
        
        tekstpanel.add(personnummer);
        tekstpanel.add(personnummerfelt);
        tekstpanel.add(fnummer);
        tekstpanel.add(forsikringsnummerfelt);
        tekstpanel.add(stype);
        tekstpanel.add(skadeType);
        tekstpanel.add(beskrivelse);
        tekstpanel.add(skadebeskrivelse);
        tekstpanel.add(kInfo);
        tekstpanel.add(kontaktInfo);
        tekstpanel.add(tbelop);
        tekstpanel.add(takseringsbelop);
        
        knappepanel.add(avbryt);
        knappepanel.add(registrer);


        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);

        add(toppanel, BorderLayout.PAGE_START);
        add(tekstpanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);

        tekstpanel.setBackground(Color.decode("#669CFF"));
        toppanel.setBackground(Color.decode("#669CFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        overskriftpanel.setBackground(Color.decode("#669CFF"));

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int hoyde = skjerm.height;

        forelder.setSize(bredde / 2, hoyde - 500);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
   
    }
    
public void registrerSkademelding() //metode som registrerer ny skademelding på en person og sjekker om det er noen feil 
    {
        try{
            String nr = personnummerfelt.getText();
            Forsikringskunde kunden = kregister.getKunde(nr);
            String fnr = forsikringsnummerfelt.getText();
            Insurance f = fregister.getForsikring(fnr);
           
            
            if(kunden != null && f != null && takseringsbelop.getText() != null)
            {
                if(f.getKunde().getPersonNr().equals(kunden.getPersonNr()))
                {
                    String type = String.valueOf(skadeType.getSelectedItem());
                    String beskrivelse = skadebeskrivelse.getText();
                    double takst = Double.parseDouble(takseringsbelop.getText());
                    Skademelding ny = new Skademelding(kunden, f, type , beskrivelse, takst);
                    
                    String snr = sregister.genererNummer();
                    ny.setSkadenummer(snr);
                    ny.setTakseringsBelop(takst);
                    ny.beregnErstatning();
                    if(ny.sjekkDekning())
                        {
                     int result = JOptionPane.showConfirmDialog(null, 
                             "Du må betale : " + ny.getEgenandel() + ",- i egenandel for å få dekket skadene." + 
                                     "\nVil du tegne denne skademeldingen?", null , JOptionPane.YES_NO_OPTION);
                      if(result == JOptionPane.YES_OPTION)
                      {
                        
                          if(sregister.leggTil(ny) && kunden.getSkademeldinger().add(ny))
                          {
                              double erstatning = ny.getErstatningsBelop() + kunden.getErstatning();
                              kunden.setErstatninger(erstatning);
                              f.setErstatning(ny.getErstatningsBelop()); //Oppdaterer erstatningsbeløpet for forsikringen
                              visMelding("Skademelding lagt til");
                              forelder.addLogo();
                              forelder.visPanel(HovedVindu.HovedVindu);
                              forelder.Size();
                          }
                            else
                            {
                                visFeilMelding("Pass på å fylle ut takseringsbeløp for skaden!");
                            }
                      } //slutt på YES_NO_OPTION
                            else
                            {
                             visFeilMelding("Tegning av skademelding avbrutt");
                            }
                      } //slutt på sjekkdekning
                    else
                 {
                  visFeilMelding("Du har ikke dekning for denne forsikringen");
                 }
                } //Slutt på eierForsikring
                 else
                 {
                  visFeilMelding("Forsikringsnummeret ligger ikke blant kundens forsikringer");
                 }
            }//Slutt på if( kunden != null )
            else
            {
                visFeilMelding("Skriv inn gyldig kundenummer og forsikringsnummer");
            }
        } //slutt på try
        catch(NumberFormatException nfe)
        {
            visFeilMelding("Pass på å skrive tall i taksering!");
        }
    }

    
     public void visMelding(String melding)
     {
        JOptionPane.showMessageDialog(null,melding);
    }
     
      public void visFeilMelding(String melding)
     {
       JOptionPane.showMessageDialog(this, melding, "Problem", 
               JOptionPane.ERROR_MESSAGE);
     }
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if (e.getSource() == registrer) {
            registrerSkademelding();
        } else if (e.getSource() == avbryt) {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();

        }  
    }
 
}//slutt på klasse