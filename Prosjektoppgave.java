/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ssandoy
 */
public class Prosjektoppgave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       /* Kunderegister kregister = new Kunderegister();
        //String nr = 050591 +"";
        String nr = JOptionPane.showInputDialog("Skriv inn: ");
        Forsikringskunde k = new Forsikringskunde(nr,"Mads","Karlstad","Haugen","Haugen");
        kregister.leggTil(k);
        System.out.println(kregister.toString());
     
        
        
        Forsikringsregister register = new Forsikringsregister();
       
            Bilforsikring bil = new Bilforsikring(k, "Mads","12","Mazda",1993,1000,4);
            register.leggTil(bil);
          
        System.out.println(register.toString());
        System.out.println(k.getForsikringer());
        //<fnr+"",Obj>
        
        //bil.beregnPremie();
        //bil.beBiPremie();regnBiPremie();
        
        //System.out.println(bil.getPremie());
      
        //Bilforsikring bil1 = new Bilforsikring("Mads","122","Mazda",1995,8000,0);
        //register.leggTil(bil1);
        //bil1.beregnPremie();
        //bil1.beregnBiPremie();
        //System.out.println(register.toString());
        //System.out.println(bil1.getPremie());*/
        
       HovedVindu vindu = new HovedVindu();
      
        vindu.addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
    }
    
}
