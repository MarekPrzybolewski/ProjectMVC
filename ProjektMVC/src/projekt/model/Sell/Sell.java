package projekt.model.Sell;

public class Sell {
    private int idSell;
    private String daneKlienta;
    private String daneSprzedawcy;
    private String data;
    private double kosztCalkowity;

    public Sell(int idSell, String daneKlienta, String daneSprzedawcy, String data, double kosztCalkowity) {
        this.idSell = idSell;
        this.daneKlienta = daneKlienta;
        this.daneSprzedawcy = daneSprzedawcy;
        this.data = data;
        this.kosztCalkowity = kosztCalkowity;
    }

    public int getIdSell() {
        return idSell;
    }

    public void setIdSell(int idSell) {
        this.idSell = idSell;
    }

    public String getDaneKlienta() {
        return daneKlienta;
    }

    public void setDaneKlienta(String daneKlienta) {
        this.daneKlienta = daneKlienta;
    }

    public String getDaneSprzedawcy() {
        return daneSprzedawcy;
    }

    public void setDaneSprzedawcy(String daneSprzedawcy) {
        this.daneSprzedawcy = daneSprzedawcy;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getKosztCalkowity() {
        return kosztCalkowity;
    }

    public void setKosztCalkowity(double kosztCalkowity) {
        this.kosztCalkowity = kosztCalkowity;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "idSell=" + idSell +
                ", daneKlienta='" + daneKlienta + '\'' +
                ", daneSprzedawcy='" + daneSprzedawcy + '\'' +
                ", data='" + data + '\'' +
                ", kosztCalkowity=" + kosztCalkowity +
                '}';
    }
}
