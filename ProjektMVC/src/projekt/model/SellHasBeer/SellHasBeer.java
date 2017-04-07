package projekt.model.SellHasBeer;

/**
 * Created by miQ333 on 07.01.2017.
 */
public class SellHasBeer {

    private int IdSell;
    private int IdBeer;
    private String daneKlienta;
    private String daneSprzedawce;
    private String danePiwa;
    private String data;

    public SellHasBeer(int idSell, int idBeer, String daneKlienta, String daneSprzedawce, String danePiwa, String data) {
        IdSell = idSell;
        IdBeer = idBeer;
        this.daneKlienta = daneKlienta;
        this.daneSprzedawce = daneSprzedawce;
        this.danePiwa = danePiwa;
        this.data = data;
    }

    public int getIdSell() {
        return IdSell;
    }

    public void setIdSell(int idSell) {
        IdSell = idSell;
    }

    public int getIdBeer() {
        return IdBeer;
    }

    public void setIdBeer(int idBeer) {
        IdBeer = idBeer;
    }

    public String getDaneKlienta() {
        return daneKlienta;
    }

    public void setDaneKlienta(String daneKlienta) {
        this.daneKlienta = daneKlienta;
    }

    public String getDaneSprzedawce() {
        return daneSprzedawce;
    }

    public void setDaneSprzedawce(String daneSprzedawce) {
        this.daneSprzedawce = daneSprzedawce;
    }

    public String getDanePiwa() {
        return danePiwa;
    }

    public void setDanePiwa(String danePiwa) {
        this.danePiwa = danePiwa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
