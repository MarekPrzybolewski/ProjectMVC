package projekt.model;

import projekt.controller.ProjektDataController;
import projekt.model.Beer.Beer;
import projekt.model.Beer.BeerTableModel;
import projekt.model.Beer.SearchBeerTableModel;
import projekt.model.Client.Client;
import projekt.model.Client.ClientTableModel;
import projekt.model.Client.SearchClientTableModel;
import projekt.model.Product.Product;
import projekt.model.Product.ProductTableModel;
import projekt.model.Sell.Sell;
import projekt.model.Sell.SellTableModel;
import projekt.model.SellHasBeer.SellHasBeer;
import projekt.model.SellHasBeer.SellHasBeerTableModel;
import projekt.model.Seller.SearchSellerTableModel;
import projekt.model.Seller.Seller;
import projekt.model.Seller.SellerTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class ProjektDataBase {

    private ProjektDataController dataBaseController;
    private ClientTableModel clientsTable;
    private SellerTableModel sellersTable;
    private BeerTableModel beersTable;
    private SearchClientTableModel searchClientTableModel;
    private SearchSellerTableModel searchSellerTableModel;
    private SearchBeerTableModel searchBeerTableModel;
    private SellTableModel sellTable;
    private SellTableModel searchSellTableModel;
    private ProductTableModel productsTable;
    private SellHasBeerTableModel sellHasBeerTable;
    private SellHasBeerTableModel searchSellHasBeerTable;

    public ProjektDataBase(ProjektDataController dataBaseController) {
        this.dataBaseController = dataBaseController;
        this.clientsTable = new ClientTableModel(getDataBaseController());
        this.sellersTable = new SellerTableModel(getDataBaseController());
        this.searchClientTableModel = new SearchClientTableModel(getDataBaseController());
        this.searchSellerTableModel = new SearchSellerTableModel(getDataBaseController());
        this.beersTable = new BeerTableModel(getDataBaseController());
        this.searchBeerTableModel = new SearchBeerTableModel(getDataBaseController());
        this.sellTable = new SellTableModel(getDataBaseController());
        this.searchSellTableModel =  new SellTableModel(getDataBaseController());
        this.productsTable = new ProductTableModel(getDataBaseController());
        this.sellHasBeerTable = new SellHasBeerTableModel(getDataBaseController());
        this.searchSellHasBeerTable = new SellHasBeerTableModel(getDataBaseController());

        makeDataBase();

    }

    private void makeDataBase(){
        makeClientTable();
        createClientsTable();
        makeSellerTable();
        createSellersTable();
        makeBeerTable();
        createBeerTable();
        makeSellTable();
        createSellTable();
        makeSellHasBeerTable();
        createSellHasBeerTable();
    }

    private void makeAdresTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Adres(" +
                "IdAdres INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Ulica VARCHAR(40) NOT NULL," +
                "Numer VARCHAR(10) NOT NULL," +
                "Miejscowosc VARCHAR(40) NOT NULL," +
                "Kod VARCHAR(6) NOT NULL )";
        try{
            dataBaseController.getStmt().execute(sql);
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    private void makeClientTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Client(" +
                "IdClient INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Imie VARCHAR(20) NOT NULL," +
                "Nazwisko VARCHAR(30) NOT NULL," +
                "Pesel VARCHAR(11) NOT NULL UNIQUE," +
                "Email VARCHAR(30) NOT NULL UNIQUE)";
        try{
            dataBaseController.getStmt().execute(sql);
        } catch (SQLException e) {
            dataBaseController.displayError(e);
        }

    }

    private void makeSellerTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Seller("+
                "IdSeller INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Imie VARCHAR(20) NOT NULL," +
                "Nazwisko VARCHAR(30) NOT NULL, " +
                "Pensja MONEY NOT NULL, " +
                "Pesel VARCHAR(11) UNIQUE, " +
                "Email VARCHAR NOT NULL UNIQUE)";
        try{
            dataBaseController.getStmt().execute(sql);
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    private void makeBeerTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Beer(" +
                "IdBeer INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "Nazwa VARCHAR(20) NOT NULL UNIQUE," +
                "ProcentAlkoholu DOUBLE NOT NULL," +
                "TypPiwa VARCHAR(20) NOT NULL," +
                "Cena MONEY NOT NULL )";
        try{
            dataBaseController.getStmt().execute(sql);
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);

        }
    }

    private void makeSellTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Sell(" +
                "IdSell INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "DaneKlienta VARCHAR(80) NOT NULL," +
                "DaneSprzedawcy VARCHAR(80) NOT NULL,"+
                "Data DATE NOT NULL," +
                "KosztCalkowity NOT NULL )";
        try{
            dataBaseController.getStmt().execute(sql);
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);

        }
    }


    private void makeSellHasBeerTable(){
            String sql = "CREATE TABLE IF NOT EXISTS SellHasBeer(" +
                    "IdSell INTEGER," +
                    "IdBeer INTEGER," +
                    "DaneKlienta VARCHAR(80) NOT NULL," +
                    "DaneSprzedawcy VARCHAR(80) NOT NULL," +
                    "DanePiwa VARCHAR(80) NOT NULL," +
                    "Data DATE NOT NULL, " +
                    "FOREIGN KEY(IdSell) REFERENCES Sell(IdSell)," +
                    "FOREIGN KEY(IdBeer) REFERENCES Beer(IdBeer))";

            try{
                dataBaseController.getStmt().execute(sql);
            } catch (SQLException currentException) {
                dataBaseController.displayError(currentException);
            }
    }


    public void createAdresTable(){
        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM Adres WHERE IdAdres = 1");
            while(result.next()){
            }
        } catch (SQLException currentException){

        }
    }
    public void createClientsTable(){
        this.clientsTable = new ClientTableModel(dataBaseController);

        try {
            ResultSet result = getDataBaseController().getStmt().executeQuery("SELECT * FROM Client");

            while(result.next()) {
                int idClient = result.getInt("IdClient");
                String imie = result.getString("Imie");
                String nazwisko = result.getString("Nazwisko");
                String pesel = result.getString("Pesel");
                String email = result.getString("Email");

                Client temp =new Client(idClient,imie,nazwisko,pesel,email);
                getClientsTable().addClient(temp);
            }
        } catch (SQLException e) {
            dataBaseController.displayError(e);
        }
    }


    public void createSellersTable(){
        this.sellersTable = new SellerTableModel(dataBaseController);

        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM Seller");

            while(result.next()){
                int idSeller = result.getInt("IdSeller");
                String imie = result.getString("Imie");
                String nazwisko = result.getString("Nazwisko");
                double pensja = result.getDouble("Pensja");
                String pesel = result.getString("Pesel");
                String email = result.getString("Email");

                Seller tempSeller = new Seller(idSeller,imie,nazwisko,pensja,pesel,email);
                sellersTable.addSeller(tempSeller);
            }
        } catch(SQLException currentException){

            dataBaseController.displayError(currentException);
        }
    }

    public void createBeerTable(){
        this.beersTable = new BeerTableModel(dataBaseController);

        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM Beer");

            while(result.next()){
                int idBeer = result.getInt("IdBeer");
                String nazwa = result.getString("Nazwa");
                Double procentAlkoholu = result.getDouble("ProcentAlkoholu");
                String typPiwa = result.getString("TypPiwa");
                Double cena = result.getDouble("Cena");

                Beer tempBeer = new Beer(idBeer,nazwa,procentAlkoholu,typPiwa,cena);
                beersTable.addBeer(tempBeer);
            }
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);

        }
    }

    public void createSellTable(){
        this.sellTable = new SellTableModel(dataBaseController);

        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM Sell");
            while(result.next()){
                int idSell = result.getInt("IdSell");
                String daneKlienta = result.getString("DaneKlienta");
                String daneSprzedawcy = result.getString("DaneSprzedawcy");
                String data = result.getString("Data");
                double kosztCalkowity = result.getDouble("KosztCalkowity");
                Sell tempSell = new Sell(idSell,daneKlienta,daneSprzedawcy,data,kosztCalkowity);
                sellTable.addSell(tempSell);
            }
        } catch(SQLException currentException){

            dataBaseController.displayError(currentException);
        }

    }

    public void createSellHasBeerTable(){
        this.sellHasBeerTable = new SellHasBeerTableModel(dataBaseController);

        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM SellHasBeer");
            while(result.next()){
                int idSell = result.getInt("IdSell");
                int idBeer = result.getInt("IdBeer");
                String daneKlienta = result.getString("DaneKlienta");
                String daneSprzedawcy = result.getString("DaneSprzedawcy");
                String danePiwa = result.getString("DanePiwa");
                String data = result.getString("Data");

                SellHasBeer tempSellHasBeer = new SellHasBeer(idSell,idBeer,daneKlienta,daneSprzedawcy,danePiwa,data);
                sellHasBeerTable.addSellHasBeer(tempSellHasBeer);
            }
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void addClient(String imie, String nazwisko, String pesel, String email){
        String sql;

        try{
            sql = "INSERT INTO Client (imie,nazwisko,pesel,email) " +
                    "VALUES ('" + imie + "', '" + nazwisko + "', '" + pesel + "', '" + email+ "')";
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch (SQLException currentException){
            if(IsAtributesUnique(currentException)){
                dataBaseController.displayError(currentException);
            }

        }
    }

    public void addSeller(String imie, String nazwisko, Double pensja, String pesel, String email){
        String sql;
        try {
            sql = "INSERT INTO Seller (imie,nazwisko,pensja,pesel,email) " +
                    "VALUES ('" + imie + "', '" + nazwisko + "', " + pensja + ", '" + pesel + "', '" + email +"')";
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            if(IsAtributesUnique(currentException)){
                dataBaseController.displayError(currentException);
            }
        }
    }

    public void addBeer(String nazwa,double procentAlkoholu, String typPiwa, double cena){
        String sql;
        try {
            sql = "INSERT INTO Beer (nazwa,procentAlkoholu,typPiwa,cena) " +
                    "VALUES ('" + nazwa + "'," + procentAlkoholu + ",'" + typPiwa + "', " + cena + ")";
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            if(IsAtributesUnique(currentException)){
                dataBaseController.displayError(currentException);
            }
        }
    }

    public void addSell(String daneKlienta, String daneSprzedawcy, String data, double kosztCalkowity){
        String sql;
        try{
            sql = "INSERT INTO Sell (daneKlienta,daneSprzedawcy,data,kosztCalkowity) " +
                    "VALUES ('" + daneKlienta + "','" + daneSprzedawcy + "','" + data + "'," + kosztCalkowity + ")";
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void addSellHasBeer(int idSell, int idBeer,String daneKlienta,String daneSprzedawcy, String danePiwa, String data){
        String sql;
        try{
            sql = "INSERT INTO SellHasBeer(idSell,idBeer,daneKlienta,daneSprzedawcy,danePiwa,data) " +
                    "VALUES ( " + idSell + "," + idBeer + ", '" + daneKlienta + "', '" + daneSprzedawcy + "', '" + danePiwa + "', '" + data + "')";
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException) {
            dataBaseController.displayError(currentException);
        }
    }

    public void addProductToProductTable(String nazwa, String procentAlkoholu, String typPiwa, String cena){
        double procent = Double.parseDouble(procentAlkoholu);
        double cenaPiwa = Double.parseDouble(cena);
        productsTable.addProduct(new Product(nazwa,procent,typPiwa,cenaPiwa));

    }

    public void updateClientTable(String columnName,int idClient, Object data){
        String sql;
        try{
            sql = "UPDATE Client" +
                    " SET " + columnName + " = '" + data + "'" +
                    " WHERE IdClient = " + idClient;

            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void updateSellerTable(String columnName,int idSeller, Object data){
        String sql;
        try{
            sql = "UPDATE Seller" +
                    " SET " + columnName + " = '" + data + "'" +
                    " WHERE IdSeller = " + idSeller;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void updateBeerTable(String columnName,int idBeer,Object data){
        String sql;

        try{
            if(columnName == "ProcentAlkoholu" || columnName == "Cena"){
                sql = "UPDATE Beer" +
                        " SET " + columnName + " = " + data + " " +
                        "WHERE IdBeer = "    + idBeer;
            }
            else{

                sql = "UPDATE Beer " +
                        " SET " + columnName + " = '" + data + "'" +
                        "WHERE IdBeer = "    + idBeer;
            }
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void updateSellTable(String columnName,int idSell, Object data){
        String sql;

        try{
            sql = "UPDATE Sell " +
                    "SET " + columnName + " = '" + data + "'" +
                    " WHERE IdSell = " + idSell;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }


    public void updateAdresTable(String columnName, int idAdres, Object data){
        String sql;
        try{
            sql = "UPDATE Adres" +
                    " SET " + columnName + " = '" + data + "'" +
                    " WHERE IdAdres = " + idAdres;
            dataBaseController.getStmt().execute(sql);
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void removeClient(int idClient){
        String sql;
        try{
            sql = "DELETE FROM Client " +
                    "WHERE IdClient = " + idClient;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        }catch (SQLException currentExcepion){
            dataBaseController.displayError(currentExcepion);
        }
    }

    public void removeSeller(int idSeller){
        String sql;
        try{
            sql = "DELETE FROM Seller " +
                    "WHERE IdSeller = " + idSeller;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void removeBeer(int idBeer){
        String sql;
        try{
            sql = "DELETE FROM Beer " +
                    "WHERE IdBeer = " + idBeer;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);

        }
    }


    public void removeSell(int idSell){
        String sql;
        try{
            sql = "DELETE FROM SellHasBeer " +
                    "WHERE IdSell = " + idSell;
            dataBaseController.getStmt().execute(sql);
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }

        try{
            sql = "DELETE FROM Sell " +
                    " WHERE IdSell = " + idSell;
            dataBaseController.getStmt().execute(sql);
            makeDataBase();
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }



    private boolean IsAtributesUnique(SQLException currentException){
        if(currentException.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column Pesel is not unique)")){
            JOptionPane.showMessageDialog(dataBaseController.getBaseController().getAppFrame().getAddClientPanel(), "Jest już taki pesel w bazie danych!");
            return false;
        } else {
            if (currentException.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column Email is not unique)")) {
                JOptionPane.showMessageDialog(dataBaseController.getBaseController().getAppFrame().getAddClientPanel(), "Jest już taki email w bazie danych!");
                return false;
            }
            else {
                if(currentException.getMessage().equals("[SQLITE_CONSTRAINT]  Abort due to constraint violation (column Nazwa is not unique)")){
                    JOptionPane.showMessageDialog(dataBaseController.getBaseController().getAppFrame(), "Jest już taka nazwa w bazie danych!");
                    return false;
                }
                return true;
            }

        }

    }

    public void searchClient(String column,String value){
        searchClientTableModel = new SearchClientTableModel(dataBaseController);

        try {
            ResultSet result = dataBaseController.getStmt().executeQuery("Select * FROM Client WHERE " + column + " = '" + value + "' ");
            while (result.next()) {
                int idClient = result.getInt("IdClient");
                String imie = result.getString("Imie");
                String nazwisko = result.getString("Nazwisko");
                String pesel = result.getString("Pesel");
                String email = result.getString("Email");
                Client tempClient = new Client(idClient, imie, nazwisko, pesel, email);
                searchClientTableModel.addClient(tempClient);

            }
        }
        catch (SQLException currentException) {
            dataBaseController.displayError(currentException);

        }
    }

    public void searchSeller(String column, String value){
        searchSellerTableModel = new SearchSellerTableModel(dataBaseController);

        try {
            ResultSet result;
            if (column == "Pensja") {
                result = dataBaseController.getStmt().executeQuery("SELECT * FROM Seller WHERE Pensja = " + value);
            }else{
                result = dataBaseController.getStmt().executeQuery("Select * FROM Seller WHERE " + column + " = '" + value + "' ");
            }

            while (result.next()) {
                int idClient = result.getInt("IdSeller");
                String imie = result.getString("Imie");
                String nazwisko = result.getString("Nazwisko");
                double pensja = result.getDouble("Pensja");
                String pesel = result.getString("Pesel");
                String email = result.getString("Email");
                Seller tempSeller = new Seller(idClient, imie, nazwisko,pensja, pesel, email);
                searchSellerTableModel.addSeller(tempSeller);

            }
        }
        catch (SQLException currentException) {
            dataBaseController.displayError(currentException);

        }

    }

    public void searchBeer(String column, String value){
        searchBeerTableModel = new SearchBeerTableModel(dataBaseController);

        try {
            ResultSet result = dataBaseController.getStmt().executeQuery("Select * FROM Beer WHERE " + column + " = '" + value + "' ");
            while (result.next()) {
                int idBeer = result.getInt("IdBeer");
                String nazwa = result.getString("Nazwa");
                double procentAlkoholu = result.getDouble("ProcentAlkoholu");
                String typPiwa = result.getString("TypPiwa");
                double cena = result.getDouble("Cena");

                Beer tempBeer = new Beer(idBeer, nazwa, procentAlkoholu,typPiwa, cena);
                searchBeerTableModel.addBeer(tempBeer);

            }
        }
        catch (SQLException currentException) {
            dataBaseController.displayError(currentException);

        }
    }

    public void searchSell(String column, String value){
        searchSellTableModel = new SellTableModel(dataBaseController);

        try{
            ResultSet result;
            if(column == "KosztCalkowity"){
                 result = dataBaseController.getStmt().executeQuery("SELECT * FROM Sell WHERE KosztCalkowity = " + value);
            } else {
                 result = dataBaseController.getStmt().executeQuery("SELECT * FROM Sell WHERE " + column + " = '" + value + "' ");
            }
            while(result.next()) {
                int idSell = result.getInt("IdSell");
                String daneKlienta = result.getString(("DaneKlienta"));
                String daneSprzedawcy = result.getString("DaneSprzedawcy");
                String data = result.getString("Data");
                double kosztCalkowity = result.getDouble("KosztCalkowity");

                Sell tempSell = new Sell(idSell, daneKlienta, daneSprzedawcy, data, kosztCalkowity);
                searchSellTableModel.addSell(tempSell);
            }
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public void searchSellHasBeerTable(String column, String value){
        searchSellHasBeerTable = new SellHasBeerTableModel(dataBaseController);
        try{
           ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM SellHasBeer WHERE " + column + " = '" + value + "' ");
           while(result.next()){
               int idSell = result.getInt("IdSell");
               int idBeer = result.getInt("IdBeer");
               String daneKlienta = result.getString("DaneKlienta");
               String daneSprzedawcy = result.getString("DaneSprzedawcy");
               String danePiwa = result.getString("DanePiwa");
               String data = result.getString("Data");

               SellHasBeer tempSell = new SellHasBeer(idSell,idBeer,daneKlienta,daneSprzedawcy,danePiwa,data);
               searchSellHasBeerTable.addSellHasBeer(tempSell);
           }
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
    }

    public JComboBox addAllClientsToComboBox(JComboBox dane,String name){
        String sql;
        try{

            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM " + name );

            while(result.next()){
                if(name.equals("Beer")){
                    String nazwa = result.getString("Nazwa");
                    double procentAlkoholu = result.getDouble("ProcentAlkoholu");
                    String typPiwa = result.getString("TypPiwa");
                    Double cena = result.getDouble("Cena");

                    dane.addItem(nazwa + ", " + procentAlkoholu + "%, " + typPiwa + ", " + cena + " zł");
                } else {
                    String imie = result.getString("Imie");
                    String nazwiwsko = result.getString("Nazwisko");
                    String pesel = result.getString("Pesel");

                    dane.addItem(imie + " " + nazwiwsko + " " +pesel);
                }
            }
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
        return dane;
    }

    public void removeProductFromProductsTable(int[] productRow){

      productsTable =  productsTable.removeProduct(productRow);


    }

    public boolean checkIfTableHaveSell(int idClient, String table){
        String dane = "";
        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM " + table + " WHERE IdClient = " + idClient);
            result.next();
            String imie = result.getString("Imie");
            String nazwiwsko = result.getString("Nazwisko");
            String pesel = result.getString("Pesel");
            dane = imie + " " + nazwiwsko + " " + pesel;
        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }

        String dataName;
        if(table == "Client"){
            dataName = "DaneKlienta";
        } else {
            dataName = "DaneSprzedawcy";

        }
        int i =0;
        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT * FROM Sell WHERE " + dataName + " = '" + dane + "'");
            while(result.next()){
                i++;
            }

        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
        if(i != 0){
            return true;
        } else{
            return false;
        }
    }


    public int findLastIdSell(){
        int maxId = 0;
        try{
            ResultSet result  = dataBaseController.getStmt().executeQuery("SELECT MAX(IdSell) AS maxId FROM Sell");
            result.next();
            maxId = result.getInt("maxId");

        } catch(SQLException currentException){
            dataBaseController.displayError(currentException);
        }
        return maxId;
    }



    public int findLastIdBeerFromText(String danePiwa){
        String[] dane = danePiwa.split(", ");
        dane[1] = dane[1].replace("%","");
        dane[3] = dane[3].replace(" zł", "");
        int maxId = 0;
        try{
            ResultSet result = dataBaseController.getStmt().executeQuery("SELECT IdBeer FROM BEER WHERE " +
                    "nazwa = '" + dane[0] + "' AND ProcentAlkoholu = " + dane[1] + " AND  TypPiwa = '" + dane[2] + "' AND Cena = " + dane[3]);
            result.next();
            maxId = result.getInt("IdBeer");
        } catch (SQLException currentException){
            dataBaseController.displayError(currentException);
        }
        return maxId;
    }

    public ProjektDataController getDataBaseController() {
        return dataBaseController;
    }



    public ClientTableModel getClientsTable() {
        return clientsTable;
    }

    public SearchClientTableModel getSearchClientTableModel() {
        return searchClientTableModel;
    }

    public void setSearchClientTableModel(SearchClientTableModel searchClientTableModel) {
        this.searchClientTableModel = searchClientTableModel;
    }

    public SellerTableModel getSellersTable() {
        return sellersTable;
    }

    public SearchSellerTableModel getSearchSellerTableModel() {
        return searchSellerTableModel;
    }

    public BeerTableModel getBeersTable() {
        return beersTable;
    }

    public SearchBeerTableModel getSearchBeerTableModel() {
        return searchBeerTableModel;
    }

    public SellTableModel getSellTable() {
        return sellTable;
    }

    public SellTableModel getSearchSellTableModel() {
        return searchSellTableModel;
    }

    public ProductTableModel getProductsTable() {
        return productsTable;
    }

    public void setProductsTable(ProductTableModel productsTable) {
        this.productsTable = productsTable;
    }

    public ProductTableModel getProductsTableStart(){
        this.productsTable = new ProductTableModel(dataBaseController);
        return  productsTable;
    }

    public SellHasBeerTableModel getSellHasBeerTable() {
        return sellHasBeerTable;
    }

    public SellHasBeerTableModel getSearchSellHasBeerTable() {
        return searchSellHasBeerTable;
    }
}
