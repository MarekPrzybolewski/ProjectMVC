package projekt.view.Beer;

import projekt.controller.ProjektAppController;
import projekt.model.Beer.SearchBeerTableModel;
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
public class ProjektSelectedBeerPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable beerTable;

    public ProjektSelectedBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.beerTable = new JTable(baseController.getDataController().getMyDataBase().getSearchBeerTableModel());

        if(beerTable.getRowCount() == 0){
            JLabel nothingHere = new JLabel("Nie ma takich piw");
            nothingHere.setHorizontalAlignment(JLabel.CENTER);

            this.add(nothingHere,BorderLayout.CENTER);
        } else {
            beerTable.removeColumn(beerTable.getColumnModel().getColumn(0));
            setupBeerTable(beerTable);
            JScrollPane beerScrollPanel = new JScrollPane(beerTable);
            this.add(beerScrollPanel);
        }

        JButton backToMain = new JButton("Wróć");
        addListener(backToMain);
        this.add(backToMain,BorderLayout.SOUTH);
    }

    private void setupBeerTable(JTable beerTable){
        setupColumnNameWidth(beerTable);

        beerTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int idBeer;
                boolean isDataCorect=false;
                SearchBeerTableModel model = (SearchBeerTableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row,column);
                idBeer = (int)model.getValueAt(row,0);

                switch (column) {
                    case 1:
                        isDataCorect = ProjektCheckRecord.checkNazwa((String) data);
                        break;
                    case 2:
                        isDataCorect = ProjektCheckRecord.checkProcentAlkoholu(String.valueOf(data));
                        break;
                    case 3:
                        isDataCorect = ProjektCheckRecord.checkTypPiwa(String.valueOf(data));
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

    private void setupColumnNameWidth(JTable beerTable){
        TableColumn column = null;
        for(int i = 0; i < beerTable.getColumnCount(); i++){
            column = beerTable.getColumnModel().getColumn(i);
            switch(i){
                case 0: column.setPreferredWidth(50);
                    break;
                case 1: column.setPreferredWidth(100);
                    break;
                case 2: column.setPreferredWidth(50);
                    break;
                case 3: column.setPreferredWidth(100);
                    break;
                case 4: column.setPreferredWidth(50);
            }
        }
    }

    private void addListener(JButton backToMain){
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektBeerPanel(baseController));
            }
        });
    }
}
