package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
    private JPanel overskriftpanel;
    private JPanel finanspanel;
    private JPanel knappepanel;
    
    private JLabel overskrift;
    
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
        infofelt = new JTextArea(15, 15);
        infofelt.setEditable(false);
        
        Font font = new Font("Verdana", Font.BOLD, 30);
        infofelt.setFont(font);
        infofelt.setForeground(Color.decode("#151514"));
        infofelt.setBackground(Color.decode("#DBDBDB"));
        
        beregn.addActionListener(this);
        this.forelder = forelder;
        
        overskrift = new JLabel("FINANSER");
        overskrift.setFont(font);
        
        setLayout(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        overskriftpanel.add(overskrift);
        finanspanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //Endre dette
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        finanspanel.add(infofelt);
        
        knappepanel.add(avbryt);
        knappepanel.add(beregn);

        add(overskriftpanel, BorderLayout.PAGE_START);
        add(finanspanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);
        
        finanspanel.setBackground(Color.decode("#5E5E5E"));
        knappepanel.setBackground(Color.decode("#5E5E5E"));
        
        Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int hoyde = skjerm.height;
         
         forelder.setSize(bredde/2, hoyde-500);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
    }
    
    public void Beregn() //Beregner finansene til selskapet
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