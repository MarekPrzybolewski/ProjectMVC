package projekt.controller;

import projekt.model.ProjektDataBase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class ProjektDataController {

    private ProjektAppController baseController;
    private String connectionString;
    private Connection databaseConnection;
    private Statement stmt;
    private ProjektDataBase myDataBase;

    public ProjektDataController(ProjektAppController baseController) {
        this.baseController = baseController;
        connectionString = "jdbc:sqlite:baza.db";
        checkDriver();
        setupConnection();
        myDataBase = new ProjektDataBase(this);

    }

    private void checkDriver(){
        try{
            Class.forName("org.sqlite.JDBC");
        } catch (Exception currentException) {
            displayError(currentException);
            System.exit(1);
        }
    }

    private void setupConnection(){
        try {
            databaseConnection = DriverManager.getConnection(connectionString);
            stmt = databaseConnection.createStatement();
        } catch (SQLException currentException) {
            displayError(currentException);
        }
    }


    public void displayError(Exception currentException){
        JOptionPane.showMessageDialog(baseController.getAppFrame(), "Exception: " + currentException.getMessage());
        if(currentException instanceof SQLException){
            JOptionPane.showMessageDialog(baseController.getAppFrame(), "SQL state: " + ((SQLException) currentException).getSQLState());
            JOptionPane.showMessageDialog(baseController.getAppFrame(),"SQL error code: " + ((SQLException) currentException).getErrorCode());
        }

    }

    public Statement getStmt() {
        return stmt;
    }

    public ProjektDataBase getMyDataBase() {
        return myDataBase;
    }

    public ProjektAppController getBaseController() {
        return baseController;
    }
}
