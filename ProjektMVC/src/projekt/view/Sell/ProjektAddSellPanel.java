package projekt.view.Sell;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;
import projekt.model.Product.ProductTableModel;
import projekt.model.ProjektCheckRecord;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 04.01.2017.
 */
public class ProjektAddSellPanel extends JPanel {
    private ProjektAppController baseController;
    private JTable productsTable;
    private double cenaOgolna;
    private JLabel cenaOgolnaLabel;


    public ProjektAddSellPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        this.productsTable = new JTable(baseController.getDataController().getMyDataBase().getProductsTableStart());


        setupProductsTable(productsTable);
        JScrollPane productsScrollPanel = new JScrollPane(productsTable);
        JPanel southPanel = new JPanel();
        makeSouthPanel(southPanel);
        cenaOgolna = checkTotalPrice();
        cenaOgolnaLabel = new JLabel("Cena ogolna wynosi: " + cenaOgolna + " zł");
        this.add(cenaOgolnaLabel,BorderLayout.EAST);
        this.add(new JLabel("Tabela Piwo", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(productsScrollPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

    }

    private void setupProductsTable(JTable productsTable) {
        setupColumnNameWidth(productsTable);

    }

    private void setupColumnNameWidth(JTable productsTable) {
        TableColumn column = null;
        for (int i = 0; i < productsTable.getColumnCount(); i++) {
            column = productsTable.getColumnModel().getColumn(i);
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

        JButton addProductButton = new JButton("Dodaj produkt");
        JButton removeProductButton = new JButton("Usuń wybrany produkt");
        JButton endPurchaseButton = new JButton("Zakończ zakup");
        addListeners(addProductButton, removeProductButton, endPurchaseButton);
        southPanel.add(addProductButton);
        southPanel.add(removeProductButton);
        southPanel.add(endPurchaseButton);

    }

    void addListeners(JButton addProductButton, JButton removeProductButton, JButton endPurchaseButton) {

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JComboBox products = new JComboBox<String>();
                products = baseController.getDataController().getMyDataBase().addAllClientsToComboBox(products,"Beer");
                if(products.getItemCount() == 0){
                    JOptionPane.showMessageDialog(baseController.getAppFrame().getSellPanel(),"Brak produktów!");
                } else {

                    JOptionPane.showMessageDialog(null,products,"Wybierz produkt:", JOptionPane.QUESTION_MESSAGE);
                    String text = (String)products.getSelectedItem();
                    String[] splitString = text.split(", ");
                    splitString[1] = splitString[1].replace("%","");
                    splitString[3] = splitString[3].replace(" zł","");
                    baseController.getDataController().getMyDataBase().addProductToProductTable(splitString[0],splitString[1],splitString[2],splitString[3]);
                    cenaOgolna +=  Double.parseDouble(splitString[3]);

                    cenaOgolnaLabel.setText("Cena ogólna wynosi: " + cenaOgolna + " zł");
                    revalidate();

                }
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = 1;
                int[] rows = productsTable.getSelectedRows();
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


                    for (int i = 0; i <rows.length; i++) {
                        cenaOgolna -= (double)(productsTable.getValueAt(rows[i],3));
                    }
                    baseController.getDataController().getMyDataBase().removeProductFromProductsTable(rows);

                    ProductTableModel model = baseController.getDataController().getMyDataBase().getProductsTable();

                    productsTable.setModel(model);
                    cenaOgolnaLabel.setText("Cena ogólna wynosi: " + cenaOgolna + " zł");
                    revalidate();


                } else {
                    revalidate();
                }

            }
        });


        endPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productsTable.getRowCount() == 0){
                    JOptionPane.showMessageDialog(baseController.getAppFrame(),"Wybierz co najmniej jesden produkt!");
                } else {

                    JPanel addClientAndSellerPanel = new JPanel();
                    JComboBox allClients = new JComboBox<String>();
                    JComboBox allSellers = new JComboBox<String>();
                    JTextField dataField = new JTextField(10);
                    allClients = baseController.getDataController().getMyDataBase().addAllClientsToComboBox(allClients,"Client");
                    allSellers = baseController.getDataController().getMyDataBase().addAllClientsToComboBox(allSellers, "Seller");
                    addClientAndSellerPanel.setLayout(new GridBagLayout());
                    addClientAndSellerPanel.add(new JLabel("Dodaj klienta:"),new GBC(0,0));
                    addClientAndSellerPanel.add(allClients, new GBC(0,1));
                    addClientAndSellerPanel.add(new JLabel("Dodaj sprzedawce:"),new GBC(0,2));
                    addClientAndSellerPanel.add(allSellers, new GBC(0,3));
                    addClientAndSellerPanel.add(new JLabel("Podaj date zakupu (YYYY-MM-DD"),new GBC(0,4));
                    addClientAndSellerPanel.add(dataField,new GBC(0,5));
                    JOptionPane.showMessageDialog(null,addClientAndSellerPanel,"Wybierz produkt:", JOptionPane.QUESTION_MESSAGE);
                    String getSelectedClient = (String)allClients.getSelectedItem();
                    String getSelectedSeller = (String)allSellers.getSelectedItem();
                    String getData = dataField.getText().trim();
                    System.out.println(getSelectedSeller);
                    if(ProjektCheckRecord.checkData(getData)){
                        baseController.getDataController().getMyDataBase().addSell(getSelectedClient,getSelectedSeller,getData,cenaOgolna);
                        int idSell = baseController.getDataController().getMyDataBase().findLastIdSell();
                        for(int i = 0; i < productsTable.getRowCount(); i++){
                            String name = (String)productsTable.getValueAt(i,0);
                            double procentAlkoholu = (double)productsTable.getValueAt(i,1);
                            String typ = (String)productsTable.getValueAt(i,2);
                            double cena = (double)productsTable.getValueAt(i,3);
                            String danePiwa = name + ", " + procentAlkoholu + "%, " + typ + ", " + cena + " zł";
                            int idBeer = baseController.getDataController().getMyDataBase().findLastIdBeerFromText(danePiwa);
                            baseController.getDataController().getMyDataBase().addSellHasBeer(idSell,idBeer,getSelectedClient,getSelectedSeller,danePiwa,getData);
                        }

                        baseController.getAppFrame().setupFrame(new ProjektSellPanel(baseController));
                    }else{
                        JOptionPane.showMessageDialog(baseController.getAppFrame(),"Zła data!");
                    }
                }

            }
        });

    }

    private double checkTotalPrice(){
        int totalPrize = 0;

        for(int i = 0; i < productsTable.getRowCount(); i++){
            System.out.println(productsTable.getValueAt(i,3));
            totalPrize += (double)productsTable.getValueAt(i,3);
        }
        return totalPrize;
    }

}
