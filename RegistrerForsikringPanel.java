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
 * Panel for å velge hvilken type forsikring man vil registrere ved å trykke på en av knappene i toppanelet
 * @author Sander
 */

public class RegistrerForsikringPanel extends JPanel implements ActionListener
{

    
    private JTextField personnummerfelt, bileierfelt, baateierfelt, regnummerfelt, typefelt, kjorelengdefelt;
    private JTextField adressefelt, boligtypefelt;
    
    private JSlider areal, byggeaar, modell, hestekrefter, fot;
    
    private JComboBox<String> materiale, reiseomraade, yrke;
    private JComboBox<Integer> skadefri, antMaaneder;
    
    private JRadioButton Bil, Baat, Bolig, Hytte, Reise;
    
    private Icon BilIcon, BoatIcon, BoligIcon, HytteIcon, ReiseIcon;
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
    
    private JButton registrer;
    private JButton avbryt;
    
    private JLabel overskrift;
    
    private HovedVindu forelder;
    
    
    
    
    public RegistrerForsikringPanel(HovedVindu forelder)
    {
        super(new BorderLayout());
        this.forelder = forelder;
       
        setGrensesnitt();
       
        
         add(toppanel, BorderLayout.PAGE_START);
         add(midtpanel, BorderLayout.CENTER );
         add(knappepanel, BorderLayout.PAGE_END);
         
         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int høyde = skjerm.height;
         
         
         
         
    }
    
   
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
        registrer = new JButton("Beregn pris på forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Tilbake");
        avbryt.addActionListener(this);
        
        overskrift = new JLabel("Velg en forsikring ved å klikke på ikonene over!");
        overskrift.setForeground(Color.WHITE);
        Font font = new Font("Verdana", Font.BOLD, 16);
        overskrift.setFont(font);
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel.add(overskrift);
        
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
        knappepanel.add(registrer);

        
        midtpanel = new JPanel(new BorderLayout());
        midtpanel.add(overskriftpanel, BorderLayout.PAGE_START);
        
        
        overskriftpanel.setBackground(Color.decode("#669CFF"));
        toppanel.setBackground(Color.decode("#FFFFFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        midtpanel.setBackground(Color.decode("#669CFF"));
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == Bil)
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
         else if(e.getSource() == Reise)
        {
                    forelder.doClick(5);
        }
        else if(e.getSource() == registrer)
        {
            visFeilMelding("Du må velge en forsikring og fylle ut alle felter først!");
        }
       
        else if(e.getSource() == avbryt)
       {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();
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
    
    
}//slutt på klasse
