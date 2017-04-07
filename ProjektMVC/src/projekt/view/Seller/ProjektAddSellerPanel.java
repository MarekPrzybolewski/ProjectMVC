package projekt.view.Seller;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;
import projekt.model.ProjektCheckRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class ProjektAddSellerPanel extends JPanel {
    private ProjektAppController baseController;
    private JPanel textFieldPanel;
    private TextField imie;
    private TextField nazwisko;
    private TextField pensja;
    private TextField email;
    private TextField pesel;
    private JLabel tempImieLabel = new JLabel("Złe imie");
    private JLabel tempNazwiskoLabel = new JLabel("Złe nazwisko");
    private JLabel tempPensjaLabel = new JLabel("Zła pensja");
    private JLabel tempPeselLabel = new JLabel("Zły pesel");
    private JLabel tempEmailLabel = new JLabel("Zły Email");

    public ProjektAddSellerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());

        addAllElementsToPanel();
        JButton addSellerButton = new JButton("Dodaj sprzedawce");
        addSellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFalseVisibleToLabels();
                String imieString = imie.getText().trim();
                String nazwiskoString = nazwisko.getText().trim();
                String pensjaString = pensja.getText().trim();
                String peselString = pesel.getText().trim();
                String emailString = email.getText().trim();
                /*
                String ulicaString = ulica.getText().trim();
                String numerString = numer.getText().trim();
                String miejscowoscString = miejscowosc.getText().trim();
                String kodString = kod.getText().trim();
                */

                addRecord(imieString,nazwiskoString,pensjaString, peselString,emailString);
            }
        });
        this.add(new JLabel("Dodaj nowego Sprzedawce",SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(textFieldPanel,BorderLayout.CENTER);
        this.add(addSellerButton,BorderLayout.SOUTH);
    }

    private void addAllElementsToPanel(){
        imie = new TextField(30);
        nazwisko = new TextField(30);
        pensja = new TextField(10);
        pesel = new TextField(30);
        email  = new TextField(30);
        /*
        ulica = new TextField(30);
        numer = new TextField(30);
        miejscowosc = new TextField(30);
        kod = new TextField(6);
        */

        setFalseVisibleToLabels();
        setRedColorToLabels();

        textFieldPanel.add(new JLabel("Imie"), new GBC(0,0).setAnchor(GBC.EAST));
        textFieldPanel.add(imie,new GBC(1,0).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempImieLabel,new GBC(2,0).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Nazwisko"),new GBC(0,1).setAnchor(GBC.EAST));
        textFieldPanel.add(nazwisko,new GBC(1,1).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempNazwiskoLabel,new GBC(2,1).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Pensja"),new GBC(0,2).setAnchor(GBC.EAST));
        textFieldPanel.add(pensja,new GBC(1,2).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempPensjaLabel,new GBC(2,2).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Pesel"),new GBC(0,3).setAnchor(GBC.EAST));
        textFieldPanel.add(pesel,new GBC(1,3).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempPeselLabel,new GBC(2,3).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Email"),new GBC(0,4).setAnchor(GBC.EAST));
        textFieldPanel.add(email,new GBC(1,4).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempEmailLabel,new GBC(2,4).setInsets(5,10,0,0));

        /*
        textFieldPanel.add(new JLabel("Ulica"), new GBC(0,4).setAnchor(GBC.EAST));
        textFieldPanel.add(ulica,new GBC(1,4).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempUlicaLabel,new GBC(2,4).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Numer"), new GBC(0,5).setAnchor(GBC.EAST));
        textFieldPanel.add(numer,new GBC(1,5).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempNumerLabel,new GBC(2,5).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Miejscowość"), new GBC(0,6).setAnchor(GBC.EAST));
        textFieldPanel.add(miejscowosc,new GBC(1,6).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempMiejscowoscLabel,new GBC(2,6).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Kod"), new GBC(0,7).setAnchor(GBC.EAST));
        textFieldPanel.add(kod,new GBC(1,7).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempKodLabel,new GBC(2,7).setInsets(5,10,0,0));

        */
    }


    private void addRecord(String imieString,String nazwiskoString, String pensjaString, String peselString, String emailString){
        boolean imieCheck = false, nazwiskoCheck = false, pensjaCheck = false, peselCheck = false, emailCheck = false;
        //boolean ulicaCheck = false, numerCheck = false, miejscowoscCheck = false, kodCheck = false;


        if(ProjektCheckRecord.checkImie(imieString)) {
            imieCheck = true;
        }

        if(ProjektCheckRecord.checkNazwisko(nazwiskoString)) {
            nazwiskoCheck = true;
        }

        if(ProjektCheckRecord.checkPensja(pensjaString)){
            pensjaCheck = true;
        }

        if(ProjektCheckRecord.checkPesel(peselString)) {
            peselCheck = true;
        }

        if(ProjektCheckRecord.checkEmail(emailString)){
            emailCheck = true;
        }
/*
        if(ProjektCheckRecord.checkUlica(ulicaString)){
            ulicaCheck = true;
        }

        if(ProjektCheckRecord.checkNumer(numerString)){
            numerCheck = true;
        }

        if(ProjektCheckRecord.checkMiejscowosc(miejscowoscString)){
            miejscowoscCheck = true;
        }

        if(ProjektCheckRecord.checkKod(kodString)){
            kodCheck = true;
        }
*/
        if(!imieCheck){
            tempImieLabel.setVisible(true);

        }

        if(!nazwiskoCheck){
            tempNazwiskoLabel.setVisible(true);
        }


        if(!pensjaCheck){
            tempPensjaLabel.setVisible(true);
        }

        if(!peselCheck){
            tempPeselLabel.setVisible(true);
        }


        if(!emailCheck){
            tempEmailLabel.setVisible(true);
        }
/*
        if(!ulicaCheck){
            tempUlicaLabel.setVisible(true);
        }

        if(!numerCheck){
            tempNumerLabel.setVisible(true);
        }

        if(!miejscowoscCheck){
            tempMiejscowoscLabel.setVisible(true);
        }

        if(!kodCheck){
            tempKodLabel.setVisible(true);
        }
*/
        revalidate();


        checkIsAllAtributesCorrect(imieCheck,nazwiskoCheck,pensjaCheck,peselCheck,emailCheck,imieString,nazwiskoString,pensjaString,peselString,emailString);
    }

    private void checkIsAllAtributesCorrect(boolean imieCheck, boolean nazwiskoCheck,boolean pensjaCheck, boolean peselCheck, boolean emailCheck, String imieString, String nazwiskoString,String pensjaString, String peselString, String emailString){
        if(imieCheck && nazwiskoCheck && pensjaCheck && peselCheck && emailCheck){
            pensjaString = pensjaString.replaceAll(",",".");
            imieString = imieString.substring(0,1).toUpperCase()+imieString.substring(1).toLowerCase();
            nazwiskoString = nazwiskoString.substring(0,1).toUpperCase()+nazwiskoString.substring(1).toLowerCase();
            baseController.getDataController().getMyDataBase().addSeller(
                    imieString,
                    nazwiskoString,
                    Double.parseDouble(pensjaString),
                    peselString,
                    emailString);
            baseController.getAppFrame().setupFrame(new ProjektSellerPanel(baseController));
        }
    }

    private void setFalseVisibleToLabels(){
        tempImieLabel.setVisible(false);
        tempNazwiskoLabel.setVisible(false);
        tempPensjaLabel.setVisible(false);
        tempPeselLabel.setVisible(false);
        tempEmailLabel.setVisible(false);
        /*
        tempUlicaLabel.setVisible(false);
        tempNumerLabel.setVisible(false);
        tempMiejscowoscLabel.setVisible(false);
        tempKodLabel.setVisible(false);
        */
    }

    private void setRedColorToLabels(){
        tempImieLabel.setForeground(Color.RED);
        tempNazwiskoLabel.setForeground(Color.RED);
        tempPensjaLabel.setForeground(Color.RED);
        tempPeselLabel.setForeground(Color.RED);
        tempEmailLabel.setForeground(Color.RED);
        /*
        tempUlicaLabel.setForeground(Color.RED);
        tempNumerLabel.setForeground(Color.RED);
        tempMiejscowoscLabel.setForeground(Color.RED);
        tempKodLabel.setForeground(Color.RED);
        */
    }

}
