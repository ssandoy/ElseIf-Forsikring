package prosjektoppgave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.Border;


/**
 * Panel som viser all Statistikk/statistikk informasjon i et JTextArea
 * @author Amir
 */
public class StatistikkPanel extends JPanel implements ActionListener
{
    private JTextArea infofelt;

    private  Statistikk statistikk;
    
    private JButton tilbake;
    
    private JLabel overskrift;
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    //private JPanel midtpanel;
    
    private HovedVindu forelder;
    
    public StatistikkPanel(HovedVindu forelder, Statistikk statistikk)
    {
        super (new BorderLayout());
        this.statistikk = statistikk;
        this.forelder = forelder;
        
        
        tekstpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        statistikk.tell(); //Metode som teller opp alle typer kunder, forsikringer, og skademelding
        
        infofelt = new JTextArea (30, 20);
        infofelt.setText(statistikk.skrivStatistikk());
        Font font = new Font("Verdana", Font.BOLD, 30);
        infofelt.setFont(font);
        infofelt.setForeground(Color.decode("#5E5E5E"));
        infofelt.setBackground(Color.decode("#DBDBDB"));
        infofelt.setEditable(false);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        infofelt.setBorder(BorderFactory.createCompoundBorder(border, 
        BorderFactory.createEmptyBorder(40, 70, 40, 40)));
        
        overskrift = new JLabel("STATISTIKK");
        
        tilbake = new JButton("Tilbake");
        tilbake.addActionListener(this);
        
        setLayout(new BorderLayout());
        tekstpanel = new JPanel(new GridLayout(1, 2, 0, 0));
        knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toppanel = new JPanel(new BorderLayout());
        overskriftpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        overskrift.setForeground(Color.WHITE);
        overskrift.setFont(font);
        overskriftpanel.add(overskrift);
        
        
        tekstpanel.add(infofelt);
        
        add(tekstpanel, BorderLayout.CENTER);
        
        knappepanel.add(tilbake);

        toppanel.add(overskriftpanel, BorderLayout.PAGE_START);

        add(toppanel, BorderLayout.PAGE_START);
        add(tekstpanel, BorderLayout.CENTER);
        add(knappepanel, BorderLayout.PAGE_END);

        tekstpanel.setBackground(Color.decode("#5E5E5E"));
        toppanel.setBackground(Color.decode("#5E5E5E"));
        knappepanel.setBackground(Color.decode("#669CFF"));
        overskriftpanel.setBackground(Color.decode("#669CFF"));
                
        Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int hoyde = skjerm.height;
         
         forelder.setSize(bredde*3/4, hoyde*3/5);
         forelder.setLocation(skjerm.width/2-forelder.getSize().width/2, skjerm.height/2-forelder.getSize().height/2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == tilbake) {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();

        }  
    }
}//slutt på klasse