package projekt.view.Seller;

import projekt.controller.ProjektAppController;
import projekt.model.ProjektCheckRecord;
import projekt.model.Seller.SellerTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 01.01.2017.
 */

public class ProjektSellerPanel extends JPanel {

    private ProjektAppController baseController;
    private JTable sellerTable;


    public ProjektSellerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.sellerTable = new JTable(baseController.getDataController().getMyDataBase().getSellersTable());

        sellerTable.removeColumn(sellerTable.getColumnModel().getColumn(0));

        setupSellerTable(sellerTable);
        JScrollPane sellerScrollPane = new JScrollPane(sellerTable);

        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);


        this.add(new JLabel("Tabela Pracownik", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(sellerScrollPane, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

    }

    private void setupSellerTable(JTable sellerTable) {
        setupColumnNameWidth(sellerTable);

        sellerTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idSeller;
                boolean isDataCorect = false;
                SellerTableModel model = (SellerTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                idSeller = (int) model.getValueAt(row, 0);

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
                        isDataCorect = ProjektCheckRecord.checkEmail((String) data);
                        break;

                }

                if (isDataCorect) {

                    baseController.getDataController().getMyDataBase().updateSellerTable(columnName, idSeller, data);
                }


            }
        });


    }


    private void setupColumnNameWidth(JTable sellerTable) {
        TableColumn column = null;
        for (int i = 0; i < sellerTable.getColumnCount(); i++) {
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

    void makeSouthPanel(JPanel southPanel) {

        southPanel.setLayout(new GridLayout());

        JButton addSellerButton = new JButton("Dodaj Pracownika");
        JButton removeSellerButton = new JButton("Usuń wybranych Pracowników");
        JButton searchSellerButton = new JButton("Wyszukaj Pracownika");
        addListeners(addSellerButton, removeSellerButton,searchSellerButton);
        southPanel.add(addSellerButton);
        southPanel.add(removeSellerButton);
        southPanel.add(searchSellerButton);


    }

    void addListeners(JButton addSellerButton, JButton removeSellerButton, JButton searchSellerButton) {

        addSellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektAddSellerPanel(baseController));
            }
        });

        removeSellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = 1;
                int[] rows = sellerTable.getSelectedRows();
                if (rows.length > 1) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczonych pracowników?", "Ostrzeżenie", dialogButton);

                } else {
                    if (rows.length == 1) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczonego pracownika?", "Ostrzeżenie", dialogButton);
                    } else {
                        JOptionPane.showMessageDialog(baseController.getAppFrame(), "Musisz zaznaczyć co najmniej jednego pracownika");
                    }
                }

                if (dialogResult == 0) {
                    int[] sellersId = new int[rows.length];
                    for (int i = 0; i < rows.length; i++) {
                        sellersId[i] = (int) baseController.getDataController().getMyDataBase().getSellersTable().getValueAt(rows[i], 0);
                    }

                    for (int i = 0; i < sellersId.length; i++) {
                        baseController.getDataController().getMyDataBase().removeSeller(sellersId[i]);
                    }

                    baseController.getAppFrame().setupFrame(new ProjektSellerPanel(baseController));
                } else {
                    baseController.getAppFrame().setupFrame(new ProjektSellerPanel(baseController));
                }

            }
        });

        searchSellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSearchSellerPanel(baseController));
            }
        });


    }
    private boolean ifSellersHaveSell(int[] rows){
        int[] sellersId = new int[rows.length];
        for(int i = 0; i < rows.length; i++){
            sellersId[i] = (int)baseController.getDataController().getMyDataBase().getSellersTable().getValueAt(rows[i],0);
        }
        for(int i = 0; i < rows.length; i++){
            if(baseController.getDataController().getMyDataBase().checkIfTableHaveSell(sellersId[i],"Seller")){
                System.out.println("JESTEM W FALSE");
                return false;
            }
        }
        return true;
    }
}

