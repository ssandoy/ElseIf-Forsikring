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
 *
 * @author ssandoy
 */
public class RegistrerHytteForsikringPanel extends JPanel implements ActionListener
{
     private JTextField personnummerfelt, adressefelt;
    
    private JComboBox<Integer>  antMaanederfelt;
    
    private JSlider arealfelt, byggeaarfelt;
    
    private JLabel personlabel, adresslabel, maanedlabel, areallabel, bygglabel;
    
    private JRadioButton Bil, Baat, Bolig, Hytte, Reise;
    
    private Icon BilIcon, BoatIcon, BoligIcon, HytteIcon, ReiseIcon;
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
    
    private JButton registrer;
    private JButton avbryt;
    
    HovedVindu forelder;

    Forsikringsregister fregister;
    Kunderegister kregister;
    
    Integer[] antMaaneder = {0,1,2,3,4,5};
    
    public RegistrerHytteForsikringPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
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
         
         
    }
    
    
    
    public void setGrensesnitt()
    {
        personnummerfelt = new JTextField(10);
        adressefelt      = new JTextField(10);
        antMaanederfelt    = new JComboBox<Integer>(antMaaneder);
        arealfelt        = new JSlider(JSlider.HORIZONTAL, 30, 150, 50);
        byggeaarfelt     = new JSlider(JSlider.HORIZONTAL, 1900, 2015, 1940);
        
        personlabel      = new JLabel("Personnummer: ");
        adresslabel      = new JLabel("Adresse: ");
        maanedlabel    = new JLabel("Antall bo-måneder i året: ");
        areallabel       = new JLabel("Boareal: " + arealfelt.getValue() + " km2");
        bygglabel        = new JLabel("Byggeår: " + byggeaarfelt.getValue());
        
        registrer = new JButton("Beregn pris på forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        SliderEvent e = new SliderEvent();
        arealfelt.addChangeListener(e);
        byggeaarfelt.addChangeListener(e);
        
        
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
      
        
        midtpanel = new JPanel(new GridLayout(5,0,0,0));
        midtpanel.add(personlabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(adresslabel);
        midtpanel.add(adressefelt);
        midtpanel.add(maanedlabel);
        midtpanel.add(antMaanederfelt);
        midtpanel.add(areallabel);
        midtpanel.add(arealfelt);
        midtpanel.add(bygglabel);
        midtpanel.add(byggeaarfelt);
        
        toppanel.setBackground(Color.decode("#E0D1FF"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        midtpanel.setBackground(Color.decode("#E57E7E"));
    }
    
    public void registrer()
    {
            String personnummer = personnummerfelt.getText();
            String adresse      = adressefelt.getText();
            int mnder        = antMaanederfelt.getItemAt(antMaanederfelt.getSelectedIndex());
            int areal           = arealfelt.getValue();
            int byggeaar        = byggeaarfelt.getValue();
            
            if(personnummer.length() == 0 || adresse.length() == 0)
            {
                visFeilMelding("Du må skrive verdier i feltene");
            } else
            {
                Forsikringskunde k = kregister.getKunde(personnummer);
                  
                  if(k == null)
                  {
                      visFeilMelding("Ingen kunde med det personnummeret!");
                  }
                  else
                 {
                     
                 }
                
                
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
         else if(e.getSource() == Reise)
        {
             forelder.doClick(5);
        }
        
    }
    
    public class SliderEvent implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) 
        {
            if(e.getSource() == arealfelt) 
            {
                areallabel.setText("Areal: " + arealfelt.getValue() + " km2") ;
            }
            else if(e.getSource() == byggeaarfelt)
            {
                bygglabel.setText("Byggeår: " + byggeaarfelt.getValue());
            }
            
        } 
    }
    
}
