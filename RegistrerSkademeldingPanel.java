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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ssandoy
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
    
    String[] st = {"BOLIG", "HYTTE", "BIL", "REISE", "BÅT"};
    
    HovedVindu forelder;
    Kunderegister kregister;
    Forsikringsregister fregister;
    Skademeldingsregister sregister;

    
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
        
        personnummer = new JLabel("Personnummer: ");
        fnummer = new JLabel("Forsikringsnummer: ");
        kInfo = new JLabel("Kontaktinfo: ");
        beskrivelse = new JLabel("Skadebeskrivelse: ");
        tbelop = new JLabel("Takseringsbeløp: ");
        stype = new JLabel("Skadetype: ");
        overskrift = new JLabel("Registrer Skademelding");
        
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

        tekstpanel.setBackground(Color.decode("#E57E7E"));
        toppanel.setBackground(Color.decode("#E57E7E"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        overskriftpanel.setBackground(Color.decode("#E57E7E"));

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
            Insurance f = (Insurance)fregister.getForsikring(fnr);
            
            LinkedList<Insurance> fliste = kunden.getForsikringer();
            
            if(kunden != null && f != null && takseringsbelop.getText() != null)
            {
                   if(fliste.contains(f))
                {
                    String type = String.valueOf(skadeType.getSelectedItem());
                    String beskrivelse = skadebeskrivelse.getText();
                    double takst = Double.parseDouble(takseringsbelop.getText());
                    Skademelding ny = new Skademelding(kunden, f, type , beskrivelse, takst);
                    
                    String snr = sregister.genererNummer();
                    ny.setSkadenummer(snr);
                    ny.setTakseringsBelop(takst);
                    ny.beregnErstatning();
                    int result = JOptionPane.showConfirmDialog(null, 
                             "Du må betale : " + ny.getEgenandel() + ",- i egenandel for å få dekket skadene." + 
                                     "\nVil du tegne denne skademeldingen?", null , JOptionPane.YES_NO_OPTION);
                      if(result == JOptionPane.YES_OPTION)
                      {
                        if(ny.sjekkDekning())
                        {
                          if(!sregister.leggTil(ny))
                          {
                              visFeilMelding("Pass på å fylle ut taksering og erstatningsbelop!");
                          }
                            else
                            {
                                kunden.addSkademelding(ny);
                                System.out.println(sregister.toString());
                                visMelding("Skademelding lagt til");
                                forelder.addLogo();
                                forelder.visPanel(HovedVindu.HovedVindu);
                                forelder.Size();
                            }          
                         } //slutt på sjekkdekning
                        else
                        {
                                visFeilMelding("Du har ikke dekning for denne forsikringen!");
                        }
            }else
             {
             visFeilMelding("Tegning av skademelding avbrutt");
            }
                }   
            }
        }catch(NumberFormatException nfe)
        {
            visFeilMelding("Pass på å skrive desimaltall i taksering!");
        }
        /*catch(NullPointerException npe)
        {
            visFeilMelding("Null Pointer");
        }*/
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
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == registrer) {
            registrerSkademelding();
        } else if (e.getSource() == avbryt) {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();

        }  
    }
    
    
    
    
}