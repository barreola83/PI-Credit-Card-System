package PointsCardSystem;

public class Status {

    private String productName;
    private float cash;
    private float points;
    private int accountNumber;
    private String payWay;
    private String date;

    public Status(String productName, float cash, float points, int accountNumber, String payWay) {
        this.productName = productName;
        this.cash = cash;
        this.points = points;
        this.accountNumber = accountNumber;
        this.payWay = payWay;
    }

    public Status(String productName, String cash, String points, String accountNumber, String payWay) {
        this.productName = productName;
        this.cash = Float.valueOf(cash);
        this.points = Float.valueOf(points);
        this.accountNumber = Integer.valueOf(accountNumber);
        this.payWay = payWay;
    }

    public String getProductName() {
        return productName;
    }

    public float getCash() {
        return cash;
    }

    public float getPoints() {
        return points;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPayWay() {
        return payWay;
    }

    public String getDate() {
        return date;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Nombre del producto: " + productName + " Precio en efectivo: "
                + cash + " Puntos obtenidos :" + points + " Numero de Cuenta:"
                + accountNumber + " Forma de pago: " + payWay;
    }
}
