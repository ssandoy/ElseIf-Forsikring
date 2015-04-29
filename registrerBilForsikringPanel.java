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
public class registrerBilForsikringPanel extends JPanel implements ActionListener
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
    
    Forsikringsregister register;
    
    public registrerBilForsikringPanel(HovedVindu forelder, Forsikringsregister register)
    {
        this.forelder = forelder;
        this.register = register;
        
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
         forelder.pack();
    }

    
    public void setGrensesnitt()
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
        mlabel = new JLabel("Modell");
        slabel = new JLabel("Antall år skadefri:");
        
        registrer = new JButton("Registrer forsikring");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
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
        Reise       = new JRadioButton(ReiseIcon);
        
        toppanel = new JPanel(new GridLayout(1, 5, 0, 0));
        toppanel.add(Bil);
        toppanel.add(Baat);
        toppanel.add(Bolig);
        toppanel.add(Hytte);
        toppanel.add(Reise);
        
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel.add(registrer);
        knappepanel.add(avbryt);
        
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
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == avbryt)
       {
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();
       }
        else if(e.getSource() == Baat)
        {
            forelder.doClick(2);
        }
        else if(e.getSource() == Bolig)
        {
            forelder.doClick(3);
        }
    }
    
}
