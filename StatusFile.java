package PointsCardSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusFile {

    RandomAccessFile file;
    private final int RECORD_SIZE = 92;

    public StatusFile(String nombreArchivo) {
        try {
            file = new RandomAccessFile("Estado.dat", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatusFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void record(Status status) {
        try {
            file.seek(file.length());
            file.writeBytes(getFixedString(status.getProductName(), 60));
            file.writeFloat(status.getCash());
            file.writeFloat(status.getPoints());
            file.writeInt(status.getAccountNumber());
            file.writeBytes(getFixedString(status.getPayWay(), 20));
        } catch (IOException ex) {
            Logger.getLogger(StatusFile.class.getName()).log(Level.SEVERE, null, ex);
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

    public Status getStatus(int regNumber) {
        int pos = RECORD_SIZE * regNumber;
        try {
            file.seek(pos);
            byte b[] = new byte[60];
            file.read(b);
            String productName = getFixedString(b);
            float cash = file.readFloat();
            float points = file.readFloat();
            int accountNumber = file.readInt();
            b = new byte[20];
            file.read(b);
            String payWay = getFixedString(b);

            return new Status(productName, cash,
                    points, accountNumber, payWay);
        } catch (IOException ex) {
            Logger.getLogger(StatusFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Status> getAllStatus() {

        try {
            ArrayList<Status> status = new ArrayList();
            for (int i = 0; i < file.length() / RECORD_SIZE; i++) {
                Status es = getStatus(i);
                status.add(es);
            }
            return status;
        } catch (IOException ex) {
            Logger.getLogger(StatusFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public float obtainPoints(float balance) {
        float points = 0;

        if (balance <= 250) {
            return points;
        } else if (balance > 250 && balance <= 500) {
            points = (float) (balance * .05);
            return points;
        } else if (balance > 500 && balance <= 1000) {
            points = (float) (balance * .10);
            return points;
        } else if (balance > 1000) {
            points = (float) (balance * .15);
            return points;
        }
        return 0;
    }

}
