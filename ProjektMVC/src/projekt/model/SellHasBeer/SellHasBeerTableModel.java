package projekt.model.SellHasBeer;

import projekt.controller.ProjektDataController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 07.01.2017.
 */
public class SellHasBeerTableModel extends AbstractTableModel {
    private ProjektDataController dataController;

    private String[] columnNames =
            {
                    "IdSell",
                    "IdBeer",
                    "DaneKlienta",
                    "DaneSprzedawcy",
                    "DanePiwa",
                    "Data"
            };

    private List<SellHasBeer> table;

    public SellHasBeerTableModel(ProjektDataController dataController) {
        this.dataController = dataController;
        table = new ArrayList<SellHasBeer>();
    }

    public SellHasBeerTableModel(List<SellHasBeer> table, ProjektDataController dataController){
        this.dataController = dataController;
        this.table = table;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return table.size();
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return int.class;
            case 1:
                return int.class;


            default:
                return String.class;


        }
    }

    public boolean isCellEditable(int row, int column) {

        switch (column) {
            default:
                return false;
        }
    }

    public Object getValueAt(int row, int column) {
        SellHasBeer sell = getSellHasBeer(row);


        switch (column) {
            case 0:
                return sell.getIdSell();
            case 1:
                return sell.getIdBeer();
            case 2:
                return sell.getDaneKlienta();
            case 3:
                return sell.getDaneSprzedawce();
            case 4:
                return sell.getDanePiwa();
            case 5:
                return sell.getData();

            default:
                return null;
        }

    }

    public void setValueAt(Object value, int row, int column) {
                fireTableCellUpdated(row, column);


    }

    public SellHasBeer getSellHasBeer(int row) {
        return table.get(row);
    }

    public void addSellHasBeer(SellHasBeer sell) {
        insertSellHasBeer(getRowCount(), sell);
    }

    public void insertSellHasBeer(int row, SellHasBeer sell) {
        table.add(row, sell);
        fireTableRowsInserted(row, row);
    }

    public void removeSellHasBeer(int row) {
        fireTableRowsDeleted(row, row);
    }
}
