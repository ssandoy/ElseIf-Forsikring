/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author YAAKOUBD
 */
public class SlettForsikringPanel extends JPanel implements ActionListener{
    
    private JTextField personnummerfelt, forsikringsnummerfelt;
    
    private JButton slett, tilbake;
    
    private JLabel overskrift, forsikringsnummer, personnummer;

    private HovedVindu forelder;
    
    Kunderegister kregister;
    Forsikringsregister fregister;

    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel toppanel;
    private JPanel overskriftpanel;
    
    
    public SlettForsikringPanel(HovedVindu forelder,Kunderegister kregister, Forsikringsregister fregister)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        this.fregister = fregister;
        
        slett = new JButton("Slett Forsikring");
        tilbake = new JButton("Tilbake");
        forsikringsnummerfelt = new JTextField(10);
        personnummerfelt = new JTextField(10);
        forsikringsnummer = new JLabel("Forsikringsnummer: ");
        personnummer = new JLabel("Personnummer: ");
        overskrift = new JLabel("Slett Forsikring");
        
        slett.addActionListener(this);
        tilbake.addActionListener(this);
        
        this.forelder = forelder;
        
        
        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(2, 0, 5, 5));
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskriftpanel.add(overskrift);
        
        tekstpanel.add(personnummer);
        tekstpanel.add(personnummerfelt);
        tekstpanel.add(forsikringsnummer);
        tekstpanel.add(forsikringsnummerfelt);
        
        
        knappepanel.add(slett);
        knappepanel.add(tilbake);

        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(tekstpanel, BorderLayout.CENTER);

        add(toppanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);
        
        tekstpanel.setBackground(Color.decode("#E57E7E"));
        toppanel.setBackground(Color.decode("#E57E7E"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        overskriftpanel.setBackground(Color.decode("#E57E7E"));

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        forelder.setSize(bredde / 2, høyde - 500);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
     
    }
    
    /*public void slettForsikring()
    {
        
        try{
            String fnr = forsikringsnummerfelt.getText();
            String pnr = personnummerfelt.getText();
            Insurance forsikring = (Insurance)fregister.getObject(fnr);
            if(fregister.finnes(fnr))
            {

                if(forsikring.getKunde().equals(kregister.getKunde(pnr)))
                {
                    Forsikringskunde kunde = forsikring.getKunde();
                    
                    if(fregister.fjern(fnr))
                    {
                        if(kunde.fjernForsikring(forsikring))
                        {
                            visMelding("Forsikringen ble slettet!");
                            return;
                        }
                        else
                            visFeilMelding("Forsikringen kunne ikke bli fjernet fra kundens register");
                        
                    }
                    else
                        visFeilMelding("Forsikringen kunne ikke bli fjernet fra forsikringsregisteret");

                }
                else
                    visFeilMelding("Følgende forsikringsnummer tilpasser ikke følgende personnummer!");
            }
            else
                visFeilMelding("Forsikring med følgende nummer finnes ikke!");
        }
        
        
        catch(NullPointerException npe)
        {
            visFeilMelding("Null Pointer!");
        }
    }*/
    
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slett) {
            slettForsikring();
        } else if (e.getSource() == tilbake) {
            forelder.visPanel(HovedVindu.HovedVindu);
            forelder.Size();
            forelder.addLogo();

        }
    }
    
}