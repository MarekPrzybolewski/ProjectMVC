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
public class ProjektSellHasBeerPanel extends JPanel{

    private ProjektAppController baseController;
    private JTable sellHasBeerTable;

    public ProjektSellHasBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;


        this.setLayout(new BorderLayout());
        this.sellHasBeerTable = new JTable(baseController.getDataController().getMyDataBase().getSellHasBeerTable());

        sellHasBeerTable.removeColumn(sellHasBeerTable.getColumnModel().getColumn(0));
        sellHasBeerTable.removeColumn(sellHasBeerTable.getColumnModel().getColumn(0));

        setupSellHasBeerTable(sellHasBeerTable);
        JScrollPane sellScrollPanel = new JScrollPane(sellHasBeerTable);

        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);



        this.add(new JLabel("Tabela dokładne zakupy", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(sellScrollPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void setupSellHasBeerTable(JTable sellHasBeerTable) {
        setupColumnNameWidth(sellHasBeerTable);
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

    void makeSouthPanel(JPanel southPanel) {

        southPanel.setLayout(new GridLayout());

        JButton searchSellButton = new JButton("Wyszukaj Zakup");
        addListeners(searchSellButton);
        southPanel.add(searchSellButton);

    }

    void addListeners(JButton searchSellButton) {


        searchSellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSearchSellHasBeerPanel(baseController));
            }
        });


    }


}
