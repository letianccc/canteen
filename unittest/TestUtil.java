package unittest;


import control.Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestUtil {
    public static Record getRecord(String recordId) {
        Record record = null;
        String DB_URL = "jdbc:mysql://localhost:3306/canteen?useSSL=false";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, "latin","123456");
            String sql = "select * from record where id = ? ;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, recordId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String cardId = rs.getString("card_id");
                String canteenId = rs.getString("canteen_id");
                String machineId = rs.getString("machine_id");
                String cost = rs.getString("cost");
                String time = rs.getString("time");
                record = new Record(cardId, canteenId, machineId, cost, time);
            }
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

    public static String getBalance(String cardId) {
        String DB_URL = "jdbc:mysql://localhost:3306/canteen?useSSL=false";
        String balance = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, "latin","123456");
            String sql = "select balance from card where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, cardId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                balance = rs.getString("balance");
            }
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
