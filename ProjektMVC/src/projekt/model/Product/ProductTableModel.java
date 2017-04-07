package projekt.model.Product;

import projekt.controller.ProjektDataController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 04.01.2017.
 */
public class ProductTableModel extends AbstractTableModel {
    private ProjektDataController dataBaseController;


    private String[] columnNames =
            {
                    "Nazwa",
                    "ProcentAlkoholu",
                    "TypPiwa",
                    "Cena"

            };

    private List<Product> products;

    public ProductTableModel(ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        products = new ArrayList<Product>();
    }

    public ProductTableModel(List<Product> products, ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        this.products = products;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return products.size();
    }

    public Class getColumnClass(int column) {
        switch (column) {

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
        Product product = getProduct(row);


        switch (column) {
            case 0:
                return product.getNazwa();
            case 1:
                return product.getProcent();
            case 2:
                return product.getTypPiwa();
            case 3:
                return product.getCena();

            default:
                return null;
        }

    }

    public void setValueAt(Object value, int row, int column) {

    }

    public Product getProduct(int row) {
        return products.get(row);
    }

    public void addProduct(Product product) {
        insertProduct(getRowCount(), product);
    }

    public void insertProduct(int row, Product product) {
        products.add(row, product);
        fireTableRowsInserted(row, row);
    }

    public ProductTableModel removeProduct(int[] row) {

        for(int i=row.length; i > 0; i--){
            products.remove(row[i-1]);
            this.fireTableRowsDeleted(row[i-1],row[i-1]);
        }

        return this;
    }

}
