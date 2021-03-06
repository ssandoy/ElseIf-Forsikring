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
 * Panel for å registrere reiseforsikring ved å fylle ut alle feltene
 * @author Sander
 */

public class RegistrerReiseForsikringPanel extends JPanel implements ActionListener
{
    
    private JTextField personnummerfelt;
    
    private JComboBox<String> reisefelt, statusfelt;
    
    private JLabel personlabel, reiselabel, statuslabel;
    
    private JRadioButton Bil, Baat, Bolig, Hytte, Reise;
    
    private Icon BilIcon, BoatIcon, BoligIcon, HytteIcon, ReiseIcon;
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
    
    private JButton beregn;
    private JButton avbryt;
    
    private HovedVindu forelder;
    private Kunderegister kregister;
    private Forsikringsregister fregister;
    
    private String[] reise = {"Velg område", "INNLANDS", "SKANDINAVIA", "EUROPA", "VERDEN"};
    private String[] status = {"Velg din status", "Student", "Voksen", "Honnør"};
    
    
    public RegistrerReiseForsikringPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
    {
        this.forelder  = forelder;
        this.kregister = kregister;
        this.fregister = fregister;
        
        setLayout(new BorderLayout());
        setGrensesnitt();
        
        add(toppanel, BorderLayout.PAGE_START);
        add(midtpanel, BorderLayout.CENTER );
        add(knappepanel, BorderLayout.PAGE_END);
         
         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int høyde = skjerm.height;
         
         forelder.setSize(bredde/2, høyde/3);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);

    }
    
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
        personnummerfelt = new JTextField(10);
        reisefelt        = new JComboBox<String>(reise);
        statusfelt       = new JComboBox<String>(status);
        
        personlabel      = new JLabel("     Personnummer: ");
        reiselabel       = new JLabel("     Velg område for forsikringen: ");
        statuslabel      = new JLabel("     Velg din rabatt: ");
        
        personlabel.setForeground(Color.WHITE);
        reiselabel.setForeground(Color.WHITE);
        statuslabel.setForeground(Color.WHITE);
        
        beregn = new JButton("Beregn pris på forsikring");
        beregn.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        try
        {
        BilIcon          = new ImageIcon(getClass().getResource("Bilder/BilIcon.png"));
        BoatIcon         = new ImageIcon(getClass().getResource("Bilder/BoatIcon.png"));
        BoligIcon        = new ImageIcon(getClass().getResource("Bilder/HusIcon.png"));
        HytteIcon        = new ImageIcon(getClass().getResource("Bilder/HytteIcon.png"));
        ReiseIcon        = new ImageIcon(getClass().getResource("Bilder/ReiseIcon.png"));
        
        Bil         = new JRadioButton(BilIcon);
        Bil.addActionListener(this);
        Baat        = new JRadioButton(BoatIcon);
        Baat.addActionListener(this);
        Bolig       = new JRadioButton(BoligIcon);
        Bolig.addActionListener(this);
        Hytte       = new JRadioButton(HytteIcon);
        Hytte.addActionListener(this);
        Reise       = new JRadioButton(ReiseIcon);
        Reise.addActionListener(this);
        
        } catch(NullPointerException npe)
        {
            Bil = new JRadioButton("Bil-Forsikring");
            Bil.addActionListener(this);
            Baat = new JRadioButton("Båt-Forsikring");
            Baat.addActionListener(this);
            Bolig = new JRadioButton("Bolig-Forsikring");
            Bolig.addActionListener(this);
            Hytte = new JRadioButton("Hytte-Forsikring");
            Hytte.addActionListener(this);
            Reise = new JRadioButton("Reise-Forsikring");
            Reise.addActionListener(this);
        }
        
        toppanel = new JPanel(new GridLayout(1, 5, 0, 0));
        toppanel.add(Bil);
        toppanel.add(Baat);
        toppanel.add(Bolig);
        toppanel.add(Hytte);
        toppanel.add(Reise);
        
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel.add(avbryt);
        knappepanel.add(beregn);

        
        midtpanel = new JPanel(new GridLayout(3,0,0,0));
        midtpanel.add(personlabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(statuslabel);
        midtpanel.add(statusfelt);
        midtpanel.add(reiselabel);
        midtpanel.add(reisefelt);
        
        toppanel.setBackground(Color.decode("#FFFFFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        midtpanel.setBackground(Color.decode("#669CFF"));
    }

    
    public void registrer() //metode som registrerer reiseforsikring og sjekker om det er noen feil 
    {
        try{
        
            String personnummer = personnummerfelt.getText();
            String reisedekning   = reisefelt.getItemAt(reisefelt.getSelectedIndex());
            String status     = statusfelt.getItemAt(statusfelt.getSelectedIndex());

            if(personnummer.length() == 0)
            {
                visFeilMelding("Du må fylle inn kundens personnummer");
            } else if(reisedekning.equals("Velg område"))
            {
                visFeilMelding("Du må velge område du vil forsikringen skal dekke");
            }else if(statusfelt.equals("Velg din status"))
            {
                visFeilMelding("Du må velge status");
            }else
            {
                Forsikringskunde k = kregister.getKunde(personnummer);

                 if(k == null)
                {
                         visFeilMelding("Ingen kunde med det personnummeret!");
                }else
                 {
                     String fnr = fregister.genererNummer();
                     Reiseforsikring f = new Reiseforsikring(k, status ,reisedekning);
                     f.setForsikringsnummer(fnr);
                     f.setType("REISE-FORSIKRING");
                     f.beregnPremie();
                     int result = JOptionPane.showConfirmDialog(null, 
                           "Årlig pris på din forsikring: " + f.getPremie() + ",-" + 
                            "\nVil du tegne denne forsikringen?", null , JOptionPane.YES_NO_OPTION);
                          if(result == JOptionPane.YES_OPTION) 
                          {
                              if(fregister.leggTil(f) && k.getForsikringer().add(f))
                              {
                                  double nypremie = f.getPremie() + k.getPremie();
                                  k.setPremie(nypremie);
                                    visMelding("Forsikring registrert på kunde:\n" + k.getNavn() + "\n" + f.toString());    
                                    forelder.addLogo();
                                    forelder.visPanel(HovedVindu.HovedVindu);
                                    forelder.Size();
                               } //slutt på fregister.leggTil(f)
                               else 
                                 {
                                  visFeilMelding("Feil informasjon fylt inn. Prøv igjen");
                                 }
                          } //slutt på YES_OPTION
                  } //slutt på kunde!=null
            } 
        } //slutt på try
        catch(NullPointerException npe)
            {
                visFeilMelding("NullPointer");
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
         if(e.getSource() == avbryt)
       {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();
       }else if(e.getSource() == beregn)
       {
           registrer();
       }
       else if(e.getSource() == Bil)
        {
          forelder.doClick(1);
        }
       else if(e.getSource() == Baat)
        {
            forelder.doClick(2);
        }
        else if(e.getSource() == Bolig)
        {
            forelder.doClick(3);
        }
         else if(e.getSource() == Hytte)
        {
          forelder.doClick(4);
        }
    }
}//slutt på klasse
