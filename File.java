package PointsCardSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class File {

    private RandomAccessFile file;

    private final int RECORD_SIZE = 72;

    public File() {
        try {
            String rout = Loggin.getUsbPath();
            file = new RandomAccessFile(rout + "\\" + "Usuario.dat", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File(String ruta) {
        try {

            file = new RandomAccessFile(ruta + "\\" + "Usuario.dat", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void record(Card tarjeta, int numReg) {
        try {
            long pos = file.length();

            if (numReg >= 0) {
                pos = RECORD_SIZE * numReg;
            }
            file.seek(pos);
            file.writeBytes(getFixedString(tarjeta.getName(), 70));
            file.writeInt(tarjeta.getCardNumber());
            file.writeFloat(tarjeta.getBalance());
            file.writeInt((int) tarjeta.getPoints());
            file.writeBytes(getFixedString(getHex(tarjeta.getPass()), 50));
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void record(Card tarjeta) {
        try {
            file.seek(file.length());
            file.writeBytes(getFixedString(tarjeta.getName(), 70));
            file.writeInt(tarjeta.getCardNumber());
            file.writeFloat(tarjeta.getBalance());
            file.writeInt((int) tarjeta.getPoints());
            file.writeBytes(getFixedString(getHex(tarjeta.getPass()), 50));

        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recordNoHexa(Card card, int regNumber) {
        try {
            long pos = file.length();

            if (regNumber >= 0) {
                pos = RECORD_SIZE * regNumber;
            }
            file.seek(pos);
            file.writeBytes(getFixedString(card.getName(), 70));
            file.writeInt(card.getCardNumber());
            file.writeFloat(card.getBalance());
            file.writeInt((int) card.getPoints());
            file.writeBytes(getFixedString(card.getPass(), 50));
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Card> getAll() {

        try {
            ArrayList<Card> todos = new ArrayList();
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Card c = getCard(i);
                c.setRegNumber(i);

                todos.add(c);
            }
            return todos;
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Card getCard(int recNumber) {
        int pos = RECORD_SIZE * recNumber;

        try {
            file.seek(pos);
            byte b[] = new byte[70];
            file.read(b);
            String nombre = getFixedString(b);
            int numeroTarjeta = file.readInt();
            float saldo = file.readFloat();
            int puntos = file.readInt();
            b = new byte[50];
            file.read(b);
            String contraseña = getFixedString(b);

            return new Card(nombre, numeroTarjeta, saldo, puntos, contraseña);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void delete(int numRegistro) {
        try {
            File.this.record(getCard(getAllRegs() - 1), numRegistro);
            file.setLength(file.length() - RECORD_SIZE);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int getAllRegs() {
        try {
            return (int) (file.length() / RECORD_SIZE);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private String getFixedString(String s, int len) {
        StringBuilder sb = new StringBuilder(s);
        sb.setLength(len);
        return sb.toString();
    }

    private String getFixedString(byte b[]) {
        String s = new String(b);
        int fin = s.indexOf('\0');
        return s.substring(0, fin);
    }

    public static String getHex(String cadena) {
        String hex = " ";
        for (int i = 0; i < cadena.length(); i++) {
            String hex2 = Integer.toHexString((int) (cadena.charAt(i)));
            hex = hex + hex2;
        }
        return hex;

    }

    public void getCliente(String contraseña) {
        try {
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Card t = getCard(i);

                if (t.getPass().equals(getHex(contraseña))) {
                    Menu menu = new Menu();
                    menu.setVisible(true);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
