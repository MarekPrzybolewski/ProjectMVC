package projekt.view.Client;

import projekt.controller.ProjektAppController;
import projekt.model.Client.ClientTableModel;
import projekt.model.ProjektCheckRecord;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class ProjektClientPanel extends JPanel {

    private ProjektAppController baseController;
    private JTable clientTable;


    public ProjektClientPanel(ProjektAppController baseController){
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.clientTable = new JTable(baseController.getDataController().getMyDataBase().getClientsTable());

        clientTable.removeColumn(clientTable.getColumnModel().getColumn(0));


        setupClientTable(clientTable);
        JScrollPane clientScrollPane = new JScrollPane(clientTable);

        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);


        this.add(new JLabel("Tabela klient",SwingConstants.CENTER),BorderLayout.NORTH);
        this.add(clientScrollPane,BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);

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
                ClientTableModel model = (ClientTableModel) e.getSource();
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

    void makeSouthPanel(JPanel southPanel){

        southPanel.setLayout(new GridLayout());

        JButton addClientButton = new JButton("Dodaj Klienta");
        JButton removeClientButton = new JButton("Usuń wybranych Klientów");
        JButton searchClientButton = new JButton("Wyszukaj klienta");
        addListeners(addClientButton,removeClientButton,searchClientButton);
        southPanel.add(addClientButton);
        southPanel.add(removeClientButton);
        southPanel.add(searchClientButton);


    }

    void addListeners(JButton addClientButton, JButton removeClientButton, JButton searchClientButton){

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektAddClientPanel(baseController));
            }
        });

        removeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogResult = 1;
                int[] rows= clientTable.getSelectedRows();
                boolean checkClients = ifClientsHaveSell(rows);
                if(checkClients){

                    if(rows.length > 1){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(),"Czy chcesz usunąć zaznaczonych klientów?", "Ostrzeżenie",dialogButton);

                    } else {
                        if(rows.length == 1){
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            dialogResult = JOptionPane.showConfirmDialog(baseController.getAppFrame(),"Czy chcesz usunąć zaznaczonego klienta?", "Ostrzeżenie",dialogButton);
                        } else{
                            JOptionPane.showMessageDialog(baseController.getAppFrame(),"Musisz zaznaczyć co najmniej jednego klienta");
                        }
                    }

                    if(dialogResult == 0){
                        int[] clientsId = new int[rows.length];
                        for(int i = 0; i < rows.length; i++){
                            clientsId[i] = (int)baseController.getDataController().getMyDataBase().getClientsTable().getValueAt(rows[i],0);
                        }

                        for(int i = 0; i < clientsId.length; i++) {
                            baseController.getDataController().getMyDataBase().removeClient(clientsId[i]);
                        }

                        baseController.getAppFrame().setupFrame(new ProjektClientPanel(baseController));
                    } else {
                        baseController.getAppFrame().setupFrame(new ProjektClientPanel(baseController));
                    }
                } else {
                    JOptionPane.showMessageDialog(new ProjektClientPanel(new ProjektAppController()),"Najpierw usuń zakup danego klienta, jeśli chcesz go usunąć");

                }

            }

        });

        searchClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseController.getAppFrame().setupFrame(new ProjektSearchClientPanel(baseController));
            }
        });
    }

    private boolean ifClientsHaveSell(int[] rows){
        int[] clientsId = new int[rows.length];
        for(int i = 0; i < rows.length; i++){
            clientsId[i] = (int)baseController.getDataController().getMyDataBase().getClientsTable().getValueAt(rows[i],0);
        }
        for(int i = 0; i < rows.length; i++){
            if(baseController.getDataController().getMyDataBase().checkIfTableHaveSell(clientsId[i],"Client")){
                System.out.println("JESTEM W FALSE");
                return false;
            }
        }
        return true;
    }

}
