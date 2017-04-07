package projekt.model.Client;

import projekt.model.Adres;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class Client {
    private int idClient;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String email;
    private int idAdres;

    private Adres adres;


    public Client(int idClient, String imie, String nazwisko, String pesel, String email) {
        this.idClient = idClient;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.email = email;
        this.idAdres = idAdres;


}

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel=" + pesel +
                ", email='" + email + '\'' +
                ", idAdres'" + idAdres + '\'' +
                ", ulica'" + adres.getUlica() + '\'' +
                ", numer," + adres.getNumer() + '\'' +
                ", miejscowosc," + adres.getMiejscowosc() + '\'' +
                ", kod," + adres.getKod() + '\''+
                '}';
    }

    public int getIdAdres() {
        return idAdres;
    }

    public void setIdAdres(int idAdres) {
        this.idAdres = idAdres;
    }


    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}

