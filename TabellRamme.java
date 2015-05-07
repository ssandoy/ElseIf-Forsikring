/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prosjektoppgave;

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ssandoy
 */

public abstract class TabellRamme<T> extends AbstractTableModel {

    private final String[] kolonnenavn;
    private final LinkedList<T> data;

    public TabellRamme(String[] kolonnenavn, LinkedList<T> data) {
        this.kolonnenavn = kolonnenavn;
        this.data = data;
    }
    //get-metoder for tabeller
    public String getColumnName(int kol) {
        return kolonnenavn[kol];
    }

    public int getRowCount() {
        return data.size();
    }

    public T getRow(int rad) {
        return data.get(rad);
    }

    public int getColumnCount() {
        return kolonnenavn.length;
    }

    public LinkedList<T> getData() {
        return data;
    }

    @Override
    public abstract Object getValueAt(int rad, int kol);

    /**
     * Setter inn et objekt i en rad i tabellen og oppdaterer modellen.
     *
     * @param obj Objektetsom skal settes inn.
     */
    public void addRow(T obj) {
        data.add(obj);
        this.fireTableDataChanged();
    }

    /**
     * Fjerner en rad fra tabellen og oppdaterer modellen.
     *
     * @param rad Indeksen til den raden som skal fjernes.
     */
    public void delRow(int rad) {
        data.remove(rad);
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data og oppdaterer modellen.
     */
    public void delTabledata() {
        data.clear();
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data, setter inn ny og oppdaterer modellen.
     *
     * @param d Den nye listen med data.
     */
    public void setTabledata(LinkedList<T> d) {
        data.clear();
        data.addAll(d);
        this.fireTableDataChanged();
    }

}//tabellModell klasse feridg

//Ramme for forsikringskundene. Arver fra Tabellramme
class KundeRamme extends TabellRamme<Forsikringskunde> {

    private final int PERSONNUMMER = 0;
    private final int  FORNAVN     = 1;
    private final int ETTERNAVN    = 2;
    private final int ADRESSE      = 3;
    private final int FAKTURA      = 4;
    

    public KundeRamme(String[] kolonnenavn, LinkedList<Forsikringskunde> data) {
        super(kolonnenavn, data);
    }
    
    //Setter inn informasjon om kunden til sin respektive søyle
    public Object getValueAt(int rad, int kol)
    {
        
        Forsikringskunde kunde = (Forsikringskunde) super.getData().get(rad);

        switch (kol) {
            case PERSONNUMMER:
                return kunde.getPersonNr();
            case FORNAVN:
                return kunde.getFornavn();
            case ETTERNAVN:
                return kunde.getEtternavn();
            case ADRESSE:
                return kunde.getAdresse();
            case FAKTURA:
                return kunde.getAdresse();
            default:
                return null;
        }

    }
    //Metode som returnerer valgt kunde i tabellen
    public Forsikringskunde getValueAt(int rad) {
        return (Forsikringskunde) super.getData().get(rad);

    }
}

//Ramme for forsikringer
//Ramme for forsikringskundene. Arver fra Tabellramme
class ForsikringsRamme extends TabellRamme<Insurance> {

    private final int FORSIKRINGSNUMMER    = 0;
    private final int FORSIKRINGSTYPE      = 1;
    private final int FORSIKRINGSPREMIE    = 2;
    private final int STARTDATO            = 3;
    

    public ForsikringsRamme(String[] kolonnenavn, LinkedList<Insurance> data) {
        super(kolonnenavn, data);
    }
    
    //Setter inn informasjon om forsikringen til sin respektive søyle
    public Object getValueAt(int rad, int kol)
    {
        
        Insurance forsikring = (Insurance) super.getData().get(rad);

        switch (kol) {
            case FORSIKRINGSNUMMER:
                return forsikring.getFNummer();
            case FORSIKRINGSTYPE:
                return forsikring.getType();
            case FORSIKRINGSPREMIE:
                return forsikring.getPremie();
            case STARTDATO:
                return forsikring.getStartDato();
            default:
                return null;
        }

    }
    //Metode som returnerer valgt kunde i tabellen
    public Insurance getValueAt(int rad) {
        return (Insurance) super.getData().get(rad);

    }
}