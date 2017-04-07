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
 * Created by miQ333 on 04.01.2017.
 */
public class ProjektSellPanel extends JPanel{

    private ProjektAppController baseController;
    private JTable sellTable;

    public ProjektSellPanel(ProjektAppController baseController) {
        this.baseController = baseController;


        this.setLayout(new BorderLayout());
        this.sellTable = new JTable(baseController.getDataController().getMyDataBase().getSellTable());

        sellTable.removeColumn(sellTable.getColumnModel().getColumn(0));

        setupSellTable(sellTable);
        JScrollPane sellScrollPanel = new JScrollPane(sellTable);

        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);



        this.add(new JLabel("Tabela Zakup", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(sellScrollPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void setupSellTable(JTable sellTable) {
        setupColumnNameWidth(sellTable);

        sellTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idBeer;
                boolean isDataCorect = false;
                SellTableModel model = (SellTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                idBeer = (int) model.getValueAt(row, 0);

                switch (column) {
                    case 3:
                        System.out.println(data);
                        isDataCorect = ProjektCheckRecord.checkData((String) data);
                        break;
                }

                if (isDataCorect) {
                    baseController.getDataController().getMyDataBase().updateSellTable(columnName, idBeer, data);
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

    void makeSouthPanel(JPanel southPanel) {

        southPanel.setLayout(new GridLayout());

        JButton addSellButton = new JButton("Dodaj Zakup");
        JButton removeSellButton = new JButton("Usuń wybrane Zakupy");
        JButton searchSellButton = new JButton("Wyszukaj Zakup");
        addListeners(addSellButton, removeSellButton,searchSellButton);
        southPanel.add(addSellButton);
        southPanel.add(removeSellButton);
        southPanel.add(searchSellButton);
    }

    void addListeners(JButton addSellButton, JButton removeSellButton, JButton searchSellButton) {

        addSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektAddSellPanel(baseController));
            }
        });

        removeSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = 1;
                int[] rows = sellTable.getSelectedRows();
                if (rows.length > 1) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczone zakupy?", "Ostrzeżenie", dialogButton);

                } else {
                    if (rows.length == 1) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczony zakup?", "Ostrzeżenie", dialogButton);
                    } else {
                        JOptionPane.showMessageDialog(baseController.getAppFrame(), "Musisz zaznaczyć co najmniej jedne zakup");
                    }
                }

                if (dialogResult == 0) {
                    int[] sellsId = new int[rows.length];
                    for (int i = 0; i < rows.length; i++) {
                        sellsId[i] = (int) baseController.getDataController().getMyDataBase().getSellTable().getValueAt(rows[i], 0);
                    }

                    for (int i = 0; i < sellsId.length; i++) {
                        baseController.getDataController().getMyDataBase().removeSell(sellsId[i]);
                    }

                    baseController.getAppFrame().setupFrame(new ProjektSellPanel(baseController));
                } else {
                    baseController.getAppFrame().setupFrame(new ProjektSellPanel(baseController));
                }

            }
        });

        searchSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSearchSellPanel(baseController));
            }
        });


    }

}
