package projekt.model.Sell;

import projekt.controller.ProjektAppController;
import projekt.controller.ProjektDataController;
import projekt.model.ProjektCheckRecord;
import projekt.view.Client.ProjektClientPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 04.01.2017.
 */
public class SellTableModel extends AbstractTableModel {

    private ProjektDataController dataBaseController;


    private String[] columnNames =
            {
                    "IdSell",
                    "DaneKlienta",
                    "DaneSprzedawcy",
                    "Data",
                    "KosztCalkowity"

            };

    private List<Sell> sells;

    public SellTableModel(ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        sells = new ArrayList<Sell>();
    }

    public SellTableModel(List<Sell> sells, ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        this.sells = sells;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return sells.size();
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return int.class;


            default:
                return String.class;


        }
    }

    public boolean isCellEditable(int row, int column) {

        switch (column) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 4:
                return false;

            default:
                return true;
        }
    }

    public Object getValueAt(int row, int column) {
        Sell sell = getSell(row);


        switch (column) {
            case 0:
                return sell.getIdSell();
            case 1:
                return sell.getDaneKlienta();
            case 2:
                return sell.getDaneSprzedawcy();
            case 3:
                return sell.getData();
            case 4:
                return sell.getKosztCalkowity();

            default:
                return null;
        }

    }

    public void setValueAt(Object value, int row, int column) {

        if (checkIsUpdateSure()) {
            Sell sell = getSell(row);

            if (isValueCorrect(value, column)) {
                switch (column) {
                    case 3:
                        sell.setData((String) value);
                        break;
                }
                fireTableCellUpdated(row, column);

            } else {
                JOptionPane.showMessageDialog(new ProjektClientPanel(new ProjektAppController()), "Zla wartosc");
            }
        }

    }

    public Sell getSell(int row) {
        return sells.get(row);
    }

    public void addSell(Sell sell) {
        insertSell(getRowCount(), sell);
    }

    public void insertSell(int row, Sell sell) {
        sells.add(row, sell);
        fireTableRowsInserted(row, row);
    }

    public void removeSell(int row) {
        fireTableRowsDeleted(row, row);
    }

    private boolean isValueCorrect(Object value, int column) {
        boolean isDataCorrect;
        switch (column) {
            case 3:
                isDataCorrect = ProjektCheckRecord.checkData((String) value);
                break;
            default:
                isDataCorrect = true;
                break;
        }
        return isDataCorrect;
    }

    private boolean checkIsUpdateSure() {
        int dialogResult;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(dataBaseController.getBaseController().getAppFrame().getClientPanel(), "Czy chcesz edytować zakup", "Ostrzeżenie", dialogButton);
        if (dialogResult == 0) {
            return true;
        } else {
            return false;
        }
    }
}
