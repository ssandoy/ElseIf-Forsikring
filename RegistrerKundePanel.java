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
public class RegistrerKundePanel extends JPanel implements ActionListener {

    private JTextField personnummerfelt, fornavnsfelt, etternavnsfelt, adressefelt, fakturafelt;
    private JLabel plabel, flabel, elabel, alabel, falabel;
    private JCheckBox fakturabox;

    Kunderegister register;

    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel toppanel;
    private JPanel overskriftpanel;

    private JButton registrer;
    private JButton avbryt;

    private JLabel overskrift;

    private HovedVindu forelder;

    public RegistrerKundePanel(HovedVindu forelder, Kunderegister register ) {
        super(new BorderLayout());
        this.forelder = forelder;
        this.register = register;

        setGrensesnitt();
        add(toppanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);

        
        toppanel.setBackground(Color.decode("#5E5E5E"));
        overskriftpanel.setBackground(Color.decode("#5E5E5E"));

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

       
        setPreferredSize( new Dimension( 640, 480 ) );
        
    }

    public void setGrensesnitt()
    {
        personnummerfelt = new JTextField(10);
        fornavnsfelt = new JTextField(10);
        etternavnsfelt = new JTextField(10);
        adressefelt = new JTextField(10);
        fakturafelt = new JTextField(10);
        overskrift = new JLabel("Registrer kunde");

        plabel = new JLabel(" Personnummer");
        flabel = new JLabel(" Fornavn");
        elabel = new JLabel(" Etternavn");
        alabel = new JLabel(" Adresse");
        falabel = new JLabel(" Fakturaadresse");

        fakturabox = new JCheckBox();
        
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        registrer.addActionListener(this);

  

        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(5, 0, 5, 5));
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        overskriftpanel.add(overskrift);

        tekstpanel.add(plabel);
        tekstpanel.add(personnummerfelt);
        tekstpanel.add(flabel);
        tekstpanel.add(fornavnsfelt);
        tekstpanel.add(elabel);
        tekstpanel.add(etternavnsfelt);
        tekstpanel.add(alabel);
        tekstpanel.add(adressefelt);
        tekstpanel.add(falabel);
        tekstpanel.add(fakturafelt);
        
        knappepanel.add(registrer);
        knappepanel.add(avbryt);

        tekstpanel.setBackground(Color.decode("#5E5E5E"));
        knappepanel.setBackground(Color.decode("#5E5E5E"));
        
        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(tekstpanel, BorderLayout.CENTER);
    }
    
    public void Registrer() {
        String personnummer = personnummerfelt.getText();
        String fornavn = fornavnsfelt.getText();
        String etternavn = etternavnsfelt.getText();
        String adresse = adressefelt.getText();
        String faktura = fakturafelt.getText();

        if (personnummer.length() != 0 && fornavn.length() != 0 && etternavn.length() != 0
                && adresse.length() != 0 && faktura.length() != 0) {
            if(godkjennPNummer())
            {
                Forsikringskunde kunde = new Forsikringskunde(personnummer, fornavn, etternavn, adresse,
                    faktura);
                if (register.leggTil(kunde)) 
                {
                visMelding("Kunde registrert!");
                forelder.addLogo();
                forelder.visPanel(HovedVindu.HovedVindu);
                forelder.Size();

                 }
            } else
                visFeilMelding("Skriv inn et gyldig personnummer. 11 siffer");
        } else {
            visFeilMelding("Vennligst fyll inn alle feltene");
        }
    }
    
    public boolean godkjennPNummer()
    {
        String regex = "[0-9]{11}";
        String personnummer = personnummerfelt.getText();
        if(personnummer.matches(regex))
            return true;
         else
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrer) {
            Registrer();
        } else if (e.getSource() == avbryt) 
        {
            forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();
        }
    }

    public void visMelding(String melding) {
        JOptionPane.showMessageDialog(null, melding);
    }

    public void visFeilMelding(String melding) {
        JOptionPane.showMessageDialog(this, melding, "Problem",
                JOptionPane.ERROR_MESSAGE);
    }

}
