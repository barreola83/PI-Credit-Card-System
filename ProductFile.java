
package PointsCardSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductFile {

    RandomAccessFile file;
    private final int RECORD_SIZE = 318;

    public ProductFile(String fileName) {
        try {
            file = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProduct(int regNumber) {
        int pos = RECORD_SIZE * regNumber;
        try {
            file.seek(pos);
            byte b[] = new byte[60];
            file.read(b);
            String productName = getFixedString(b);
            float cash = file.readFloat();
            int pointsPrice = file.readInt();
            b = new byte[200];
            file.read(b);
            String description = getFixedString(b);
            b = new byte[30];
            file.read(b);
            String editorial = getFixedString(b);
            b = new byte[20];
            file.read(b);
            String brand = getFixedString(b);

            return new Product(productName, cash, pointsPrice,
                    description, editorial, brand);
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void record(Product product) {
        try {
            file.seek(file.length());
            file.writeBytes(getFixedString(product.getProductName(), 60));
            file.writeFloat(product.getCash());
            file.writeInt(product.getPointsPrice());
            file.writeBytes(getFixedString(product.getDescription(), 200));
            file.writeBytes(getFixedString(product.getEditorial(), 30));
            file.writeBytes(getFixedString(product.getBrand(), 20));
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void record(Product product, int regNumber) {
        try {
            long pos = file.length();

            if (regNumber > 0) {
                pos = RECORD_SIZE * regNumber;
            }
            file.seek(pos);
            file.writeBytes(getFixedString(product.getProductName(), 60));
            file.writeFloat(product.getCash());
            file.writeInt(product.getPointsPrice());
            file.writeBytes(getFixedString(product.getDescription(), 200));
            file.writeBytes(getFixedString(product.getEditorial(), 30));
            file.writeBytes(getFixedString(product.getBrand(), 30));
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public ArrayList<Product> getAll() {
        try {
            ArrayList<Product> producto = new ArrayList();
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Product p = getProduct(i);
                producto.add(p);
            }
            return producto;
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Product> getProductsWBrand() {
        try {
            ArrayList<Product> product = new ArrayList();
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Product p = getProduct(i);

                if (p.getEditorial().equals(""))
                    product.add(p);

            }
            return product;
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Product> getProductsWEditorial() {
        try {
            ArrayList<Product> product = new ArrayList();
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Product p = getProduct(i);

                if (p.getBrand().equals(""))
                    product.add(p);

            }
            return product;
        } catch (IOException ex) {
            Logger.getLogger(ProductFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
