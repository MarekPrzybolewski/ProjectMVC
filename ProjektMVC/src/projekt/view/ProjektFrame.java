package projekt.view;

import projekt.controller.ProjektAppController;
import projekt.view.Beer.ProjektBeerPanel;
import projekt.view.Beer.ProjektSearchBeerPanel;
import projekt.view.Client.ProjektAddClientPanel;
import projekt.view.Client.ProjektClientPanel;
import projekt.view.Client.ProjektSearchClientPanel;
import projekt.view.Sell.ProjektAddSellPanel;
import projekt.view.Sell.ProjektSellPanel;
import projekt.view.SellHasBeer.ProjektSearchSellHasBeerPanel;
import projekt.view.SellHasBeer.ProjektSellHasBeerPanel;
import projekt.view.Seller.ProjektAddSellerPanel;
import projekt.view.Seller.ProjektSearchSellerPanel;
import projekt.view.Seller.ProjektSellerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class ProjektFrame extends JFrame {

    private ProjektAppController baseController;
    private ProjektClientPanel basePanel;
    private ProjektClientPanel clientPanel;
    private ProjektAddClientPanel addClientPanel;
    private ProjektSearchClientPanel searchClientPanel;
    private ProjektAddSellerPanel addSellerPanel;
    private ProjektSearchSellerPanel searchSellerPanel;
    private ProjektSellerPanel sellerPanel;
    private ProjektBeerPanel beerPanel;
    private ProjektSearchBeerPanel searchBeerPanel;
    private ProjektSellPanel sellPanel;
    private ProjektAddSellPanel addSellPanel;
    private ProjektSellHasBeerPanel sellHasBeerPanel;
    private ProjektSearchSellHasBeerPanel searchSellHasBeerPanel;


    public ProjektFrame(ProjektAppController baseController){
        this.baseController = baseController;
        clientPanel = new ProjektClientPanel(baseController);
        addClientPanel = new ProjektAddClientPanel(baseController);
        searchClientPanel = new ProjektSearchClientPanel(baseController);
        sellerPanel = new ProjektSellerPanel(baseController);
        addSellerPanel = new ProjektAddSellerPanel(baseController);
        searchSellerPanel = new ProjektSearchSellerPanel(baseController);
        beerPanel = new ProjektBeerPanel(baseController);
        searchBeerPanel = new ProjektSearchBeerPanel(baseController);
        addSellPanel = new ProjektAddSellPanel(baseController);
        sellPanel = new ProjektSellPanel(baseController);
        sellHasBeerPanel = new ProjektSellHasBeerPanel(baseController);
        searchSellHasBeerPanel = new ProjektSearchSellHasBeerPanel(baseController);
        setupFrame(sellPanel);


    }

    public void setupFrame(JPanel panel){

        this.setTitle("Marek Przybolewski Projekt Java");
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu encjeMenu = new JMenu("Tabele");
        menuBar.add(encjeMenu);
        JMenuItem clientItem = new JMenuItem("Klient");
        JMenuItem sellerItem = new JMenuItem("Sprzedawca");
        JMenuItem beerItem = new JMenuItem("Piwo");
        JMenuItem sellItem = new JMenuItem("Zakup");
        JMenuItem sellHasBeerItem = new JMenuItem("Dokładne zakupy");
        encjeMenu.add(clientItem);
        encjeMenu.add(sellerItem);
        encjeMenu.add(beerItem);
        encjeMenu.add(sellItem);
        encjeMenu.add(sellHasBeerItem);

        clientItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFrame(new ProjektClientPanel(baseController));
            }
        });

        sellerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFrame(new ProjektSellerPanel(baseController));
            }
        });

        beerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFrame(new ProjektBeerPanel(baseController));
            }
        });

        sellItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFrame(new ProjektSellPanel(baseController));
            }
        });

        sellHasBeerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupFrame(new ProjektSellHasBeerPanel(baseController));
            }
        });
        this.setContentPane(panel);
        this.setSize(1000,500);
        this.setVisible(true);
    }

    public ProjektClientPanel getBasePanel() {
        return basePanel;
    }

    public ProjektAddClientPanel getAddClientPanel() {
        return addClientPanel;
    }

    public ProjektClientPanel getClientPanel() {
        return clientPanel;
    }

    public ProjektAppController getBaseController() {
        return baseController;
    }

    public ProjektSearchClientPanel getSearchClientPanel() {
        return searchClientPanel;
    }

    public ProjektAddSellerPanel getAddSellerPanel() {
        return addSellerPanel;
    }

    public ProjektSearchSellerPanel getSearchSellerPanel() {
        return searchSellerPanel;
    }

    public ProjektSellerPanel getSellerPanel() {
        return sellerPanel;
    }

    public ProjektBeerPanel getBeerPanel() {
        return beerPanel;
    }

    public ProjektSearchBeerPanel getSearchBeerPanel() {
        return searchBeerPanel;
    }

    public ProjektSellPanel getSellPanel() {
        return sellPanel;
    }

    public ProjektAddSellPanel getAddSellPanel() {
        return addSellPanel;
    }
}
