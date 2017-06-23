package PointsCardSystem;

public class Card {

    private int cardNumber;
    private float balance;
    private int points;
    private String pass;
    private int regNumber;
    private String name;

    public Card(String name, int cardNumber, float balance, int points, String pass) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.points = points;
        this.pass = pass;
        this.name = name;
    }

    public Card(String name, String cardNumber, String balance, String points, String pass) {
        this.name = name;
        this.cardNumber = Integer.valueOf(cardNumber);
        this.balance = Float.valueOf(balance);
        this.points = Integer.valueOf(points);
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Nombre de usuario: " + name + "Numero Tarjeta: " + cardNumber + " Saldo: " + balance + " Puntos: "
                + points + " Contraseña: " + pass;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public float getBalance() {
        return balance;
    }

    public int getPoints() {
        return points;
    }

    public String getPass() {
        return pass;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public String getName() {
        return name;
    }
}
