package projekt.model.Client;

import projekt.controller.ProjektAppController;
import projekt.controller.ProjektDataController;
import projekt.model.ProjektCheckRecord;
import projekt.view.Client.ProjektClientPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class SearchClientTableModel extends AbstractTableModel{
    private ProjektDataController dataController;
    private String[] columnNames =
            {
                    "IdClient",
                    "Imie",
                    "Nazwisko",
                    "Pesel",
                    "Email"
            };

    private List<Client> searchClients;

    public SearchClientTableModel(ProjektDataController dataController){
        searchClients = new ArrayList<Client>();
        this.dataController = dataController;
    }

    public SearchClientTableModel(List<Client> clients,ProjektDataController dataController){
        this.searchClients = clients;
        this.dataController = dataController;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public String getColumnName(int column){
        return columnNames[column];
    }

    public int getRowCount(){
        return searchClients.size();
    }

    public Class getColumnClass(int column){
        switch(column){
            case 0: return int.class;


            default: return String.class;


        }
    }

    public boolean isCellEditable(int row, int column){

        switch(column){
            case 0: return false;
            default: return true;
        }
    }

    public Object getValueAt(int row, int column){
        Client client = getClient(row);


        switch(column){
            case 0: return client.getIdClient();
            case 1: return client.getImie();
            case 2: return client.getNazwisko();
            case 3: return client.getPesel();
            case 4: return client.getEmail();
            default: return null;
        }

    }

    public void setValueAt(Object value, int row, int column){
        int dialogResult = 1;

        if(checkIsUpdateSure()){
            Client client = getClient(row);

            if(isValueCorrect(value,column)){
                switch(column){
                    case 1: client.setImie((String)value);
                        break;
                    case 2: client.setNazwisko((String)value);
                        break;
                    case 3: client.setPesel((String)value);
                        break;
                    case 4: client.setEmail((String)value);
                        break;
                }
                fireTableCellUpdated(row,column);

            } else{
                JOptionPane.showMessageDialog(new ProjektClientPanel(new ProjektAppController()),"Zla wartosc");
            }
        }

    }

    public Client getClient(int row){
        return searchClients.get(row);
    }

    public void addClient(Client client){
        insertClient(getRowCount(), client);
    }

    public void insertClient(int row, Client client){
        searchClients.add(row,client);
        fireTableRowsInserted(row,row);
    }

    public void removeClient(int row){
        fireTableRowsDeleted(row,row);
    }

    private boolean isValueCorrect(Object value, int column){
        boolean isDataCorrect;
        switch(column){
            case 1:
                isDataCorrect = ProjektCheckRecord.checkImie((String)value);
                break;
            case 2:
                isDataCorrect = ProjektCheckRecord.checkNazwisko((String)value);
                break;
            case 3:
                isDataCorrect = ProjektCheckRecord.checkPesel((String)value);
                break;
            case 4:
                isDataCorrect = ProjektCheckRecord.checkEmail((String)value);
                break;
            default: isDataCorrect = true;
                break;
        }
        return isDataCorrect;
    }

    private boolean checkIsUpdateSure(){
        int dialogResult = 1;
        int dialogButton = JOptionPane.YES_NO_OPTION;
        dialogResult = JOptionPane.showConfirmDialog(dataController.getBaseController().getAppFrame().getClientPanel(),"Czy chcesz edytować klienta", "Ostrzeżenie",dialogButton);
        if(dialogResult == 0){
            return true;
        }
        else{
            return false;
        }
    }

}
