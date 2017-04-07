package projekt.model.Product;

/**
 * Created by miQ333 on 04.01.2017.
 */
public class Product {
    private String nazwa;
    private double procent;
    private String typPiwa;
    private double cena;

    public Product(String nazwa, double procent, String typPiwa, double cena) {
        this.nazwa = nazwa;
        this.procent = procent;
        this.typPiwa = typPiwa;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = procent;
    }

    public String getTypPiwa() {
        return typPiwa;
    }

    public void setTypPiwa(String typPiwa) {
        this.typPiwa = typPiwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
