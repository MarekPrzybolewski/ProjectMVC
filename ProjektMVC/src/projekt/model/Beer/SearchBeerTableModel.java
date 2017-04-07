package projekt.model.Beer;

import projekt.controller.ProjektAppController;
import projekt.controller.ProjektDataController;
import projekt.model.ProjektCheckRecord;
import projekt.view.Beer.ProjektBeerPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by miQ333 on 03.01.2017.
 */
public class SearchBeerTableModel extends AbstractTableModel {
    private ProjektDataController dataBaseController;


    private String[] columnNames =
            {
                    "IdPiwa",
                    "Nazwa",
                    "ProcentAlkoholu",
                    "TypPiwa",
                    "Cena"

            };

    private List<Beer> beers;

    public SearchBeerTableModel(ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        beers = new ArrayList<Beer>();
    }

    public SearchBeerTableModel(List<Beer> beers, ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        this.beers = beers;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return beers.size();
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
        Beer beer = getBeer(row);


        switch (column) {
            case 0:
                return beer.getIdBeer();
            case 1:
                return beer.getNazwa();
            case 2:
                return beer.getProcentAlkoholu();
            case 3:
                return beer.getTypPiwa();
            case 4:
                return beer.getCena();

            default:
                return null;
        }

    }

    public void setValueAt(Object value, int row, int column) {
        int dialogResult = 1;

        if (checkIsUpdateSure()) {
            Beer beer = getBeer(row);

            if (isValueCorrect(value, column)) {
                switch (column) {
                    case 1:
                        beer.setNazwa((String)value);
                        break;
                    case 2:
                        beer.setProcentAlkoholu(Double.parseDouble((String)value));
                        break;
                    case 3:
                        beer.setTypPiwa((String) value);
                        break;
                    case 4:
                        beer.setCena(Double.parseDouble((String)value));
                        break;
                }
                fireTableCellUpdated(row, column);

            } else {
                JOptionPane.showMessageDialog(new ProjektBeerPanel(new ProjektAppController()), "Zla wartosc");
            }
        }

    }

    public Beer getBeer(int row) {
        return beers.get(row);
    }

    public void addBeer(Beer beer) {
        insertBeer(getRowCount(), beer);
    }

    public void insertBeer(int row, Beer beer) {
        beers.add(row, beer);
        fireTableRowsInserted(row, row);
    }

    public void removeBeer(int row) {
        fireTableRowsDeleted(row, row);
    }

    private boolean isValueCorrect(Object value, int column) {
        boolean isDataCorrect;
        switch (column) {
            case 1:
                isDataCorrect = ProjektCheckRecord.checkNazwa((String) value);
                break;
            case 2:
                isDataCorrect = ProjektCheckRecord.checkProcentAlkoholu((String) value);
                break;
            case 3:
                isDataCorrect = ProjektCheckRecord.checkTypPiwa((String) value);
                break;
            case 4:
                isDataCorrect = ProjektCheckRecord.checkCena((String) value);
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
        dialogResult = JOptionPane.showConfirmDialog(dataBaseController.getBaseController().getAppFrame().getClientPanel(), "Czy chcesz edytować piwo", "Ostrzeżenie", dialogButton);
        if (dialogResult == 0) {
            return true;
        } else {
            return false;
        }
    }
}