package projekt.view.Client;

import projekt.controller.ProjektAppController;
import projekt.model.ProjektCheckRecord;
import projekt.model.Client.SearchClientTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class ProjektSelectedClientPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable clientTable;

    public ProjektSelectedClientPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.clientTable = new JTable(baseController.getDataController().getMyDataBase().getSearchClientTableModel());

        if(clientTable.getRowCount() == 0){
            JLabel nothingHere = new JLabel("Nie ma takich klientów");
            nothingHere.setHorizontalAlignment(JLabel.CENTER);
            this.add(nothingHere,BorderLayout.CENTER);
        } else {
            clientTable.removeColumn(clientTable.getColumnModel().getColumn(0));
            setupClientTable(clientTable);
            JScrollPane clientScrollPane = new JScrollPane(clientTable);
            this.add(clientScrollPane,BorderLayout.CENTER);
        }

        JButton backToMain = new JButton("Wróć");
        addListener(backToMain);
        this.add(backToMain,BorderLayout.SOUTH);
    }


    private void setupClientTable(JTable clientTable){
        setupColumnNameWidth(clientTable);

        clientTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idClient;
                boolean isDataCorect=false;
                SearchClientTableModel model = (SearchClientTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row,column);
                idClient = (int)model.getValueAt(row,0);

                switch (column) {
                    case 1:
                        isDataCorect = ProjektCheckRecord.checkImie((String) data);
                        break;
                    case 2:
                        isDataCorect = ProjektCheckRecord.checkNazwisko((String) data);
                        break;
                    case 3:
                        isDataCorect = ProjektCheckRecord.checkPesel((String) data);
                        break;
                    case 4:
                        isDataCorect = ProjektCheckRecord.checkEmail((String) data);
                        break;

                }

                if (isDataCorect) {
                    baseController.getDataController().getMyDataBase().updateClientTable(columnName, idClient, data);
                }


            }
        });


    }

    private void setupColumnNameWidth(JTable clientTable){
        TableColumn column = null;
        for(int i = 0; i < clientTable.getColumnCount(); i++){
            column = clientTable.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(50);
                    break;
                case 1: column.setPreferredWidth(100);
                    break;
                case 2: column.setPreferredWidth(100);
                    break;
                case 3: column.setPreferredWidth(150);
                    break;
                case 4: column.setPreferredWidth(150);
            }
        }
    }

    private void addListener(JButton backToMain){
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektClientPanel(baseController));
            }
        });
    }
}