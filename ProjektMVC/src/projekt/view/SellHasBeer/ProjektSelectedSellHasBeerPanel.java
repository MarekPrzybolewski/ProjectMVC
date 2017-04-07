package projekt.view.SellHasBeer;

import projekt.controller.ProjektAppController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 07.01.2017.
 */
public class ProjektSelectedSellHasBeerPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable sellHasBeerTable;

    public ProjektSelectedSellHasBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;

        this.setLayout(new BorderLayout());

        this.sellHasBeerTable = new JTable(baseController.getDataController().getMyDataBase().getSearchSellHasBeerTable());

        if(sellHasBeerTable.getRowCount() == 0){
            JLabel nothingHere = new JLabel("Nie ma takich zakupów");
            nothingHere.setHorizontalAlignment(JLabel.CENTER);
            this.add(nothingHere,BorderLayout.CENTER);
        } else {
            sellHasBeerTable.removeColumn(sellHasBeerTable.getColumnModel().getColumn(0));
            sellHasBeerTable.removeColumn(sellHasBeerTable.getColumnModel().getColumn(0));
            setupSellHasBeerTable(sellHasBeerTable);
            JScrollPane sellScrollPanel = new JScrollPane(sellHasBeerTable);
            this.add(sellScrollPanel);
        }

        JButton backToMain = new JButton("Wróć");
        addListener(backToMain);
        this.add(backToMain,BorderLayout.SOUTH);
    }


    private void setupSellHasBeerTable(JTable sellTable){
        setupColumnNameWidth(sellTable);

    }

    private void setupColumnNameWidth(JTable sellHasBeerTable) {
        TableColumn column = null;
        for (int i = 0; i < sellHasBeerTable.getColumnCount(); i++) {
            column = sellHasBeerTable.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(150);
                    break;
                case 1:
                    column.setPreferredWidth(150);
                    break;
                case 2:
                    column.setPreferredWidth(150);
                    break;
                case 3:
                    column.setPreferredWidth(100);
                    break;
            }
        }
    }

    private void addListener(JButton backToMain){
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSellHasBeerPanel(baseController));
            }
        });
    }
}
