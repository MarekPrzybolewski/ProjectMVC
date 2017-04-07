package projekt.model.Seller;

import projekt.controller.ProjektAppController;
import projekt.controller.ProjektDataController;
import projekt.model.ProjektCheckRecord;
import projekt.view.Client.ProjektClientPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class SellerTableModel extends AbstractTableModel {
    private ProjektDataController dataBaseController;


    private String[] columnNames =
            {
                    "IdSeller",
                    "Imie",
                    "Nazwisko",
                    "Pensja",
                    "Pesel",
                    "Email",

            };

    private List<Seller> sellers;

    public SellerTableModel(ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        sellers = new ArrayList<Seller>();
    }

    public SellerTableModel(List<Seller> sellers, ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        this.sellers = sellers;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return sellers.size();
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

            default:
                return true;
        }
    }

    public Object getValueAt(int row, int column) {
        Seller seller = getSeller(row);


        switch (column) {
            case 0:
                return seller.getIdSeller();
            case 1:
                return seller.getImie();
            case 2:
                return seller.getNazwisko();
            case 3:
                return seller.getPensja();
            case 4:
                return seller.getPesel();
            case 5:
                return seller.getEmail();

            default:
                return null;
        }

    }

    public void setValueAt(Object value, int row, int column) {
        int dialogResult = 1;

        if (checkIsUpdateSure()) {
            Seller seller = getSeller(row);

            if (isValueCorrect(value, column)) {
                switch (column) {
                    case 1:
                        seller.setImie((String) value);
                        break;
                    case 2:
                        seller.setNazwisko((String) value);
                        break;
                    case 3:
                        seller.setPensja(Double.valueOf((String) value));
                        break;
                    case 4:
                        seller.setPesel((String) value);
                        break;
                    case 5:
                        seller.setEmail((String) value);
                        break;

                }
                fireTableCellUpdated(row, column);

            } else {
                JOptionPane.showMessageDialog(new ProjektClientPanel(new ProjektAppController()), "Zla wartosc");
            }
        }

    }

    public Seller getSeller(int row) {
        return sellers.get(row);
    }

    public void addSeller(Seller seller) {
        insertSeller(getRowCount(), seller);
    }

    public void insertSeller(int row, Seller seller) {
        sellers.add(row, seller);
        fireTableRowsInserted(row, row);
    }

    public void removeSeller(int row) {
        fireTableRowsDeleted(row, row);
    }

    private boolean isValueCorrect(Object value, int column) {
        boolean isDataCorrect;
        switch (column) {
            case 1:
                isDataCorrect = ProjektCheckRecord.checkImie((String) value);
                break;
            case 2:
                isDataCorrect = ProjektCheckRecord.checkNazwisko((String) value);
                break;
            case 3:
                isDataCorrect = ProjektCheckRecord.checkPensja((String) value);
                break;
            case 4:
                isDataCorrect = ProjektCheckRecord.checkPesel((String) value);
                break;
            case 5:
                isDataCorrect = ProjektCheckRecord.checkEmail((String) value);
                break;
            default:
                isDataCorrect = true;
                break;
        }
        return isDataCorrect;
    }

    private boolean checkIsUpdateSure() {
        int dialogResult = 1;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(dataBaseController.getBaseController().getAppFrame().getClientPanel(), "Czy chcesz edytować pracownika", "Ostrzeżenie", dialogButton);
        if (dialogResult == 0) {
            return true;
        } else {
            return false;
        }
    }
}
