package projekt.model;

import projekt.controller.ProjektDataController;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by miQ333 on 01.01.2017.
 */
public class Adres {
    private int IdAdres;
    private String ulica;
    private String numer;
    private String miejscowosc;
    private String kod;
    private ProjektDataController dataController;


    public Adres(int idAdres, ProjektDataController dataController) {
        this.IdAdres = idAdres;
        this.dataController = dataController;
        try{
            ResultSet asd = dataController.getStmt().executeQuery("SELECT * FROM Adres WHERE IdAdres = " + idAdres);
            asd.next();
                String ulica = asd.getString("Ulica");
                String numer = asd.getString("Numer");
                String miejscowosc = asd.getString("Miejscowosc");
                String kod = asd.getString("Kod");

                this.ulica = ulica;
                this.numer = numer;
                this.miejscowosc = miejscowosc;
                this.kod = kod;

        } catch (SQLException currentException){
            dataController.displayError(currentException);
        }
    }

    public int getIdAdres() {
        return IdAdres;
    }

    public void setIdAdres(int idAdres) {
        IdAdres = idAdres;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public ProjektDataController getDataController() {
        return dataController;
    }

    public void setDataController(ProjektDataController dataController) {
        this.dataController = dataController;
    }
}
