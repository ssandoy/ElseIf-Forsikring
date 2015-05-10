/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;


/**
 *
 * @author ssandoy
 */
public class HovedVindu extends JFrame implements ActionListener  
{
    
    private JPanel hovedpanel;
    
    private final JPanel underpaneler;
    
    public static JPanel logopanel;
    
    private JButton regKunde, visKunde, regForsikring, regSkade, finanser, historikk, slettKunde
            ,slettForsikring;
    
    private JButton BilForsikring, BaatForsikring, BoligForsikring, HytteForsikring, ReiseForsikring;
    
    private Icon LOGO, regKundeIcon, visKundeIcon, regForsikringIcon, regSkadeIcon, finanserIcon,
            historikkIcon, slettKundeIcon, slettForsikringIcon;
    
    private JLabel logo;
    
    public static final String HovedVindu = "HovedVindu";
    
    private Kunderegister kregister;
    private Forsikringsregister fregister;
    private Skademeldingsregister sregister;
    private Finanser finans;
    private Historikk historikker;
   
    public HovedVindu(Kunderegister kregister, Forsikringsregister fregister, Skademeldingsregister sregister)
    {
        super("Else-if Forsikring");
        setLayout(new BorderLayout());
        
        this.kregister = kregister;
        this.fregister = fregister;
        this.sregister = sregister;
        this.finans = new Finanser(sregister, fregister);
        this.historikker = new Historikk(fregister, kregister, sregister);
        
        Toolkit verktoykasse = Toolkit.getDefaultToolkit();
        //Bildefil for ikon er plassert i underkatalogen bilder:
        String bildefil = "Bilder/icon.png";
        URL kilde = HovedVindu.class.getResource(bildefil);
        if (kilde != null)
        {
            ImageIcon bilde = new ImageIcon(kilde);
            Image ikon = bilde.getImage();
            setIconImage(ikon);
        }
        
        setGrensesnitt();
        underpaneler = new JPanel(new CardLayout());
        underpaneler.add(hovedpanel, HovedVindu);
        hovedpanel.setBackground(Color.decode("#5E5E5E"));
        
        
        add(logopanel, BorderLayout.PAGE_START);
        add(underpaneler,BorderLayout.CENTER);
        
        
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
        slettKundeIcon      = new ImageIcon(getClass().getResource("Bilder/slettKunde.png"));
        slettForsikringIcon = new ImageIcon(getClass().getResource("Bilder/slettForsikring.png"));
        
        regKunde          = new JButton(regKundeIcon);
        visKunde          = new JButton(visKundeIcon);
        regForsikring     = new JButton(regForsikringIcon);
        regSkade          = new JButton(regSkadeIcon);
        finanser          = new JButton(finanserIcon);
        historikk         = new JButton(historikkIcon);
        slettKunde        = new JButton(slettKundeIcon);
        slettForsikring   = new JButton(slettForsikringIcon);
        logo              = new JLabel(LOGO);
        
        } catch(NullPointerException npe)
        {
            regKunde         = new JButton("Registrer kunde");
            visKunde         = new JButton("Vis kunde");
            regForsikring    = new JButton("Registrer forsikring");
            regSkade         = new JButton("Registrer skademelding");
            finanser         = new JButton("Finanser");
            historikk        = new JButton("Historikk");
            slettKunde       = new JButton("Slett kunde");
            slettForsikring  = new JButton("Slett forsikring");
            logo             = new JLabel("LOGO");
        }
        BilForsikring   = new JButton();
        BaatForsikring  = new JButton();
        BoligForsikring = new JButton();
        HytteForsikring = new JButton();
        ReiseForsikring = new JButton();
        
        
        logopanel.add(logo);
        hovedpanel.add(regKunde);
        hovedpanel.add(visKunde);
        hovedpanel.add(regForsikring);
        hovedpanel.add(regSkade);
        hovedpanel.add(slettKunde);
        hovedpanel.add(slettForsikring);
        hovedpanel.add(finanser);
        hovedpanel.add(historikk);
        
        
        regKunde.addActionListener(this);
        visKunde.addActionListener(this);
        regForsikring.addActionListener(this);
        regSkade.addActionListener(this);
        finanser.addActionListener(this);
        slettKunde.addActionListener(this);
        slettForsikring.addActionListener(this);
        historikk.addActionListener(this);
        
        BilForsikring.addActionListener(this);
        BaatForsikring.addActionListener(this);
        BoligForsikring.addActionListener(this);
        HytteForsikring.addActionListener(this);
        ReiseForsikring.addActionListener(this);
        
        
        logopanel.setBackground(Color.decode("#5E5E5E"));
        
        
        
    }
       
      //Metode for å vise panel med innkommen parameter. Utføres ved klikk på JButton
    public void visPanel(String st) 
    {
        CardLayout cl = (CardLayout) underpaneler.getLayout();
        cl.show(underpaneler, st);
    }

    //metode for underpanelene som endrer paneler ut i fra knappetrykk. 
    //Brukes i regForsikring-panelene
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
                break;
            case 4:
                HytteForsikring.doClick();
                break;
            case 5:
                ReiseForsikring.doClick();
                break;
        }

    }
    
       public void Size() 
       {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension skjerm = kit.getScreenSize();
        int bredde = skjerm.width;
        int hoyde = skjerm.height;

        setSize((bredde /2)+15, hoyde - 250);
        setLocation(skjerm.width / 2 - getSize().width / 2, skjerm.height / 2 - getSize().height / 2);
       }
       
       public void fjernLogo(){
           this.remove(logopanel);
           revalidate();
           repaint();
       }
       public void addLogo(){
           add(logopanel, BorderLayout.PAGE_START);
       }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    
        if (e.getSource() == regKunde)
        {
            underpaneler.add(new RegistrerKundePanel(this, kregister), "REG KUNDE");
            visPanel("REG KUNDE");
            fjernLogo();
            
        }
        else if(e.getSource() == visKunde)
        {
            underpaneler.add(new VisKundePanel(this, kregister), "VIS KUNDE");
            visPanel("VIS KUNDE");
            fjernLogo();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        else if(e.getSource() == regForsikring)
        {
            underpaneler.add(new RegistrerForsikringPanel(this), "REG FORSIKRING");
            visPanel("REG FORSIKRING");
            fjernLogo();
        }
         else if(e.getSource() == finanser)
         {
             underpaneler.add(new FinanserPanel(finans, this),"FINANSER");
             visPanel("FINANSER");
             fjernLogo();
         }
        else if(e.getSource() == regSkade)
        {
            underpaneler.add(new RegistrerSkademeldingPanel(this, kregister, fregister, sregister),"REG SKADEMELDING");
            visPanel("REG SKADEMELDING");
            fjernLogo();
        }
        else if(e.getSource() == slettKunde)
        {
            underpaneler.add(new SlettKundePanel(this, kregister), "SLETT KUNDE");
            visPanel("SLETT KUNDE");
            fjernLogo();
        }
        else if(e.getSource() == slettForsikring)
        {
            underpaneler.add(new SlettForsikringerPanel(this, kregister, fregister), "SLETT FORSIKRING");
            visPanel("SLETT FORSIKRING");
            fjernLogo();
        }
          else if(e.getSource() == BilForsikring)
        {
            underpaneler.add(new RegistrerBilForsikringPanel(this,kregister, fregister), "BilForsikring");
            visPanel("BilForsikring");
            fjernLogo();
            
        }
        else if(e.getSource() == BaatForsikring)
        {
            underpaneler.add(new RegistrerBaatForsikringPanel(this,kregister, fregister ), "BåtForsikring");
            visPanel("BåtForsikring");
        }
        else if(e.getSource() == BoligForsikring)
        {
            underpaneler.add(new RegistrerBoligForsikringPanel(this, kregister ,fregister), "BoligForsikring");
            visPanel("BoligForsikring");
        }
        else if(e.getSource() == HytteForsikring)
        {
            underpaneler.add(new RegistrerHytteForsikringPanel(this,kregister, fregister), "HytteForsikring");
            visPanel("HytteForsikring");
        }
        else if(e.getSource() == ReiseForsikring)
        {
            underpaneler.add(new RegistrerReiseForsikringPanel(this, kregister, fregister), "ReiseForsikring");
            visPanel("ReiseForsikring");
        }
        else if(e.getSource() == historikk)
        {
            underpaneler.add(new HistorikkPanel(this, historikker), "HISTORIKK");
            fjernLogo();
            visPanel("HISTORIKK");
        }
  }
}

