/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ssandoy
 */

public class VisKundePanel extends JPanel implements ActionListener
{
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel sokepanel;
    private JPanel knapppanel;

    

    private JLabel overskrift;
    private JTextField sokfelt;
    private JTable kundetabell;
    private JScrollPane scroll;
    
    private final String[] soyler = {"Personnummer"};
    
    
   private Kunderegister kregister;
    
   private HovedVindu forelder;
   
    public VisKundePanel(Kunderegister kregister, HovedVindu forelder)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        this.forelder = forelder;
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       
    }
}
