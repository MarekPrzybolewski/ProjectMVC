package projekt.model;

import projekt.controller.ProjektAppController;
import projekt.view.Client.ProjektClientPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 01.01.2017.
 */
public class AdresTableModel extends AbstractTableModel {
    private String[] columnNames =
            {
                    "IdAdres",
                    "Ulica",
                    "Numer",
                    "Miejscowosc",
                    "Kod"
            };

    private List<Adres> adreses;

    public AdresTableModel(){
        adreses = new ArrayList<Adres>();
    }

    public AdresTableModel(List<Adres> adreses){
        this.adreses = adreses;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public String getColumnName(int column){
        return columnNames[column];
    }

    public int getRowCount(){
        return adreses.size();
    }

    public Class getColumnClass(int column){
        switch(column){
            case 0: return int.class;
            default: return String.class;

        }
    }

    public boolean isCellEditable(int row, int column){
        switch(column){
            case 0: return false;
            default: return true;
        }
    }

    public Object getValueAt(int row, int column){
        Adres adres = getAdres(row);


        switch(column){
            case 0: return adres.getIdAdres();
            case 1: return adres.getUlica();
            case 2: return adres.getNumer();
            case 3: return adres.getMiejscowosc();
            case 4: return adres.getKod();
            default: return null;
        }

    }

    public void setValueAt(Object value, int row, int column){
        int dialogResult = 1;

        if(checkIsUpdateSure()){
            Adres adres = getAdres(row);

            if(isValueCorrect(value,column)){
                switch(column){
                    case 1: adres.setUlica((String)value);
                        break;
                    case 2: adres.setNumer((String)value);
                        break;
                    case 3: adres.setMiejscowosc((String)value);
                        break;
                    case 4: adres.setKod((String)value);
                }
                fireTableCellUpdated(row,column);
            } else{
                JOptionPane.showMessageDialog(new ProjektClientPanel(new ProjektAppController()),"Zla wartosc");
            }
        }

    }

    public Adres getAdres(int row){
        return adreses.get(row);
    }

    public void addAdres(Adres adres){
        insertAdres(getRowCount(), adres);
    }

    public void insertAdres(int row, Adres adres){
        adreses.add(row,adres);
        fireTableRowsInserted(row,row);
    }

    public void removeAdres(int row){
        fireTableRowsDeleted(row,row);
    }

    private boolean isValueCorrect(Object value, int column){
        boolean isDataCorrect;
        switch(column){
            case 1:
                isDataCorrect = ProjektCheckRecord.checkImie((String)value);
                break;
            case 2:
                isDataCorrect = ProjektCheckRecord.checkNazwisko((String)value);
                break;
            case 3:
                isDataCorrect = ProjektCheckRecord.checkPesel((String)value);
                break;
            case 4:
                isDataCorrect = ProjektCheckRecord.checkEmail((String)value);
                break;
            default: isDataCorrect = false;
                break;
        }
        return isDataCorrect;
    }

    private boolean checkIsUpdateSure(){
        int dialogResult = 1;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(new ProjektClientPanel(new ProjektAppController()),"Czy chcesz edytować adres", "Ostrzeżenie",dialogButton);
        if(dialogResult == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
