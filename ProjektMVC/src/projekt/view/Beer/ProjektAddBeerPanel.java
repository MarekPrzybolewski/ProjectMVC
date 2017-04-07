package projekt.view.Beer;

import projekt.controller.ProjektAppController;
import projekt.model.GBC;
import projekt.model.ProjektCheckRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 03.01.2017.
 */
public class ProjektAddBeerPanel extends JPanel{
    private ProjektAppController baseController;
    private JPanel textFieldPanel;
    private TextField nazwa;
    private TextField procentAlkoholu;
    private TextField typPiwa;
    private TextField cena;
    private JLabel tempNazwaLabel = new JLabel("Zła nazwa");
    private JLabel tempProcentAlkoholuLabel = new JLabel("Zły procent");
    private JLabel tempTypPiwaLabel = new JLabel("Zły typ piwa");
    private JLabel tempCenaLabel = new JLabel("Zła cena");

    public ProjektAddBeerPanel(ProjektAppController baseController) {
        this.baseController = baseController;
        this.setLayout(new BorderLayout());

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridBagLayout());

        addAllElementsToPanel();
        JButton addBeerButton = new JButton("Dodaj piwo");
        addBeerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFalseVisibleToLabels();
                String nazwaString = nazwa.getText().trim();
                String procentAlkoholuString = procentAlkoholu.getText().trim();
                String typPiwaString = typPiwa.getText().trim();
                String cenaString = cena.getText().trim();

                addRecord(nazwaString,procentAlkoholuString,typPiwaString, cenaString);
            }
        });
        this.add(new JLabel("Dodaj nowe Piwo",SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(textFieldPanel,BorderLayout.CENTER);
        this.add(addBeerButton,BorderLayout.SOUTH);
    }

    private void addAllElementsToPanel(){
        nazwa = new TextField(20);
        procentAlkoholu = new TextField(10);
        typPiwa = new TextField(20);
        cena = new TextField(10);



        setFalseVisibleToLabels();
        setRedColorToLabels();

        textFieldPanel.add(new JLabel("Nazwa"), new GBC(0,0).setAnchor(GBC.EAST));
        textFieldPanel.add(nazwa,new GBC(1,0).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempNazwaLabel,new GBC(2,0).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Procent Alkoholu"),new GBC(0,1).setAnchor(GBC.EAST));
        textFieldPanel.add(procentAlkoholu,new GBC(1,1).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempProcentAlkoholuLabel,new GBC(2,1).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Typ Piwa"),new GBC(0,2).setAnchor(GBC.EAST));
        textFieldPanel.add(typPiwa,new GBC(1,2).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempTypPiwaLabel,new GBC(2,2).setInsets(5,10,0,0));

        textFieldPanel.add(new JLabel("Cena"),new GBC(0,3).setAnchor(GBC.EAST));
        textFieldPanel.add(cena,new GBC(1,3).setAnchor(GBC.WEST).setInsets(5,15,0,0));
        textFieldPanel.add(tempCenaLabel,new GBC(2,3).setInsets(5,10,0,0));

    }

    private void addRecord(String nazwaString,String procentAlkoholuString, String typPiwaString, String cenaString){
        boolean nazwaCheck = false, procentAlkoholuCheck = false, typPiwaCheck = false, cenaCheck = false;

        if(ProjektCheckRecord.checkNazwa(nazwaString)) {
            nazwaCheck = true;
        }

        if(ProjektCheckRecord.checkProcentAlkoholu(procentAlkoholuString)) {
            procentAlkoholuCheck = true;
        }

        if(ProjektCheckRecord.checkTypPiwa(typPiwaString)){
            typPiwaCheck = true;
        }

        if(ProjektCheckRecord.checkCena(cenaString)) {
            cenaCheck = true;
        }

        if(!nazwaCheck){
            tempNazwaLabel.setVisible(true);

        }

        if(!procentAlkoholuCheck){
            tempProcentAlkoholuLabel.setVisible(true);
        }


        if(!typPiwaCheck){
            tempTypPiwaLabel.setVisible(true);
        }

        if(!cenaCheck){
            tempCenaLabel.setVisible(true);
        }

        revalidate();


        checkIsAllAtributesCorrect(nazwaCheck,procentAlkoholuCheck,typPiwaCheck,cenaCheck,nazwaString,procentAlkoholuString,typPiwaString,cenaString);
    }

    private void checkIsAllAtributesCorrect(boolean nazwaCheck, boolean procentAlkoholuCheck,boolean typPiwaCheck, boolean cenaCheck, String nazwaString, String procentAlkoholuString,String typPiwaString, String cenaString){
        if(nazwaCheck && procentAlkoholuCheck && typPiwaCheck && cenaCheck){
            procentAlkoholuString = procentAlkoholuString.replaceAll(",",".");
            cenaString = cenaString.replaceAll(",",".");

            nazwaString =  nazwaString.substring(0,1).toUpperCase()+nazwaString.substring(1).toLowerCase();
            typPiwaString = typPiwaString.substring(0,1).toUpperCase()+typPiwaString.substring(1).toLowerCase();
            baseController.getDataController().getMyDataBase().addBeer(
                    nazwaString,
                    Double.parseDouble(procentAlkoholuString),
                    typPiwaString,
                    Double.parseDouble(cenaString));
            baseController.getAppFrame().setupFrame(new ProjektBeerPanel(baseController));
        }
    }

    private void setFalseVisibleToLabels(){
        tempNazwaLabel.setVisible(false);
        tempProcentAlkoholuLabel.setVisible(false);
        tempTypPiwaLabel.setVisible(false);
        tempCenaLabel.setVisible(false);
    }

    private void setRedColorToLabels(){
        tempNazwaLabel.setForeground(Color.RED);
        tempProcentAlkoholuLabel.setForeground(Color.RED);
        tempTypPiwaLabel.setForeground(Color.RED);
        tempCenaLabel.setForeground(Color.RED);
    }

}
