package projekt.model.Seller;

/**
 * Created by miQ333 on 02.01.2017.
 */
public class Seller {
    private int idSeller;
    private String imie;
    private String nazwisko;
    private double pensja;
    private String pesel;
    private String email;

    public Seller(int idSeller, String imie, String nazwisko, double pensja, String pesel, String email) {
        this.idSeller = idSeller;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pensja = pensja;
        this.pesel = pesel;
        this.email = email;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
