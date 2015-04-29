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
public class registrerBoligForsikringPanel extends JPanel implements ActionListener 
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
    
    private JButton registrer;
    private JButton avbryt;
    
    HovedVindu forelder;
    
    Forsikringsregister fregister;
    
    String[] materialtype = {"Velg materialtype", "TRE", "MUR" };
    String[] boligtype = {"Velg boligtype", "LEILIGHET", "ENEBOLIG"};
    
    
    public registrerBoligForsikringPanel(HovedVindu forelder, Forsikringsregister fregister)
    {
        this.forelder  = forelder;
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
         
         forelder.setSize(bredde/2, høyde-200);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
         forelder.pack();
        
    }
    
    
    public void setGrensesnitt()
    {
        personnummerfelt = new JTextField(10);
        adressefelt      = new JTextField(10);
        boligtypefelt    = new JComboBox(boligtype);
        materialfelt     = new JComboBox(materialtype);
        arealfelt        = new JSlider(JSlider.HORIZONTAL, 30, 150, 50);
        byggeaarfelt     = new JSlider(JSlider.HORIZONTAL, 1900, 2015, 1940);
        
        personlabel      = new JLabel("Personnummer: ");
        adresslabel      = new JLabel("Adresse: ");
        typelabel        = new JLabel("Boligtype: ");
        materiallabel    = new JLabel("Byggemateriale: ");
        areallabel       = new JLabel("Boareal: ");
        bygglabel        = new JLabel("Byggeår: ");
        
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
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
         if(e.getSource() == avbryt)
        {
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
        
    }
    
}
