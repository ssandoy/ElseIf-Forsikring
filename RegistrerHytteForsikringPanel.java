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
 * Panel for å registrere hytteforsikring ved å fylle ut alle feltene
 * @author Sander
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
    
    private HovedVindu forelder;

    private Forsikringsregister fregister;
    private Kunderegister kregister;
    
    private Integer[] antMaaneder = {0,1,2,3,4,5};
    
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
         int hoyde = skjerm.height;
         
         forelder.setSize(bredde/2, hoyde/2);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
         
    }
    
    
    
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
        personnummerfelt = new JTextField(10);
        adressefelt      = new JTextField(10);
        antMaanederfelt    = new JComboBox<Integer>(antMaaneder);
        arealfelt        = new JSlider(JSlider.HORIZONTAL, 30, 150, 50);
        byggeaarfelt     = new JSlider(JSlider.HORIZONTAL, 1900, 2015, 1940);
        
        personlabel      = new JLabel("     Personnummer: ");
        adresslabel      = new JLabel("     Adresse: ");
        maanedlabel    = new JLabel("     Antall bo-måneder i året: ");
        areallabel       = new JLabel("     Boareal: " + arealfelt.getValue() + " m²");
        bygglabel        = new JLabel("     Byggeår: " + byggeaarfelt.getValue());
        
        personlabel.setForeground(Color.WHITE);
        adresslabel.setForeground(Color.WHITE);
        maanedlabel.setForeground(Color.WHITE);
        areallabel.setForeground(Color.WHITE);
        bygglabel.setForeground(Color.WHITE);
        
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
        
        toppanel.setBackground(Color.decode("#FFFFFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        midtpanel.setBackground(Color.decode("#669CFF"));
    }
    
    public void registrer()
    {
        try{
            String personnummer = personnummerfelt.getText();
            String adresse      = adressefelt.getText();
            int byggeaar        = byggeaarfelt.getValue();
            int areal           = arealfelt.getValue();
            int mnder           = antMaanederfelt.getItemAt(antMaanederfelt.getSelectedIndex());
            
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
                     String fnr =  fregister.genererNummer();
                     Fritidsboligforsikring f = new Fritidsboligforsikring(k, adresse, byggeaar, areal, mnder);
                     f.setForsikringsnummer(fnr);
                     f.setType("HYTTE-FORSIKRING");;
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
                     }
                     else 
                     {
                         visFeilMelding("Feil informasjon fylt inn. Prøv igjen");
                     }
                     
                 } //slutt på if(yes_option)
          
            } //slutt på if(k == null
          }//slutt på if(personnummer.length) 
        }//slutt på try
        catch(NullPointerException npe)
        {
            
        }
    }//slutt på metode
    
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
        
    }//slutt på metode
    
    public class SliderEvent implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) 
        {
            if(e.getSource() == arealfelt) 
            {
                areallabel.setText("     Boareal: " + arealfelt.getValue() + " m²") ;
            }
            else if(e.getSource() == byggeaarfelt)
            {
                bygglabel.setText("     Byggeår: " + byggeaarfelt.getValue());
            }
            
        }//slutt på metode 
    }
    
}//slutt på klasse
