package projekt.view.Client;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;
import projekt.model.ProjektCheckRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 29.12.2016.
 */
public class ProjektAddClientPanel extends JPanel{
    private ProjektAppController baseController;
    private JPanel textFieldPanel;
    private TextField imie;
    private TextField nazwisko;
    private TextField email;
    private TextField pesel;

    private JLabel tempImieLabel = new JLabel("Złe imie");
    private JLabel tempNazwiskoLabel = new JLabel("Złe nazwisko");
    private JLabel tempPeselLabel = new JLabel("Zły pesel");
    private JLabel tempEmailLabel = new JLabel("Zły Email");



    public ProjektAddClientPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());

        addAllElementsToPanel();
        JButton addClientButton = new JButton("Dodaj klienta");
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFalseVisibleToLabels();
                String imieString = imie.getText().trim();
                String nazwiskoString = nazwisko.getText().trim();
                String peselString = pesel.getText().trim();
                String emailString = email.getText().trim();


                addRecord(imieString,nazwiskoString,peselString,emailString);
            }
        });
        this.add(new JLabel("Dodaj nowego klienta",SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(textFieldPanel,BorderLayout.CENTER);
        this.add(addClientButton,BorderLayout.SOUTH);

    }

    private void addAllElementsToPanel(){
        imie = new TextField(30);
        nazwisko = new TextField(30);
        pesel = new TextField(30);
        email  = new TextField(30);

        setFalseVisibleToLabels();
        setRedColorToLabels();

        textFieldPanel.add(new JLabel("Imie"), new GBC(0,0).setAnchor(GBC.EAST));
        textFieldPanel.add(imie,new GBC(1,0).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempImieLabel,new GBC(2,0).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Nazwisko"),new GBC(0,1).setAnchor(GBC.EAST));
        textFieldPanel.add(nazwisko,new GBC(1,1).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempNazwiskoLabel,new GBC(2,1).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Pesel"),new GBC(0,2).setAnchor(GBC.EAST));
        textFieldPanel.add(pesel,new GBC(1,2).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempPeselLabel,new GBC(2,2).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Email"),new GBC(0,3).setAnchor(GBC.EAST));
        textFieldPanel.add(email,new GBC(1,3).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempEmailLabel,new GBC(2,3).setInsets(5,10,0,0));


    }

    private void addRecord(String imieString,String nazwiskoString, String peselString, String emailString){
        boolean imieCheck = false, nazwiskoCheck = false, peselCheck = false, emailCheck = false;


        if(ProjektCheckRecord.checkImie(imieString)) {
            imieCheck = true;
        }

        if(ProjektCheckRecord.checkNazwisko(nazwiskoString)) {
            nazwiskoCheck = true;
        }

        if(ProjektCheckRecord.checkPesel(peselString)) {
            peselCheck = true;
        }

        if(ProjektCheckRecord.checkEmail(emailString)){
            emailCheck = true;
        }

        if(!imieCheck){
            tempImieLabel.setVisible(true);

        }

        if(!nazwiskoCheck){
            tempNazwiskoLabel.setVisible(true);
        }

        if(!peselCheck){
            tempPeselLabel.setVisible(true);
        }

        if(!emailCheck){
            tempEmailLabel.setVisible(true);
        }

        revalidate();


        checkIsAllAtributesCorrect(imieCheck,nazwiskoCheck,peselCheck,emailCheck,imieString,nazwiskoString,peselString,emailString);
    }

    private void checkIsAllAtributesCorrect(boolean imieCheck, boolean nazwiskoCheck, boolean peselCheck, boolean emailCheck, String imieString, String nazwiskoString, String peselString, String emailString){
        if(imieCheck && nazwiskoCheck && peselCheck && emailCheck){
            imieString = imieString.substring(0,1).toUpperCase()+imieString.substring(1).toLowerCase();
            nazwiskoString = nazwiskoString.substring(0,1).toUpperCase()+nazwiskoString.substring(1).toLowerCase();
            baseController.getDataController().getMyDataBase().addClient(
                    imieString,
                    nazwiskoString,
                    peselString,
                    emailString);
            baseController.getAppFrame().setupFrame(new ProjektClientPanel(baseController));
        }
    }

    private void setFalseVisibleToLabels(){
        tempImieLabel.setVisible(false);
        tempNazwiskoLabel.setVisible(false);
        tempPeselLabel.setVisible(false);
        tempEmailLabel.setVisible(false);

    }

    private void setRedColorToLabels(){
        tempImieLabel.setForeground(Color.RED);
        tempNazwiskoLabel.setForeground(Color.RED);
        tempPeselLabel.setForeground(Color.RED);
        tempEmailLabel.setForeground(Color.RED);

    }
}
