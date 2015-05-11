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
public class RegistrerBilForsikringPanel extends JPanel implements ActionListener
{

    
    private JTextField personnummerfelt, bileierfelt, regnummerfelt, typefelt, kjorelengdefelt;
    
    private JLabel plabel, blabel, rlabel, tlabel, klabel, mlabel, slabel;
    
    private JSlider modell;
    
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
    
    public RegistrerBilForsikringPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
    {
        this.forelder   = forelder;
        this.fregister   = fregister;
        this.kregister  = kregister;
        
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

    
    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
         personnummerfelt = new JTextField(10);
        //bilpanel
        bileierfelt = new JTextField(10);
        regnummerfelt = new JTextField(10);
        typefelt = new JTextField(10);
        kjorelengdefelt = new JTextField(10);
        modell = new JSlider(JSlider.HORIZONTAL, 1950, 2015, 1990);
        skadefri = new JComboBox<Integer>(skade);
        
        plabel = new JLabel("Personnummer:");
        blabel = new JLabel("Bileier: ");
        rlabel = new JLabel("Registreringsnummer: ");
        tlabel = new JLabel("Biltype: ");
        klabel = new JLabel("Årlig kjørelengde: ");
        mlabel = new JLabel("Modell: " + modell.getValue());
        slabel = new JLabel("Antall år skadefri:");
        
        registrer = new JButton("Beregn pris på forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        SliderEvent e = new SliderEvent();
        modell.addChangeListener(e);
       
        
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
        knappepanel.add(avbryt);
        knappepanel.add(registrer);
        
        midtpanel = new JPanel(new GridLayout(7,0,0,0));
        midtpanel.add(plabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(blabel);
        midtpanel.add(bileierfelt);
        midtpanel.add(rlabel);
        midtpanel.add(regnummerfelt);
        midtpanel.add(tlabel);
        midtpanel.add(typefelt);
        midtpanel.add(mlabel);
        midtpanel.add(modell);
        midtpanel.add(klabel);
        midtpanel.add(kjorelengdefelt);
        midtpanel.add(slabel);
        midtpanel.add(skadefri);
        
        toppanel.setBackground(Color.decode("#E0D1FF"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        midtpanel.setBackground(Color.decode("#E57E7E"));
        
    }
    
    public void registrer() //metode som registrerer bilforsikring og sjekker om det er noen feil 
        {
            try{
            String personnummer = personnummerfelt.getText();
            String bileier      = bileierfelt.getText();
            String regnummer    = regnummerfelt.getText();
            String biltype      = typefelt.getText();
            int bilmodell       = modell.getValue();
            int kjorelengde     = Integer.parseInt(kjorelengdefelt.getText());
            String test         = kjorelengdefelt.getText();
            int skade           = skadefri.getItemAt(skadefri.getSelectedIndex());
                    
              if( personnummer.length() == 0 || bileier.length() == 0 || regnummer.length() == 0 
                      || biltype.length() == 0 || bilmodell == 0 || test.length() == 0)
              {
                  visFeilMelding("Skriv inn verdier i feltene!");
              }
              else
              {
                  Forsikringskunde k = kregister.getKunde(personnummer);
                  
                  if(k == null)
                  {
                      visFeilMelding("Ingen kunde med det personnummeret!");
                  }
                  else
                  {
                      String fnr = fregister.genererNummer();
                      Bilforsikring b = new Bilforsikring(k, bileier, regnummer, biltype, bilmodell, kjorelengde, skade);
                      b.setForsikringsnummer(fnr);
                      b.setType("BIL-FORSIKRING");
                      b.beregnPremie();
                      int result = JOptionPane.showConfirmDialog(null, "Pris på din forsikring: " + b.getPremie() + ",-" + "\nVil du tegne denne forsikringen?", null , JOptionPane.YES_NO_OPTION);
                         if(result == JOptionPane.YES_OPTION) 
                         {
                           if(fregister.leggTil(b))
                           {
                            k.addForsikring(b);
                            visMelding("Forsikring registrert på kunde:\n" + k.getNavn() + b.toString());
                            forelder.addLogo();
                            forelder.visPanel(HovedVindu.HovedVindu);
                            forelder.Size();
                           } else
                               visFeilMelding("Ikke lagt til");
                          }else
                            {
                             b = null;
                             visMelding("Tegning av forsikring avbrutt");
                            }
                     
                    }  
              }
            } catch(NumberFormatException nfe)
            {
             visFeilMelding("Skriv inn riktige verdier i feltene");
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
        
        
    }
 
    public class SliderEvent implements ChangeListener{


        @Override
        public void stateChanged(ChangeEvent e) 
        {
            if(e.getSource() == modell) 
            {
                mlabel.setText("Modell: " + modell.getValue());
            }
            
        } 
    }
    
}
