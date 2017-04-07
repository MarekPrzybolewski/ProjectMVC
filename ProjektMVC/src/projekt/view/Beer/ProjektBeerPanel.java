package projekt.view.Beer;

import projekt.controller.ProjektAppController;
import projekt.model.Beer.BeerTableModel;
import projekt.model.ProjektCheckRecord;

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
public class ProjektBeerPanel extends JPanel {

    private ProjektAppController baseController;
    private JTable beerTable;

    public ProjektBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.beerTable = new JTable(baseController.getDataController().getMyDataBase().getBeersTable());

        beerTable.removeColumn(beerTable.getColumnModel().getColumn(0));

        setupBeerTable(beerTable);
        JScrollPane beerScrollPanel = new JScrollPane(beerTable);

        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);


        this.add(new JLabel("Tabela Piwo", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(beerScrollPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

    }

    private void setupBeerTable(JTable beerTable) {
        setupColumnNameWidth(beerTable);

        beerTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idBeer;
                boolean isDataCorect = false;
                BeerTableModel model = (BeerTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                idBeer = (int) model.getValueAt(row, 0);

                switch (column) {
                    case 1:
                        isDataCorect = ProjektCheckRecord.checkNazwa((String) data);
                        break;
                    case 2:
                        isDataCorect = ProjektCheckRecord.checkProcentAlkoholu(String.valueOf(data));
                        break;
                    case 3:
                        isDataCorect = ProjektCheckRecord.checkTypPiwa((String) data);
                        break;
                    case 4:
                        isDataCorect = ProjektCheckRecord.checkCena(String.valueOf(data));
                        break;
                }

                if (isDataCorect) {
                    baseController.getDataController().getMyDataBase().updateBeerTable(columnName, idBeer, data);
                }


            }
        });


    }

    private void setupColumnNameWidth(JTable beerTable) {
        TableColumn column = null;
        for (int i = 0; i < beerTable.getColumnCount(); i++) {
            column = beerTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(50);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(50);
                    break;
                case 3:
                    column.setPreferredWidth(100);
                    break;
                case 4:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }

    void makeSouthPanel(JPanel southPanel) {

        southPanel.setLayout(new GridLayout());

        JButton addBeerButton = new JButton("Dodaj Piwo");
        JButton removeBeerButton = new JButton("Usuń wybrane Piwa");
        JButton searchBeerButton = new JButton("Wyszukaj Piwo");
        addListeners(addBeerButton, removeBeerButton,searchBeerButton);
        southPanel.add(addBeerButton);
        southPanel.add(removeBeerButton);
        southPanel.add(searchBeerButton);
    }


    void addListeners(JButton addBeerButton, JButton removeBeerButton, JButton searchBeerButton) {

        addBeerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektAddBeerPanel(baseController));
            }
        });

        removeBeerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = 1;
                int[] rows = beerTable.getSelectedRows();
                if (rows.length > 1) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczone piwa?", "Ostrzeżenie", dialogButton);

                } else {
                    if (rows.length == 1) {
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(), "Czy chcesz usunąć zaznaczone piwo?", "Ostrzeżenie", dialogButton);
                    } else {
                        JOptionPane.showMessageDialog(baseController.getAppFrame(), "Musisz zaznaczyć co najmniej jedno piwo");
                    }
                }

                if (dialogResult == 0) {
                    int[] beersId = new int[rows.length];
                    for (int i = 0; i < rows.length; i++) {
                        beersId[i] = (int) baseController.getDataController().getMyDataBase().getBeersTable().getValueAt(rows[i], 0);
                    }

                    for (int i = 0; i < beersId.length; i++) {
                        baseController.getDataController().getMyDataBase().removeBeer(beersId[i]);
                    }

                    baseController.getAppFrame().setupFrame(new ProjektBeerPanel(baseController));
                } else {
                    baseController.getAppFrame().setupFrame(new ProjektBeerPanel(baseController));
                }

            }
        });

        searchBeerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSearchBeerPanel(baseController));
            }
        });


    }

}
