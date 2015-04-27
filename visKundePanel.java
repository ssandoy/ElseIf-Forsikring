/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author ssandoy
 */

public class visKundePanel extends JPanel implements ActionListener
{

   private Kunderegister kregister;
    
   private HovedVindu forelder;
   
    public visKundePanel(Kunderegister kregister, HovedVindu forelder)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        this.forelder = forelder;
        
    }
}
