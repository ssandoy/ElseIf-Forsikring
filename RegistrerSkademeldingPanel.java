/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ssandoy
 */
public class RegistrerSkademeldingPanel extends JPanel implements ActionListener
{ 
    
    private JPanel tekstpanel;
    private JPanel knappepanel;
    private JPanel overskriftpanel;
    private JPanel toppanel;
    private JPanel midtpanel;
   

    private JButton registrer;
    private JButton avbryt;
    
    HovedVindu forelder;
    Kunderegister kregister;
    Forsikringsregister fregister;
    Skademeldingsregister sregister;

    
    public RegistrerSkademeldingPanel(HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister, Skademeldingsregister sregister) 
    {
        super(new BorderLayout());
        this.forelder = forelder;
        this.kregister = kregister;
        this.fregister = fregister;
        this.sregister = sregister;

        setGrensesnitt();
        
    }

    
    public void setGrensesnitt()
    {
        registrer = new JButton("Registrer skademelding");
        registrer.addActionListener(this);
        avbryt = new JButton("Avbryt");
        avbryt.addActionListener(this);
        
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
