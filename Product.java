package PointsCardSystem;

public class Product {

    private String productName;
    private float cash;
    private int pointsPrice;
    private String description;
    private String editorial;
    private String brand;

    public Product(String productName, float cash, int pointsPrice, String description, String editorial, String brand) {
        this.productName = productName;
        this.cash = cash;
        this.pointsPrice = pointsPrice;
        this.description = description;
        this.editorial = editorial;
        this.brand = brand;
    }

    public Product(String productName, String cash, String pointsPrice, String description, String editorial, String brand) {
        this.productName = productName;
        this.cash = Float.valueOf(cash);
        this.pointsPrice = Integer.valueOf(pointsPrice);
        this.description = description;
        this.editorial = editorial;
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public float getCash() {
        return cash;
    }

    public int getPointsPrice() {
        return pointsPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getBrand() {
        return brand;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public void setPointsPrice(int pointsPrice) {
        this.pointsPrice = pointsPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Nombre del producto: " + productName + " Precio en efectivo: "
                + cash + " Precio en puntos: " + pointsPrice + " Descripcion: "
                + description + " Editorial: " + editorial + " Marca: " + brand;
    }
}
