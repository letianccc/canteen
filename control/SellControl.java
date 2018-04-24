package control;
import UI.SellView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static util.Util.log;


public class SellControl {
    SellView view = new SellView(this);
    ArrayList<String> inputBuffer = new ArrayList<>();
    Uploader uploader = new Uploader();
    XmlFileManager xmlFM = new XmlFileManager();
    SerialFileManager serialFM = new SerialFileManager();

    public SellControl() {}

    public void uploadData() {
        try {
            uploader.uploadData();
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void inputCost(String input) {
        inputBuffer.add(input);
    }

    public void updateCardData() {
        if (inputBuffer.size() > 0) {
            String cardId = getCardId();
            String canteenId = view.getCanteen();
            String machineId = view.getMachine();
            String cost = String.valueOf(getCost());
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String time = timestamp.toString();
            Record record = new Record(cardId, canteenId, machineId, cost, time);
            try {
                xmlFM.saveRecord(record);
                serialFM.saveRecord(record);
            } catch (Exception e) {
                handleException(e);
            }

        }
    }

    private void handleException(Exception e) {
        e.printStackTrace();
    }

    private double getCost() {
        double cost = getNumber();
        while (true) {
            if (inputBuffer.isEmpty()) {
                return cost;
            }
            String c = inputBuffer.remove(0);
            if (c.equals("+")) {
                double n = getNumber();
                cost += n;
            } else if (c.equals("-")) {
                double n = getNumber();
                cost -= n;
            } else {
                throw new java.lang.Error("not + or - " + c);
            }
        }
    }

    private double getNumber() {
        double n = 0;
        while (true) {
            if (inputBuffer.isEmpty()) {
                return n;
            }
            String c = inputBuffer.remove(0);
            if (c.equals("+") || c.equals("-")) {
                inputBuffer.add(0, c);
                return n;
            }
            if (c.equals(".")) {
                double decimal = getDecimal();
                n = n + decimal;
                return n;
            }
            int digit = Integer.parseInt(c);
            n = n * 10 + digit;
        }
    }

    private double getDecimal() {
        double decimal = 0;
        while (true) {
            if (inputBuffer.isEmpty()) {
                return decimal;
            }
            String c = inputBuffer.remove(0);
            if (c.equals("+") || c.equals("-")) {
                inputBuffer.add(0, c);
                return decimal;
            }
            int digit = Integer.parseInt(c);
            decimal = decimal + digit * 0.1;
        }
    }

    public String getCardId() {
        return "10000";
    }

    public String getBalance(String cardId) {
        try {
            String sql = "select balance from card where id = ?";
            ArrayList<HashMap> result = Database.query(sql, cardId);
            HashMap<String, String> row = result.get(0);
            String balance = row.get("balance");
            return balance;
        } catch (Exception e) {
            handleException(e);
            return "";
        }
    }

}
