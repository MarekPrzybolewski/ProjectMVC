package projekt.view.Seller;

import projekt.controller.ProjektAppController;
import projekt.model.ProjektCheckRecord;
import projekt.model.Seller.SearchSellerTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 03.01.2017.
 */
public class ProjektSelectedSellerPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable sellerTable;

    public ProjektSelectedSellerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.sellerTable = new JTable(baseController.getDataController().getMyDataBase().getSearchSellerTableModel());

        if(sellerTable.getRowCount() == 0){
            JLabel nothingHere = new JLabel("Nie ma takich sprzedawców");
            nothingHere.setHorizontalAlignment(JLabel.CENTER);
            this.add(nothingHere,BorderLayout.CENTER);
        } else {
            sellerTable.removeColumn(sellerTable.getColumnModel().getColumn(0));
            setupSellerTable(sellerTable);
            JScrollPane sellerScrollPane = new JScrollPane(sellerTable);
            this.add(sellerScrollPane);
        }

        JButton backToMain = new JButton("Wróć");
        addListener(backToMain);
        this.add(backToMain,BorderLayout.SOUTH);
    }


    private void setupSellerTable(JTable sellerTable){
        setupColumnNameWidth(sellerTable);

        sellerTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idSeller;
                boolean isDataCorect=false;

                SearchSellerTableModel model = (SearchSellerTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row,column);
                idSeller = (int)model.getValueAt(row,0);

                switch (column) {
                    case 1:
                        isDataCorect = ProjektCheckRecord.checkImie((String) data);
                        break;
                    case 2:
                        isDataCorect = ProjektCheckRecord.checkNazwisko((String) data);
                        break;
                    case 3:
                        isDataCorect = ProjektCheckRecord.checkPensja(String.valueOf(data));
                        break;
                    case 4:
                        isDataCorect = ProjektCheckRecord.checkPesel((String) data);
                        break;
                    case 5:
                        isDataCorect = ProjektCheckRecord.checkPesel((String) data);
                        break;

                }

                if (isDataCorect) {
                    baseController.getDataController().getMyDataBase().updateSellerTable(columnName, idSeller, data);
                }


            }
        });


    }

    private void setupColumnNameWidth(JTable sellerTable){
        TableColumn column = null;
        for(int i = 0; i < sellerTable.getColumnCount(); i++){
            column = sellerTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(100);
                    break;
                case 3:
                    column.setPreferredWidth(80);
                    break;
                case 4:
                    column.setPreferredWidth(150);
                    break;
                case 5:
                    column.setPreferredWidth(150);

            }
        }
    }

    private void addListener(JButton backToMain){
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSellerPanel(baseController));
            }
        });
    }
}
