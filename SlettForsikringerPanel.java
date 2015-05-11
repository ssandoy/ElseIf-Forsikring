/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ssandoy, YAAKOUBD
 */

public class SlettForsikringerPanel extends JPanel implements ActionListener
{
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel sokepanel;
    private JPanel knappepanel;

    private JButton avbryt, sokKunde, slettForsikring;

    private JLabel overskrift;
    private JTextField sokfelt;
    private JTextArea tekstinfo;
    private JTable forsikringstabell;
    private ForsikringsRamme forsikringsramme;
    private JScrollPane scroll;
    
    
   private final String[] soyler = {"Forsikringsnummer", "Forsikringstype", "Forsikringspremie", "Startdato"};
        
   private Kunderegister kregister; 
   private Forsikringsregister fregister;
   private LinkedList<Insurance> forsikringsliste;
   private LinkedList<Forsikringskunde> kundeliste;
 
   
   private HovedVindu forelder;
   
    public SlettForsikringerPanel( HovedVindu forelder, Kunderegister kregister, Forsikringsregister fregister)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        this.fregister = fregister;
        this.forelder = forelder;
        
         sokepanel = new JPanel(new FlowLayout());
         sokfelt   = new JTextField(20);
         sokKunde = new JButton("Søk etter kunde");
         sokKunde.addActionListener(this);
         sokepanel.add(sokfelt);
         sokepanel.add(sokKunde);
         
         
         forsikringsliste = new LinkedList<Insurance>();
        
         
         forsikringsramme = new ForsikringsRamme(soyler, forsikringsliste);
         forsikringstabell = new JTable(forsikringsramme);
          forsikringstabell.addMouseListener(new MusLytter());
         scroll = new JScrollPane(forsikringstabell);

         tabellpanel = new JPanel(new BorderLayout());
         tabellpanel.add(scroll, BorderLayout.CENTER);
         
         avbryt = new JButton("Avbryt");
         avbryt.addActionListener(this);
         slettForsikring = new JButton("SLETT FORSIKRING");
         slettForsikring.addActionListener(this);
         knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   
         knappepanel.add(avbryt);
         knappepanel.add(slettForsikring);
 
         this.add(sokepanel, BorderLayout.NORTH);
         this.add(tabellpanel, BorderLayout.CENTER);
         this.add(knappepanel, BorderLayout.PAGE_END);
         
         
    }

     public void sok()
     {
         scroll.remove(forsikringstabell);
         tabellpanel.remove(scroll);
         this.remove(tabellpanel);
         revalidate();
         repaint();

         
        String sok = sokfelt.getText();
      
        Forsikringskunde f = kregister.getKunde(sok);
        if(f == null)
        {
            visFeilMelding("Ingen kunde med det personnummeret!");
        }
        else
        {
        LinkedList<Insurance> kundeforsikringer = f.getForsikringer();
        
        if(kundeforsikringer.isEmpty())
        {
            tabellpanel = new JPanel(new BorderLayout());
            tekstinfo   = new JTextArea(10,10);
            tekstinfo.setText("Kunden eier ingen forsikringer. Enda!");
            tabellpanel.add(tekstinfo, BorderLayout.CENTER);
            this.add(tabellpanel,BorderLayout.CENTER);
        }
        else
        {  
        forsikringsramme = new ForsikringsRamme(soyler, kundeforsikringer);

        forsikringstabell = new JTable(forsikringsramme);
        forsikringstabell.addMouseListener(new MusLytter());
        forsikringstabell.setAutoCreateRowSorter(true);

        scroll = new JScrollPane(forsikringstabell);

        tabellpanel = new JPanel(new BorderLayout());
        tabellpanel.add(scroll, BorderLayout.CENTER);

        this.add(tabellpanel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
        }
     }
    
     
    
    public void visMelding(String melding) {
        JOptionPane.showMessageDialog(null, melding);
    }

     public void visFeilMelding(String melding)
     {
       JOptionPane.showMessageDialog(this, melding, "Problem", 
               JOptionPane.ERROR_MESSAGE);
     }
     
     
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       if(e.getSource() == avbryt)
       {
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.addLogo();
           forelder.Size();  
       } else if(e.getSource() == slettForsikring)
       {
           try{
                Insurance f = (Insurance) forsikringsramme.getData().get(forsikringstabell.getSelectedRow());
                Forsikringskunde k = f.getKunde();
                if(fregister.fjern(f.getFNummer()))
                {
                     k.fjernForsikring(f);
                     visMelding("Forsikring fjernet!");
                     f = null;
                }
           }catch(IndexOutOfBoundsException i)
           {
               visFeilMelding("Du har ikke valgt noen forsikring");
           }
       }
       else if(e.getSource() == sokKunde)
       {
           sok();
       }
    }
    
     private class TekstLytter implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            sok();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            sok();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            sok();
        }

    }//Tekstlytter ferdig
     
      
      
     
      private class MusLytter implements MouseListener {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent me) {
            if (me.getClickCount() == 2) 
            {
                Insurance f = (Insurance) forsikringsramme.getData().get(forsikringstabell.getSelectedRow());
                Forsikringskunde k = f.getKunde();
                if(fregister.fjern(f.getFNummer()))
                {
                    k.fjernForsikring(f);
                     visMelding("Forsikring fjernet!");
                     f = null;
                }
               
                
                        
                        
            }

        }//mouseClicked ferdig

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            
        }//mousePressed ferdig

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
          
        }//mouseReleased ferdig

        @Override
        public void mouseEntered(java.awt.event.MouseEvent me) {
        }//mouseEntered ferdig

        @Override
        public void mouseExited(java.awt.event.MouseEvent me) {
        }//mouseExited ferdig

        
    }//Muslytter ferdig

}