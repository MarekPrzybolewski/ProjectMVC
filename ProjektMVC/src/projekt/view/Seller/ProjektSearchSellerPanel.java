package projekt.view.Seller;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class ProjektSearchSellerPanel extends JPanel {

    private ProjektAppController baseController;
    private JComboBox<String> atributesCombo;
    private JPanel searchPanel;
    private String column = "Imie";

    public ProjektSearchSellerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());
        searchPanel = new JPanel();
        this.add(searchPanel, BorderLayout.CENTER);
        searchPanel.setLayout(new GridBagLayout());

        JLabel whichAtribuetLabel = new JLabel("Po którym atrybucie chesz wyszukać ?");

        atributesCombo = new JComboBox<>();
        atributesCombo.addItem("Imie");
        atributesCombo.addItem("Nazwisko");
        atributesCombo.addItem("Pensja");
        atributesCombo.addItem("Pesel");
        atributesCombo.addItem("Email");



        JTextField valueText = new JTextField(30);
        JLabel valueLabel = new JLabel("Podaj wartość");
        JButton searchSellerButton = new JButton("Wyszukaj");


        searchSellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = valueText.getText().trim();
                if(column == "Pensja"){
                    value = value.replaceAll(",",".");
                    value = value.replace(" ",".");
                }
                if(column == "Imie" || column == "Nazwisko"){
                    value = value.substring(0,1).toUpperCase()+value.substring(1).toLowerCase();
                }
                baseController.getDataController().getMyDataBase().searchSeller(column, value);
                baseController.getAppFrame().setupFrame(new ProjektSelectedSellerPanel(baseController));
            }
        });


        atributesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                column = atributesCombo.getItemAt(atributesCombo.getSelectedIndex());
                System.out.println(column);
            }
        });

        searchPanel.add(whichAtribuetLabel, new GBC(0,0));
        searchPanel.add(valueLabel, new GBC(0, 1));
        searchPanel.add(atributesCombo, new GBC(1, 0));
        searchPanel.add(valueText, new GBC(1, 1));
        searchPanel.add(searchSellerButton, new GBC(0, 2));


    }
}

