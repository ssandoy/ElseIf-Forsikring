/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ssandoy
 */
public class RegistrerBoligForsikringPanel extends JPanel implements ActionListener 
{

    private JTextField personnummerfelt, adressefelt;
    
    private JComboBox<String> materialfelt, boligtypefelt;
    
    private JSlider arealfelt, byggeaarfelt;
    
    private JLabel personlabel, adresslabel, materiallabel, typelabel, areallabel, bygglabel;
    
    private JRadioButton Bil, Baat, Bolig, Hytte, Reise;
    
    private Icon BilIcon, BoatIcon, BoligIcon, HytteIcon, ReiseIcon;
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
    
    private JButton beregn;
    private JButton avbryt;
    
    HovedVindu forelder;
    
    Forsikringsregister fregister;
    Kunderegister kregister;
    
    String[] materialtype = {"Velg materialtype", "TRE", "MUR" };
    String[] boligtyper = {"Velg boligtype", "LEILIGHET", "ENEBOLIG"};
    
    
    public RegistrerBoligForsikringPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
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
         
         forelder.setSize(bredde / 2, hoyde - 400);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
        
    }
    
    
    public void setGrensesnitt()
    {
        personnummerfelt = new JTextField(10);
        adressefelt      = new JTextField(10);
        boligtypefelt    = new JComboBox<String>(boligtyper);
        materialfelt     = new JComboBox<String>(materialtype);
        arealfelt        = new JSlider(JSlider.HORIZONTAL, 30, 150, 50);
        byggeaarfelt     = new JSlider(JSlider.HORIZONTAL, 1900, 2015, 1940);
        
        personlabel      = new JLabel("Personnummer: ");
        adresslabel      = new JLabel("Adresse: ");
        typelabel        = new JLabel("Boligtype: ");
        materiallabel    = new JLabel("Byggemateriale: ");
        areallabel       = new JLabel("Boareal: " + arealfelt.getValue() + " km2");
        bygglabel        = new JLabel("Byggeår: " + byggeaarfelt.getValue());
        
        beregn = new JButton("Beregn pris på forsikring");
        beregn.addActionListener(this);
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
        knappepanel.add(beregn);

        
        midtpanel = new JPanel(new GridLayout(6,0,0,0));
        midtpanel.add(personlabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(adresslabel);
        midtpanel.add(adressefelt);
        midtpanel.add(typelabel);
        midtpanel.add(boligtypefelt);
        midtpanel.add(materiallabel);
        midtpanel.add(materialfelt);
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
            String boligtype    = boligtypefelt.getItemAt(boligtypefelt.getSelectedIndex());
            String material     = materialfelt.getItemAt(materialfelt.getSelectedIndex());
            int areal           = arealfelt.getValue();
            int byggeaar        = byggeaarfelt.getValue();
                    
              if( personnummer.length() == 0 || adresse.length() == 0 || boligtype.length() == 0
                     || material.length() == 0)
              {
                  visFeilMelding("Du må skrive inn verdier i alle feltene!");
              } else if(material.equals("Velg materialtype"))
              {
                  visFeilMelding("Du må velge byggematerial");
              } 
                else if(boligtype.equals("Velg boligtype")) 
                 {
                  visFeilMelding("Du må velge boligtype");        
                 } else
                  {
                  
                  Forsikringskunde k = kregister.getKunde(personnummer);
                  
                  if(k == null)
                  {
                      visFeilMelding("Ingen kunde med det personnummeret!");
                  }
                  else
                 {
                     String fnr =  fregister.genererNummer();
                     Innboforsikring f = new Innboforsikring(k, adresse, byggeaar, areal, boligtype, material);
                     f.setForsikringsnummer(fnr);
                     f.setType("BOLIG-FORSIKRING");;
                     f.beregnPremie();
                     int result = JOptionPane.showConfirmDialog(null, 
                             "Pris på din forsikring: " + f.getPremie() + ",-" + 
                                     "\nVil du tegne denne forsikringen?", null , JOptionPane.YES_NO_OPTION);
                     if(result == JOptionPane.YES_OPTION) 
                         {
                     if(fregister.leggTil(f))
                     {
                        k.addForsikring(f);
                        visMelding("Forsikring registrert på kunde:\n" + k.toString());
                        System.out.println(fregister.toString());
                        System.out.println(f.getFNummer());
                        forelder.addLogo();
                        forelder.visPanel(HovedVindu.HovedVindu);
                        forelder.Size();
                     }
                     else 
                       {
                         visFeilMelding("Feil informasjon fylt inn. Prøv igjen");
                       }
                        } 
                  } 
                   }
              
              
            
        }
    
        public void eierInnbo()
        {
            
            
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
         else if(e.getSource() == beregn)
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
            if(e.getSource() == arealfelt) 
            {
                areallabel.setText("Boareal: " + arealfelt.getValue() + " km2") ;
            }
            else if(e.getSource() == byggeaarfelt)
            {
                bygglabel.setText("Byggeår: " + byggeaarfelt.getValue());
            }
            
        } 
    }
    
    
}
