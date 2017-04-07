package projekt.model.Beer;

/**
 * Created by miQ333 on 03.01.2017.
 */
public class Beer {
    private int idBeer;
    private String nazwa;
    private double procentAlkoholu;
    private String typPiwa;
    private Double cena;

    public Beer(int idBeer, String nazwa, double procentAlkoholu, String typPiwa, double cena) {
        this.idBeer = idBeer;
        this.nazwa = nazwa;
        this.procentAlkoholu = procentAlkoholu;
        this.typPiwa = typPiwa;
        this.cena = cena;
    }

    public int getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(int idBeer) {
        this.idBeer = idBeer;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getProcentAlkoholu() {
        return procentAlkoholu;
    }

    public void setProcentAlkoholu(double procentAlkoholu) {
        this.procentAlkoholu = procentAlkoholu;
    }

    public String getTypPiwa() {
        return typPiwa;
    }

    public void setTypPiwa(String typPiwa) {
        this.typPiwa = typPiwa;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
