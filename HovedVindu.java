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
public class HovedVindu extends JFrame implements ActionListener  
{
    
    private JPanel hovedpanel;
    
    private JPanel underpaneler;
    
    private JButton regKunde, visKunde, regForsikring, regSkade, finanser, historikk, statistikk
            ,søk;
    private Icon LOGO, regKundeIcon, visKundeIcon, regForsikringIcon, regSkadeIcon, finanserIcon,
            historikkIcon, statistikkIcon, søkIcon;
    
    public static final String HovedVindu = "-1";
    
    private Kunderegister register;
   
    public HovedVindu(Kunderegister register)
    {
        super("Insurance-Browse!");
        setLayout(new BorderLayout());
        
        this.register = register;
        
        setGrensesnitt();
        underpaneler = new JPanel(new CardLayout());
        underpaneler.add(hovedpanel, HovedVindu);
        hovedpanel.setBackground(Color.decode("#E57E7E"));
        
        add(underpaneler, BorderLayout.CENTER);
        
        setVisible(true);
        pack();
        
    }
    
    
    //setter grensesnittet. Panelene, knappene osv.
    public void setGrensesnitt()
    {
        hovedpanel = new JPanel(new GridLayout(2,4,5,5));
        
        regKundeIcon        = new ImageIcon(getClass().getResource("Bilder/regKunde.png"));
        visKundeIcon        = new ImageIcon(getClass().getResource("Bilder/visKunde.png"));
        regForsikringIcon   = new ImageIcon(getClass().getResource("Bilder/regForsikring.png"));
        regSkadeIcon        = new ImageIcon(getClass().getResource("Bilder/regSkademelding.png"));
        finanserIcon        = new ImageIcon(getClass().getResource("Bilder/Finanser.png"));
        historikkIcon       = new ImageIcon(getClass().getResource("Bilder/Historikk.png"));
        statistikkIcon      = new ImageIcon(getClass().getResource("Bilder/Statistikk.png"));
        søkIcon             = new ImageIcon(getClass().getResource("Bilder/Søk.png"));
        
        regKunde      = new JButton(regKundeIcon);
        visKunde      = new JButton(visKundeIcon);
        regForsikring = new JButton(regForsikringIcon);
        regSkade      = new JButton(regSkadeIcon);
        finanser      = new JButton(finanserIcon);
        historikk     = new JButton(historikkIcon);
        statistikk    = new JButton(statistikkIcon);
        søk           = new JButton(søkIcon);
        
        hovedpanel.add(regKunde);
        hovedpanel.add(visKunde);
        hovedpanel.add(regForsikring);
        hovedpanel.add(regSkade);
        hovedpanel.add(finanser);
        hovedpanel.add(historikk);
        hovedpanel.add(statistikk);
        hovedpanel.add(søk);
        
        regKunde.addActionListener(this);
        
        Size();
        
    }
       
      //Metode for å vise panel med innkommen parameter
    public void visPanel(String st) 
    {
        CardLayout cl = (CardLayout) underpaneler.getLayout();
        cl.show(underpaneler, st);
    }

       public void Size() 
       {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        setSize(bredde /2, høyde - 500);
        setLocation(skjerm.width / 2 - getSize().width / 2, skjerm.height / 2 - getSize().height / 2);
       
       }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    
        if (e.getSource() == regKunde)
        {
            underpaneler.add(new registrerKundePanel(register, this), "REG KUNDE");
            visPanel("REG KUNDE");
        }
    }
    
    
    
    
}