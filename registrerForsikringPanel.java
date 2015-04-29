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
public class registrerForsikringPanel extends JPanel implements ActionListener
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
    
    String[] materialtype =  {"VELG MATERIALTYPE", "MUR", "TRE"};
    String[] reiseområder = {};
    String[] yrker = {"VELG DIN YRKESSTATUS", "Student", "Deltidsjobb", "Fulltidsjobb"};
    Integer[] maaneder = {0, 1, 2, 3, 4, 5, 6};
    Integer[] skade = {0, 1, 2, 3, 4, 5};
    
    
    public registrerForsikringPanel(HovedVindu forelder)
    {
        super(new BorderLayout());
        this.forelder = forelder;
        
        setLayout(new BorderLayout());
        setGrensesnitt();
        
        
        
        
        
         add(toppanel, BorderLayout.PAGE_START);
         add(midtpanel, BorderLayout.CENTER );
         add(knappepanel, BorderLayout.PAGE_END);
         
         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int høyde = skjerm.height;
         
         forelder.setSize(bredde/2, høyde-200);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
         
    }
    
    //inistialiserer knapper, tekstfelter o.l
    public void setGrensesnitt()
    {
        personnummerfelt = new JTextField(10);
        //bilpanel
        bileierfelt = new JTextField(10);
        regnummerfelt = new JTextField(10);
        typefelt = new JTextField(10);
        kjorelengdefelt = new JTextField(10);
        skadefri = new JComboBox<Integer>(skade);
        
        //båtpanel
         baateierfelt = new JTextField(10);
         modell = new JSlider(JSlider.HORIZONTAL, 1950, 2015, 1990);
         hestekrefter = new JSlider(JSlider.HORIZONTAL, 0, 150, 15);
         fot = new JSlider(JSlider.HORIZONTAL, 8, 50, 20);
        
        
        //huspanel
         adressefelt = new JTextField(10); 
         boligtypefelt = new JTextField(10);
         areal = new JSlider(JSlider.HORIZONTAL,0,150,50);
         byggeaar = new JSlider(JSlider.HORIZONTAL, 1850,2015,1950);
         materiale = new JComboBox<String>(materialtype);
        
         //hyttepanel
         antMaaneder = new JComboBox<Integer>(maaneder);
        
      
        
        registrer = new JButton("Registrer forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        overskrift = new JLabel("Velg en forsikring ved å klikke på ikonene over!");
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel.add(overskrift);
        
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
        
        toppanel = new JPanel(new GridLayout(1, 5, 0, 0));
        toppanel.add(Bil);
        toppanel.add(Baat);
        toppanel.add(Bolig);
        toppanel.add(Hytte);
        toppanel.add(Reise);
        
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel.add(registrer);
        knappepanel.add(avbryt);
        
        midtpanel = new JPanel(new FlowLayout());
        midtpanel.add(overskriftpanel, BorderLayout.PAGE_START);
        
        
        overskriftpanel.setBackground(Color.decode("#E57E7E"));
        toppanel.setBackground(Color.decode("#E0D1FF"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        midtpanel.setBackground(Color.decode("#E57E7E"));
        
        
        
    }
    
    
        //Metode som bytter ut midtpanelet slik at man kan tegne bil-forsikring
        public void byttTilBilPanel()
        {
            midtpanel = new JPanel(new GridLayout(7,0,0,0));
            midtpanel.add(personnummerfelt);
            midtpanel.add(bileierfelt);
            midtpanel.add(regnummerfelt);
            midtpanel.add(typefelt);
            midtpanel.add(modell);
            midtpanel.add(kjorelengdefelt);
            midtpanel.add(skadefri);
            add(midtpanel, BorderLayout.CENTER );
            forelder.pack();
        }
    
        public void byttTilBaatPanel()
        {
            midtpanel = new JPanel(new GridLayout(7,0,0,0));
            midtpanel.add(personnummerfelt);
            midtpanel.add(baateierfelt);
            midtpanel.add(modell);
            add(midtpanel,BorderLayout.CENTER);
            forelder.pack();
            
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
        else if(e.getSource() == registrer)
        {
            
        }
       
        else if(e.getSource() == avbryt)
       {
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
    
    
}
