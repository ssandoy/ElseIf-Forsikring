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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panel for å registrere båtforsikring ved å fylle ut alle feltene
 * @author Sander
 */

public class RegistrerBaatForsikringPanel extends JPanel implements ActionListener
{
    private JTextField personnummerfelt, baateierfelt, regnummerfelt, typefelt;
    
    private JLabel plabel, blabel, rlabel, tlabel, hlabel, mlabel, flabel;
    
    private JSlider modell, hestekrefter, fot;
    
    private JComboBox<Integer> skadefri;
    
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
    
    Integer[] skade = {0, 1, 2, 3, 4, 5};
    
    Forsikringsregister fregister;
    Kunderegister kregister;

    public RegistrerBaatForsikringPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
    {
        this.forelder  = forelder;
        this.fregister = fregister;
        this.kregister = kregister;
        
        setLayout(new BorderLayout());
        setGrensesnitt();
        
        add(toppanel, BorderLayout.PAGE_START);
        add(midtpanel, BorderLayout.CENTER );
        add(knappepanel, BorderLayout.PAGE_END);
        
         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int hoyde = skjerm.height;
   
         forelder.setSize(bredde/2, hoyde/2);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
    }

    
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
         personnummerfelt = new JTextField(10);
         baateierfelt = new JTextField(10);
         regnummerfelt = new JTextField(10);
         typefelt = new JTextField(10);
         modell = new JSlider(JSlider.HORIZONTAL, 1950, 2015, 1990);
         hestekrefter = new JSlider(JSlider.HORIZONTAL, 0, 150, 15);
         fot = new JSlider(JSlider.HORIZONTAL, 8, 50, 20);
         
         
        plabel = new JLabel("       Personnummer: ");
        blabel = new JLabel("       Båteier: ");
        rlabel = new JLabel("       Registreringsnummer: ");
        tlabel = new JLabel("       Båttype: ");
        mlabel = new JLabel("       Årsmodell: " + modell.getValue());
        flabel = new JLabel("       Antall fot: " + fot.getValue());
        hlabel = new JLabel("       Antall hestekrefter: " + hestekrefter.getValue());
        
        plabel.setForeground(Color.WHITE);
        blabel.setForeground(Color.WHITE);
        rlabel.setForeground(Color.WHITE);
        tlabel.setForeground(Color.WHITE);
        mlabel.setForeground(Color.WHITE);
        flabel.setForeground(Color.WHITE);
        hlabel.setForeground(Color.WHITE);
        
        SliderEvent e = new SliderEvent();
        fot.addChangeListener(e);
        hestekrefter.addChangeListener(e);
        modell.addChangeListener(e);
        
        registrer = new JButton("Beregn pris på forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        try{
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
        
        
        midtpanel = new JPanel(new GridLayout(7,0,0,0));
        midtpanel.add(plabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(blabel);
        midtpanel.add(baateierfelt);
        midtpanel.add(rlabel);
        midtpanel.add(regnummerfelt);
        midtpanel.add(tlabel);
        midtpanel.add(typefelt);
        midtpanel.add(mlabel);
        midtpanel.add(modell);
        midtpanel.add(hlabel);
        midtpanel.add(hestekrefter);
        midtpanel.add(flabel);
        midtpanel.add(fot);
       
        
        toppanel.setBackground(Color.decode("#FFFFFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        midtpanel.setBackground(Color.decode("#669CFF"));
    }
    
public void registrer() //metode som registrerer båtforsikring og sjekker om det er noen feil 
    {
        try{
                String personnummer     = personnummerfelt.getText();
                String baateier     = baateierfelt.getText();
                String regnummer    = regnummerfelt.getText();
                String baattype     = typefelt.getText();
                int baatmodell      = modell.getValue();
                int motor           = hestekrefter.getValue();
                int lengde          = fot.getValue();

             if(personnummer.length() == 0 || baateier.length() == 0 || baattype.length() == 0)
             {
                 visFeilMelding("Skriv inn verdier i feltene!");
             }
             else
             {
                 if(kregister.finnes(personnummer))
                 {
                          Forsikringskunde k = kregister.get(personnummer);
                          String fnr = fregister.genererNummer();
                          Baatforsikring b = new Baatforsikring(k, baateier, regnummer, baattype, baatmodell, motor, lengde);
                          b.setForsikringsnummer(fnr);
                          b.setType("BÅT-FORSIKRING");
                          b.beregnPremie();
                          int result = JOptionPane.showConfirmDialog(null, "Pris på din forsikring: " + b.getPremie() + ",-" + "\nVil du tegne denne forsikringen?", null , JOptionPane.YES_NO_OPTION);
                             if(result == JOptionPane.YES_OPTION) 
                             {
                              if(fregister.leggTil(b))
                              {
                              k.addForsikring(b);
                              visMelding("Forsikring registrert på kunde:\n" + k.getNavn() + "\n" + b.toString());
                              forelder.addLogo();
                              forelder.visPanel(HovedVindu.HovedVindu);
                              forelder.Size();
                              } else 
                               {
                                visFeilMelding("Feil info skrevet inn. Prøv igjen");                                
                               }
                             }else
                             {
                              visFeilMelding("Tegning av forsikring avbrutt.");

                             }

                      
             } else
                 {
                     visFeilMelding("Personnummeret finnes ikke i registeret");
                 }
             }
    
        }
        catch(NullPointerException npe)
        {
            visFeilMelding("Null Pointer");
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
       }
        else if(e.getSource() == registrer)
        {
            registrer();

                
        }
        else if(e.getSource() == Bil)
        {
            forelder.doClick(1);
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
    }
        

     public class SliderEvent implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) 
        {
            if(e.getSource() == hestekrefter) 
            {
                hlabel.setText("       Antall hestekrefter: " + hestekrefter.getValue());
            }
            else if(e.getSource() == fot)
            {
                flabel.setText("       Antall fot: " + fot.getValue());
            }
            else if(e.getSource() == modell)
            {
                mlabel.setText("       Årsmodell: " + modell.getValue());
            }
        } 
    }
    
    
}

