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
    
    private JPanel underpaneler, logopanel;
    
    private JButton regKunde, visKunde, regForsikring, regSkade, finanser, historikk, statistikk
            ,søk;
    
    private JButton BilForsikring, BaatForsikring, BoligForsikring;
    
    private Icon LOGO, regKundeIcon, visKundeIcon, regForsikringIcon, regSkadeIcon, finanserIcon,
            historikkIcon, statistikkIcon, søkIcon;
    
    private JLabel logo;
    
    public static final String HovedVindu = "HovedVindu";
    
    private Kunderegister kregister;
    private Forsikringsregister fregister;
   
    public HovedVindu(Kunderegister kregister, Forsikringsregister fregister)
    {
        super("Else-if Forsikring");
        setLayout(new BorderLayout());
        
        this.kregister = kregister;
        this.fregister = fregister;
        
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
        
        logopanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hovedpanel = new JPanel(new GridLayout(2,4,5,5));
        try {
            
        LOGO                = new ImageIcon(getClass().getResource("Bilder/logo.png"));
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
        logo          = new JLabel(LOGO);
        
        } catch(NullPointerException npe)
        {
            regKunde      = new JButton("Registrer kunde");
            visKunde      = new JButton("Vis kunde");
            regForsikring = new JButton("Registrer forsikring");
            regSkade      = new JButton("Registrer skademelding");
            finanser      = new JButton("Finanser");
            historikk     = new JButton("Historikk");
            statistikk    = new JButton("Statistikk");
            søk           = new JButton("Søk");
            logo          = new JLabel("LOGO");
        }
        BilForsikring = new JButton();
        BaatForsikring = new JButton();
        BoligForsikring = new JButton();
        
        
        
        hovedpanel.add(regKunde);
        hovedpanel.add(visKunde);
        hovedpanel.add(regForsikring);
        hovedpanel.add(regSkade);
        hovedpanel.add(finanser);
        hovedpanel.add(historikk);
        hovedpanel.add(statistikk);
        hovedpanel.add(søk);
        
        regKunde.addActionListener(this);
        regForsikring.addActionListener(this);
        
        BilForsikring.addActionListener(this);
        BaatForsikring.addActionListener(this);
        BoligForsikring.addActionListener(this);
        
        Size();
        
    }
       
      //Metode for å vise panel med innkommen parameter
    public void visPanel(String st) 
    {
        CardLayout cl = (CardLayout) underpaneler.getLayout();
        cl.show(underpaneler, st);
    }

    //metode for underpanelene som toggler paneler ut i fra knappetrykk
     public void doClick(int i)
     {

        switch (i)
        {
            case 1:
                BilForsikring.doClick();
                break;
            case 2:
                BaatForsikring.doClick();
                break;
            case 3:
                BoligForsikring.doClick();
        }

    }
    
       public void Size() 
       {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int høyde = skjerm.height;

        setSize(bredde /2, høyde - 430);
        setLocation(skjerm.width / 2 - getSize().width / 2, skjerm.height / 2 - getSize().height / 2);
       
       }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    
        if (e.getSource() == regKunde)
        {
            underpaneler.add(new registrerKundePanel(kregister, this), "REG KUNDE");
            visPanel("REG KUNDE");
        }
        else if(e.getSource() == regForsikring)
        {
            underpaneler.add(new registrerForsikringPanel(this), "REG FORSIKRING");
            visPanel("REG FORSIKRING");
        
        }
          else if(e.getSource() == BilForsikring)
        {
            underpaneler.add(new registrerBilForsikringPanel(this, fregister), "BilForsikring");
            visPanel("BilForsikring");
        }
        else if(e.getSource() == BaatForsikring)
        {
            underpaneler.add(new registrerBaatForsikringPanel(this, fregister), "BåtForsikring");
            visPanel("BåtForsikring");
        }
        else if(e.getSource() == BoligForsikring)
        {
            underpaneler.add(new registrerBoligForsikringPanel(this,fregister), "BoligForsikring");
            visPanel("BoligForsikring");
        }
    }
}

