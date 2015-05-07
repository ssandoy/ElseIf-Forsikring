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
 *
 * @author YAAKOUBD
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
        this.forelder = forelder;
        this.kregister = kregister;
        setLayout(new BorderLayout());
        
        setGrensesnitt();

        add(toppanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);
        
        
        
        
        

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        forelder.setSize(bredde / 2, høyde - 500);
        forelder.setLocation(skjerm.width / 2 - forelder.getSize().width / 2, skjerm.height / 2 - forelder.getSize().height / 2);
     
    }
    
        public void setGrensesnitt()
        {
            personnummerfelt = new JTextField(10);
            personnummer = new JLabel("Personnummer: ");
            overskrift = new JLabel("Slett Kunde");
            
            slett = new JButton("Slett Kunde");
            tilbake = new JButton("Tilbake");

            slett.addActionListener(this);
            tilbake.addActionListener(this);
            
            tekstpanel = new JPanel(new GridLayout(5, 0, 5, 5));
            knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            toppanel = new JPanel(new BorderLayout());
            overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            overskriftpanel.add(overskrift);
            overskriftpanel.setBackground(Color.decode("#E57E7E"));
        
            tekstpanel.add(personnummer);
            tekstpanel.add(personnummerfelt);
            tekstpanel.setBackground(Color.decode("#E57E7E"));
        
            knappepanel.add(slett);
            knappepanel.add(tilbake);
            knappepanel.setBackground(Color.decode("#E57E7E"));

            toppanel.add(overskriftpanel, BorderLayout.PAGE_START);
            toppanel.add(tekstpanel, BorderLayout.CENTER);
            toppanel.setBackground(Color.decode("#E57E7E"));
            
            
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
         
            kregister.fjern(nr);
            kunden = null; 
            
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
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();
        }
    }
}