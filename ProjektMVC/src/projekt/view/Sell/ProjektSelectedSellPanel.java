package projekt.view.Sell;

import projekt.controller.ProjektAppController;
import projekt.model.ProjektCheckRecord;
import projekt.model.Sell.SellTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 05.01.2017.
 */
public class ProjektSelectedSellPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable sellTable;

    public ProjektSelectedSellPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());

        this.sellTable = new JTable(baseController.getDataController().getMyDataBase().getSearchSellTableModel());

        if(sellTable.getRowCount() == 0){
            JLabel nothingHere = new JLabel("Nie ma takich zakupów");
            nothingHere.setHorizontalAlignment(JLabel.CENTER);
            this.add(nothingHere,BorderLayout.CENTER);
        } else {
            sellTable.removeColumn(sellTable.getColumnModel().getColumn(0));
            setupSellTable(sellTable);
            JScrollPane sellScrollPanel = new JScrollPane(sellTable);
            this.add(sellScrollPanel);
        }

        JButton backToMain = new JButton("Wróć");
        addListener(backToMain);
        this.add(backToMain,BorderLayout.SOUTH);
    }

    private void setupSellTable(JTable sellTable){
        setupColumnNameWidth(sellTable);

        sellTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idBeer;
                boolean isDataCorect=false;
                SellTableModel model = (SellTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row,column);
                int idSell = (int)model.getValueAt(row,0);

                switch (column) {
                    case 3:
                        isDataCorect = ProjektCheckRecord.checkData(String.valueOf(data));
                        break;
                }

                if (isDataCorect) {
                    baseController.getDataController().getMyDataBase().updateSellTable(columnName, idSell, data);
                }


            }
        });
    }

    private void setupColumnNameWidth(JTable sellTable) {
        TableColumn column = null;
        for (int i = 0; i < sellTable.getColumnCount(); i++) {
            column = sellTable.getColumnModel().getColumn(i);
            switch (i) {

                case 0:
                    column.setPreferredWidth(150);
                    break;
                case 1:
                    column.setPreferredWidth(150);
                    break;
                case 2:
                    column.setPreferredWidth(50);
                    break;
                case 3:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }

    private void addListener(JButton backToMain){
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSellPanel(baseController));
            }
        });
    }

}
