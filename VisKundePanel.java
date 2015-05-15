/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Panel som viser alle kunder i en tabell og hvor man kan søke opp alle kundene 
 * og viser infromasjon om dem ved å trykke på en rad
 * @author Sander
 */

public class VisKundePanel extends JPanel implements ActionListener
{
    private JPanel overskriftpanel;
    private JPanel tabellpanel;
    private JPanel sokepanel;
    private JPanel knappepanel;

    private JButton tilbake, visSkademeldinger, visFinanser;

    private JLabel overskrift;
    private JTextField sokfelt;
    private JTable kundetabell;
    private KundeRamme kunderamme;
    private JScrollPane scroll;
    
    
    private final String[] soyler = {"Personnummer","Fornavn","Etternavn","Adresse" ,"Telefonnummer", "Kundenummer", "TOTALKUNDE"};
        
   private Kunderegister kregister; 
   private LinkedList<Forsikringskunde> kundeliste;
 
   
   private HovedVindu forelder;
   
    public VisKundePanel( HovedVindu forelder, Kunderegister kregister)
    {
        super(new BorderLayout());
        this.kregister = kregister;
        this.forelder = forelder;
        
         sokepanel = new JPanel(new FlowLayout());
         sokfelt   = new JTextField(20);
         sokfelt.getDocument().addDocumentListener(new TekstLytter());
         sokepanel.add(sokfelt);
         
         
         kundeliste = new LinkedList<Forsikringskunde>();
         
         for(Map.Entry<String, Forsikringskunde> entry : kregister.entrySet())
         {
             kundeliste.add((Forsikringskunde) entry.getValue());
         }
         
         kunderamme = new KundeRamme(soyler, kundeliste);
         kundetabell = new JTable(kunderamme);
         kundetabell.addMouseListener(new MusLytter());
         kundetabell.setAutoCreateRowSorter(true);
         scroll = new JScrollPane(kundetabell);

         tabellpanel = new JPanel(new BorderLayout());
         tabellpanel.add(scroll, BorderLayout.CENTER);
         
         tilbake = new JButton("Tilbake");
         tilbake.addActionListener(this);
         visSkademeldinger = new JButton("Vis skademeldinger");
         visSkademeldinger.addActionListener(this);
         visFinanser = new JButton("Vis finanser");
         visFinanser.addActionListener(this);
         knappepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   
         knappepanel.add(tilbake);
         knappepanel.add(visSkademeldinger);
         knappepanel.add(visFinanser);
 
         this.add(sokepanel, BorderLayout.NORTH);
         this.add(tabellpanel, BorderLayout.CENTER);
         this.add(knappepanel, BorderLayout.PAGE_END);
         
         Toolkit kit = Toolkit.getDefaultToolkit();
         Dimension skjerm = kit.getScreenSize();
         int bredde = skjerm.width;
         int høyde = skjerm.height;
         
    }

     public void sok()
     {
         scroll.remove(kundetabell);
         tabellpanel.remove(scroll);
         this.remove(tabellpanel);
         revalidate();
         repaint();

         
        String sok = sokfelt.getText();
      
       
        LinkedList<Forsikringskunde> sokliste = sokListe(sok);

        kunderamme = new KundeRamme(soyler, sokliste);

        kundetabell = new JTable(kunderamme);
        kundetabell.addMouseListener(new MusLytter());
        kundetabell.setAutoCreateRowSorter(true);


        scroll = new JScrollPane(kundetabell);

        tabellpanel = new JPanel(new BorderLayout());
        tabellpanel.add(scroll, BorderLayout.CENTER);

        this.add(tabellpanel, BorderLayout.CENTER);
        

        revalidate();
        repaint();
        
     }
    
     
    public LinkedList<Forsikringskunde> sokListe(String sok)
    {
        LinkedList<Forsikringskunde> sokliste = new LinkedList<>();
            sok = sok.toUpperCase();
        if(kregister.isEmpty()){
            return sokliste;
        }else{
            for(Forsikringskunde p : kundeliste){

                if(p.getPersonNr().toUpperCase().startsWith(sok) || p.getFornavn().toUpperCase().startsWith(sok)
                        || p.getEtternavn().toUpperCase().startsWith(sok) || p.getAdresse().toUpperCase().startsWith(sok)
                        || p.getKundenummer().toUpperCase().startsWith(sok))
                {
                    sokliste.add(p);
                }
            }
        }
        return sokliste;
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
       if(e.getSource() == tilbake)
       {
           forelder.addLogo();
           forelder.visPanel(HovedVindu.HovedVindu);
           forelder.Size();                     
       }
       else if(e.getSource() == visSkademeldinger)
       {
           try{
           Forsikringskunde k = (Forsikringskunde) kunderamme.getData().get(kundetabell.getSelectedRow());
           String utskrift = "";
           if(k.getSkademeldinger().isEmpty())
           {
               visMelding("Kunde: " + k.getFornavn() + " " + k.getEtternavn()
                         + "\nHar ikke skrevet noen skademeldinger");
               return;
           }
           Iterator it = k.getSkademeldinger().iterator();
           while(it.hasNext())
           {
               utskrift += it.next().toString() + "\n";
           }
           visMelding("Kunde: " + k.getFornavn() + " " + k.getEtternavn() + 
                      "\nSkademeldinger: " + utskrift);
           }catch(IndexOutOfBoundsException i)
           {
               visFeilMelding("Ingen kunde markert");
           }
       }
       else if(e.getSource() == visFinanser)
       {
           try{
           Forsikringskunde k = (Forsikringskunde) kunderamme.getData().get(kundetabell.getSelectedRow());
           k.beregnTotalPremie();
           String utskrift = "";
           if(k.getForsikringer().isEmpty())
               {
               visMelding("Kunde: " + k.getFornavn() + " " + k.getEtternavn()
                         + "\nHar ikke skrevet noen forsikringer");
               return;
               }
           visMelding("Kunde: " + k.getFornavn() + " " + k.getEtternavn() + 
                     "\nInnbetalt årlig premie: " + k.beregnTotalPremie() + ",-"
                    + "\nUtbetalt i erstatninger: " + k.beregnTotalErstatning()); //FIKS DENNE GETERSTATNING
           } catch(IndexOutOfBoundsException i)
           {
               visFeilMelding("Ingen kunde markert");
           }
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
                Forsikringskunde k = (Forsikringskunde) kunderamme.getData().get(kundetabell.getSelectedRow());
                visMelding("Kunde: " + k.getFornavn() + " " + k.getEtternavn() + 
                           "\nAntall forsikringer: " + k.getForsikringer().size() + 
                            "\nTotalsum årlig premie: " + k.getPremie());
      
            }

        }//mouseClicked ferdig

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            
        }//mousePressed ferdig

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
          
        }//mouseReleased ferdig

        @Override
        public void mouseEntered(java.awt.event.MouseEvent me) 
        {
        }//mouseEntered ferdig

        @Override
        public void mouseExited(java.awt.event.MouseEvent me) 
        {
        }//mouseExited ferdig

        
    }//Muslytter ferdig

}
