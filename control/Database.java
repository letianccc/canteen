package control;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static util.Util.log;

public class Database {
    static Connection con;

    static {
        connect();
    }

    public static void connect() {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/canteen?useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, "latin","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(String sql, String... args) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            int index = i + 1;
            pst.setString(index, args[i]);
        }
        try {
            pst.executeUpdate();
        } finally {
            pst.close();
        }
    }

    public static ArrayList query(String sql, String... args) throws SQLException {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            int index = i + 1;
            pst.setString(index, args[i]);
        }
        ResultSet rs = pst.executeQuery();
        ArrayList result = formatResultSet(rs);
        pst.close();
        return result;
    }

    private static ArrayList formatResultSet(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        int rowSize = rs.getFetchSize();
        int colSize = md.getColumnCount();
        ArrayList resultSet = new ArrayList(rowSize);
        while (rs.next()) {
            HashMap row = new HashMap(colSize);
            for(int i = 1; i <= colSize; i++){
                String name = md.getColumnName(i);
                String value = rs.getString(i);
                row.put(name, value);
            }
            resultSet.add(row);
        }
        return resultSet;
    }


}
