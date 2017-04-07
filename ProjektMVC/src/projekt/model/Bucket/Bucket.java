package projekt.model.Bucket;

/**
 * Created by miQ333 on 04.01.2017.
 */
public class Bucket {
    private int idSell;
    private int idBeer;
    private String beerName;
    private double cena;

    public Bucket(int idSell, int idBeer, String beerName, double cena) {
        this.idSell = idSell;
        this.idBeer = idBeer;
        this.beerName = beerName;
        this.cena = cena;
    }

    public int getIdSell() {
        return idSell;
    }

    public void setIdSell(int idSell) {
        this.idSell = idSell;
    }

    public int getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(int idBeer) {
        this.idBeer = idBeer;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
