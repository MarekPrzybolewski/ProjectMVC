package projekt.view.SellHasBeer;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 07.01.2017.
 */
public class ProjektSearchSellHasBeerPanel extends JPanel {
    private ProjektAppController baseController;
    private JPanel searchPanel;
    private JComboBox<String> atributesCombo;
    private String column;

    public ProjektSearchSellHasBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        searchPanel = new JPanel();
        this.add(searchPanel, BorderLayout.CENTER);
        searchPanel.setLayout(new GridBagLayout());

        JLabel whichAtribuetLabel = new JLabel("Po którym atrybucie chesz wyszukać ?");

        atributesCombo = new JComboBox<>();
        atributesCombo.addItem("DaneKlienta");
        atributesCombo.addItem("DaneSprzedawcy");
        atributesCombo.addItem("DanePiwa");
        atributesCombo.addItem("Data");


        JButton searchBeerButton = new JButton("Wyszukaj");


        searchBeerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch((String)atributesCombo.getSelectedItem()){
                    case "DaneKlienta":
                        JComboBox clientsBox = new JComboBox();
                        clientsBox =baseController.getDataController().getMyDataBase().addAllClientsToComboBox(clientsBox,"Client");
                        JOptionPane.showMessageDialog(null,clientsBox,"Wybierz klienta",JOptionPane.QUESTION_MESSAGE);
                        baseController.getDataController().getMyDataBase().searchSellHasBeerTable("DaneKlienta",(String)clientsBox.getSelectedItem());
                        baseController.getAppFrame().setupFrame(new ProjektSelectedSellHasBeerPanel(baseController));
                        break;

                    case "DaneSprzedawcy":
                        JComboBox sellersBox = new JComboBox();
                        sellersBox =baseController.getDataController().getMyDataBase().addAllClientsToComboBox(sellersBox,"Seller");
                        JOptionPane.showMessageDialog(null,sellersBox,"Wybierz klienta",JOptionPane.QUESTION_MESSAGE);
                        baseController.getDataController().getMyDataBase().searchSellHasBeerTable("DaneSprzedawcy",(String)sellersBox.getSelectedItem());
                        baseController.getAppFrame().setupFrame(new ProjektSelectedSellHasBeerPanel(baseController));
                        break;

                    case "Data":
                        JPanel dataPanel = new JPanel();
                        dataPanel.setLayout(new GridBagLayout());
                        dataPanel.add(new JLabel("Podaj date w formacie YYYY-MM-DD"), new GBC(0,0).setAnchor(GBC.CENTER));
                        JTextField dataField = new JTextField(10);
                        dataPanel.add(dataField,new GBC(0,1).setAnchor(GBC.CENTER));
                        JOptionPane.showMessageDialog(null,dataPanel,"Podaj date",JOptionPane.QUESTION_MESSAGE);
                        baseController.getDataController().getMyDataBase().searchSellHasBeerTable("Data",dataField.getText().trim());
                        baseController.getAppFrame().setupFrame(new ProjektSelectedSellHasBeerPanel(baseController));
                        break;

                    case "DanePiwa":
                        JComboBox beersBox = new JComboBox();
                        beersBox =baseController.getDataController().getMyDataBase().addAllClientsToComboBox(beersBox,"Beer");
                        JOptionPane.showMessageDialog(null,beersBox,"Wybierz piwo",JOptionPane.QUESTION_MESSAGE);
                        baseController.getDataController().getMyDataBase().searchSellHasBeerTable("DanePiwa",(String)beersBox.getSelectedItem());
                        baseController.getAppFrame().setupFrame(new ProjektSelectedSellHasBeerPanel(baseController));
                        break;
                }
            }
        });


        searchPanel.add(whichAtribuetLabel, new GBC(0,0));
        searchPanel.add(atributesCombo, new GBC(0, 1).setAnchor(GBC.WEST));
        searchPanel.add(searchBeerButton, new GBC(1, 3).setAnchor(GBC.EAST));


    }

}
