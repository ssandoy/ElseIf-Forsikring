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
import javax.swing.border.Border;

/**
 * Panel for å registrere en kunde ved å fylle ut alle feltene
 * @author Sander
 */

public class RegistrerKundePanel extends JPanel implements ActionListener {

    private JTextField personnummerfelt, fornavnsfelt, etternavnsfelt, adressefelt, telefonfelt;
    private JLabel plabel, flabel, elabel, alabel, tlabel;
    private JCheckBox fakturabox;

    private Kunderegister kregister;

    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel toppanel;
    private JPanel overskriftpanel;

    private JButton registrer;
    private JButton avbryt;

    private JLabel overskrift;

    private HovedVindu forelder;

    public RegistrerKundePanel(HovedVindu forelder, Kunderegister kregister ) {
        super(new BorderLayout());
        this.forelder = forelder;
        this.kregister = kregister;

        setGrensesnitt();
        add(toppanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);

        toppanel.setBackground(Color.decode("#669CFF"));
        overskriftpanel.setBackground(Color.decode("#669CFF"));
        
    }

    public void setGrensesnitt() //initialiserer tekstfeltene, panelene og ikonene
    {
        personnummerfelt = new JTextField(10);
        fornavnsfelt = new JTextField(10);
        etternavnsfelt = new JTextField(10);
        adressefelt = new JTextField(10);
        telefonfelt = new JTextField(10);
        overskrift = new JLabel("REGISTRER KUNDE");
        Font font = new Font("Verdana", Font.BOLD, 16);
        overskrift.setFont(font);

        plabel = new JLabel("       Personnummer:");
        flabel = new JLabel("       Fornavn:");
        elabel = new JLabel("       Etternavn:");
        alabel = new JLabel("       Adresse:");
        tlabel = new JLabel("       Telefonnummer(8 siffer):");
        
        overskrift.setForeground(Color.WHITE);
        plabel.setForeground(Color.WHITE);
        flabel.setForeground(Color.WHITE);
        elabel.setForeground(Color.WHITE);
        alabel.setForeground(Color.WHITE);
        tlabel.setForeground(Color.WHITE);

        fakturabox = new JCheckBox();
        
        registrer = new JButton("Registrer");
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        registrer.addActionListener(this);

  

        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(5, 0, 0, 0));
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
        tekstpanel.add(tlabel);
        tekstpanel.add(telefonfelt);
        
        knappepanel.add(avbryt);
        knappepanel.add(registrer);

        tekstpanel.setBackground(Color.decode("#669CFF"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        
        Border border = BorderFactory.createLineBorder(Color.decode("#669CFF"));
        tekstpanel.setBorder(BorderFactory.createCompoundBorder(border, 
        BorderFactory.createEmptyBorder(0, 30, 0, 0)));
        
        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
        toppanel.add(tekstpanel, BorderLayout.CENTER);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int hoyde = skjerm.height;

        forelder.setSize(bredde / 2, hoyde/3);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
        
    }
    
public void Registrer() //metode som registrerer en ny kunde og sjekker om det er noen feil 
    {
        try{
            String personnummer = personnummerfelt.getText();
            String fornavn = fornavnsfelt.getText();
            String etternavn = etternavnsfelt.getText();
            String adresse = adressefelt.getText();
            String tlfnummer = telefonfelt.getText();

            if (personnummer.length() != 0 && fornavn.length() != 0 && etternavn.length() != 0
                    && adresse.length() != 0 && tlfnummer.length() != 0) 
            {
                if(godkjennPNummer() && godkjennTLFnummer())
                {
                    if(kregister.finnes(personnummer))
                    {
                        visFeilMelding("Finnes allerede en kunde med dette personnummeret. Forsøk på identitestyveri!");
                    }
                    String knr = kregister.genererNummer();
                    Forsikringskunde kunde = new Forsikringskunde(personnummer, fornavn, etternavn, adresse,
                        tlfnummer);
                    kunde.setKundenummer(knr);
                    if (kregister.leggTil(kunde)) 
                    {
                    visMelding("Kunde registrert!");
                    forelder.addLogo();
                    forelder.visPanel(HovedVindu.HovedVindu);
                    forelder.Size();

                     }
                } //slutt på godkjennPNummer()
                else
                    visFeilMelding("Skriv inn et gyldig personnummer og telefonnummer. 11 siffer og 8 siffer");
            } //slutt på personnummer.length()
            else 
            {
                visFeilMelding("Vennligst fyll inn alle feltene");
            }
        }//slutt på try
        catch(NullPointerException npe)
        {
            visFeilMelding("Null Pointer");
        }
    }
    
//Metode som sjekker om innskreven nummer er et gyldig norsk personnummer
    public boolean godkjennPNummer()
    {
        String regex = "[0-9]{11}";
        String personnummer = personnummerfelt.getText();
        if(personnummer.matches(regex))
            return true;
         else
            return false;
    }

    //Metode som sjekker om innskreven nummer er et gyldig norsk telefonnummer
    public boolean godkjennTLFnummer()
    {
        String regex = "[0-9]{8}";
        String personnummer = telefonfelt.getText();
        if(personnummer.matches(regex))
            return true;
         else
            return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrer) 
        {
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
