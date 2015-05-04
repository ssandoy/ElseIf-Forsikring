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
    
    private JButton registrer;
    private JButton avbryt;
    
    HovedVindu forelder;
    Forsikringsregister fregister;
    
    String[] reise = {"Velg område", "INNLANDS", "SKANDINAVIA", "EUROPA", "VERDEN"};
    String[] status = {"Velg din status", "Student", "Voksen", "Honnør"};
    
    
    public RegistrerReiseForsikringPanel(HovedVindu forelder, Forsikringsregister fregister)
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
        reisefelt        = new JComboBox<String>(reise);
        statusfelt       = new JComboBox<String>(status);
        
        personlabel      = new JLabel("Personnummer: ");
        reiselabel       = new JLabel("Velg område for forsikringen: ");
        statuslabel      = new JLabel("Velg din rabatt: ");
        
        registrer = new JButton("Registrer forsikring");
        registrer.addActionListener(this);
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
        knappepanel.add(registrer);
        knappepanel.add(avbryt);
        
        midtpanel = new JPanel(new GridLayout(3,0,0,0));
        midtpanel.add(personlabel);
        midtpanel.add(personnummerfelt);
        midtpanel.add(statuslabel);
        midtpanel.add(statusfelt);
        midtpanel.add(reiselabel);
        midtpanel.add(reisefelt);
        
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
           forelder.addLogo();
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
}
