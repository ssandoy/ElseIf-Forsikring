package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author YAAKOUBD
 */
public class FinanserPanel extends JPanel implements ActionListener{
    
    private JTextArea infofelt;
    private JButton beregn, avbryt;
    private JPanel finanspanel;
    private JPanel knappepanel;
    
    private Finanser finanser;
    
    private HovedVindu forelder;
    
    public FinanserPanel( Finanser finanser, HovedVindu forelder)
    {
        super(new BorderLayout());
        this.finanser = finanser;
        beregn = new JButton("Beregn");
        avbryt = new JButton("Avbryt");
        beregn.addActionListener(this);
        avbryt.addActionListener(this);
        infofelt = new JTextArea(60, 60);
        infofelt.setEditable(false);
        
        beregn.addActionListener(this);
        
        this.forelder = forelder;
        
        setLayout(new BorderLayout());
        finanspanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        finanspanel.add(infofelt);
        knappepanel.add(beregn);
        knappepanel.add(avbryt);
        
        add(finanspanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);
        
        finanspanel.setBackground(Color.decode("#E57E7E"));
        knappepanel.setBackground(Color.decode("#E57E7E"));
        
        Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int høyde = skjerm.height;
         
         forelder.setSize(bredde/2, høyde-500);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
    }
    
    public void Beregn()
    {
        finanser.beregnInnbetalt();
        finanser.beregnUtbetalt();
        finanser.beregnDifferanse();
        infofelt.setText( finanser.toString() );
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == beregn)
        {
            Beregn();
        }
        else if(e.getSource() == avbryt)
        {
            forelder.visPanel(HovedVindu.HovedVindu);
            forelder.Size();
            forelder.addLogo();
        }
    }
}