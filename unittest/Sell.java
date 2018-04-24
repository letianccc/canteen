package unittest;

import control.*;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static util.Util.log;

public class Sell {
    @org.junit.Before
    public void setUp() throws Exception {
       initDatabase();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSaveXml() {
        String path = "configure.xml";

        try {
            PrintWriter pw = new PrintWriter(path);
            pw.close();
            XmlFileManager fm = new XmlFileManager();
            Record record = new Record("10000", "1", "1", "3", "2018-04-21 00:01:11.083");
            fm.saveRecord(record);
            String text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            String expect = "<?xml version=\"1.0\"?><consumption><CheckPoint></CheckPoint><record><cardId>10000</cardId><canteenId>1</canteenId><machineId>1</machineId><cost>3</cost><time>2018-04-21 00:01:11.083</time></record></consumption>";
            log(text);
            assert text.equals(expect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveSerial() {
        String path = "serialfile";

        try {
            PrintWriter pw = new PrintWriter(path);
            pw.close();
            SerialFileManager serialFM = new SerialFileManager();
            Record target = new Record("10000", "1", "1", "3", "2018-04-21 00:01:11.083");
            serialFM.saveRecord(target);

            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Data> records = (ArrayList<Data>)ois.readObject();
            assert records.size() == 1;
            assert target.match((Record)records.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUploadXml() {
        String path = "configure.xml";

        try {
            String xmlCase = "<?xml version=\"1.0\"?><consumption><CheckPoint></CheckPoint><record><cardId>10000</cardId><canteenId>1</canteenId><machineId>1</machineId><cost>3</cost><time>2018-04-21 00:01:11.083</time></record></consumption>";
            PrintWriter pw = new PrintWriter(path);
            pw.println(xmlCase);
            pw.close();
            XmlUploader uploader = new XmlUploader();
            uploader.uploadData();

            String text = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            String expect = "<?xml version=\"1.0\"?><consumption><record><cardId>10000</cardId><canteenId>1</canteenId><machineId>1</machineId><cost>3</cost><time>2018-04-21 00:01:11.083</time></record><CheckPoint></CheckPoint></consumption>";
            assert text.equals(expect);

            String recordId = "1";
            Record result = TestUtil.getRecord(recordId);
            Record target = new Record("10000", "1", "1", "3", "2018-04-21 00:01:11.083");

            assert result.match(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialUpload() throws Exception {
        String path = "serialfile";
        Record record = new Record("10000", "1", "1", "3", "2018-04-21 00:01:11.083");
        ArrayList<Data> records = new ArrayList();
        records.add(record);
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(records);
        SerialUploader uploader = new SerialUploader();
        uploader.uploadData();

        String recordId = "1";
        Record result1 = TestUtil.getRecord(recordId);
        Record target = new Record("10000", "1", "1", "3", "2018-04-21 00:01:11.083");
        assert result1.match(target);

        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        records = (ArrayList<Data>)ois.readObject();
        Record result2 = (Record)records.get(0);
        assert records.size() == 2;
        assert result2.match(target);
        assert records.get(1) instanceof CheckPoint;



    }

//    @Test
//    public void testGetBalance() {
//        try {
//            Class<?> c = Class.forName("control.SellControl");
//            Object o = c.newInstance();
//            Method m = c.getDeclaredMethod("getBalance", String.class);
//            m.setAccessible(true);
//            String balance = (String)m.invoke(o, "10000");
//            assert balance.equals("5000");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void initDatabase() {
        String DB_URL = "jdbc:mysql://localhost:3306/canteen?useSSL=false";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, "latin","123456");
            String allsql = "drop table if exists record;"       +
                            "create table record"                +
                            "("                                  +
                            "id int primary key auto_increment," +
                            "card_id int not null," +
                            "canteen_id int not null," +
                            "machine_id int not null," +
                            "cost double not null," +
                            "time timestamp(3) not null" +
                            ");" +
                            "" +
                            "drop table if exists card;" +
                            "create table card" +
                            "(" +
                            "id int primary key auto_increment," +
                            "balance double not null" +
                            ");"+
                            "insert into card(id, balance) value(10000, 5000);";
            String[] sqls = allsql.split(";");
            for (String sql: sqls) {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                pst.close();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
