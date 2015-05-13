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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel som er for å slette en kunde fra registeret hvis kunden ikke har noen forsikringer lenger.
 * @author Amir
 */

public class SlettKundePanel extends JPanel implements ActionListener{
    
    private JTextField personnummerfelt;
    
    private JButton slett, tilbake;
    
    private JLabel overskrift, personnummer;

    private HovedVindu forelder;
    
    Kunderegister kregister;

    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel toppanel;
    private JPanel overskriftpanel;
    
    
    public SlettKundePanel(HovedVindu forelder, Kunderegister kregister)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        
        slett = new JButton("Slett Kunde");
        tilbake = new JButton("Tilbake");
        personnummerfelt = new JTextField(10);
        personnummer = new JLabel("Personnummer: ");
        overskrift = new JLabel("Slett Kunde");
        
        slett.addActionListener(this);
        tilbake.addActionListener(this);
        
        this.forelder = forelder;
        
        
        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(5, 0, 5, 5)); //Endre dette.
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskriftpanel.add(overskrift);
        
        tekstpanel.add(personnummer);
        tekstpanel.add(personnummerfelt);
        
        knappepanel.add(tilbake);
        knappepanel.add(slett);


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
        int hoyde = skjerm.height;

        forelder.setSize(bredde / 2, hoyde - 500);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
     
    }
    
    
    public void slettKunde()
    {
        try{
            String nr = personnummerfelt.getText();
            Forsikringskunde kunden = kregister.getKunde(nr);
            if(kunden == null)
            {
                visFeilMelding("Kunne ikke finne kunden med dette personnummeret!");
                return;
            }
            if(!kunden.getForsikringer().isEmpty())
            {
                visFeilMelding("Du må slette alle forsikringene til kunden før du sletter kunden!");
                return;
            }
            
            if(kregister.fjern(nr))
            {
                kunden = null;
                visMelding("Kunden ble slettet.");
            }
             
        }
        catch(NullPointerException npe)
        {
            visFeilMelding("Null Pointer!");
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slett) {
            slettKunde();
        } else if (e.getSource() == tilbake) {
            forelder.visPanel(HovedVindu.HovedVindu);
            forelder.Size();
            forelder.addLogo();

        }
    }
}