// import UI.Sell;
import UI.SellView;
import control.*;
import util.Util;
import unittest.TestUtil;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static util.Util.log;

public class Test {
    public static void main(String[] args) throws Exception{
          // SellControl sell = new SellControl();
        String path = "configure.xml";
        XMLEventFactory factory = XMLEventFactory.newInstance();
        ArrayList<XMLEvent> events = new ArrayList<>();
        events.add(factory.createStartDocument());
        events.add(factory.createStartElement("", "", "consumption"));
        events.add(factory.createEndElement("", "", "consumption"));
        events.add(factory.createEndDocument());
        XmlFileManager.writeRecords(path, events);

        factory = XMLEventFactory.newInstance();
        events = new ArrayList<>();
        events.add(factory.createStartDocument(""));
        events.add(factory.createStartElement("", "", "consumption"));
        events.add(factory.createEndElement("", "", "consumption"));
        events.add(factory.createEndDocument());
        XmlFileManager.writeRecords(path, events);

//        ArrayList<XMLEvent> newEvents = XmlFileManager.readRecords(path);

//        XmlFileManager.writeRecords(path, events);
        events = XmlFileManager.readRecords(path);
        log(events);
        XmlFileManager.writeRecords(path, events);
        events = XmlFileManager.readRecords(path);
        log(events);
        XmlFileManager.writeRecords(path, events);
//        events = XmlFileManager.readRecords(path);
//        log(events);
//        XmlFileManager.writeRecords(path, events);

    }


//    public static void serilization() throws Exception{
//        String path = "serialfile";
//        ArrayList<Data> ts = new ArrayList();
//        T t1 = new T(1);
//        T t2 = new T(2);
//        ts.add(t1);
//        ts.add(t2);
//        ts.add(new Parent());
//        // T t = new T(3);
//        // FileOutputStream fos = new FileOutputStream(path);
//        // ObjectOutputStream oos = new ObjectOutputStream(fos);
//        // oos.writeObject(ts);
//        write(path, ts);
//
//        FileInputStream fis = new FileInputStream(path);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        ArrayList<Data> results = (ArrayList<Data>)ois.readObject();
//
//
//        for (Object o: results) {
//            if (o instanceof T) {
//                T t = (T)o;
//                log(t.value);
//            } else if (o instanceof Parent) {
//                log("this is parent");
//            }
//
//        }
//    }

    public static void write(String path, Object o) throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
    }

}



class Parent implements Serializable{
    public Parent() {
    }

    public void show() {
        log("this is Parent");
    }
}

class Child extends Parent {
    public Child() {
    }

    public void show() {
        log("this is Child");
    }

    public void show(double d) {
        log("double");
    }

    public void show(int i) {
        log("int");
    }
}

class T implements Serializable {
    int value;
    public T(int value) {
        this.value = value;
    }

    public static class StaticT {
        public StaticT() {

        }
    }
}

//class SellThread extends Thread {
//    Sell sell;
//    public void run() {
//        sell = new Sell();
//    }
//
//    public boolean isExit() {
//        return sell.isVisible();
//    }
//
//}

class TestThread extends Thread {

    public void run() {
        String fileName = "configure.xml";
        String test = "<?xml version=\"1.0\"?><consumption><record><card>0</card><canteen>食堂1</canteen><machine>机器1</machine><cost>2.0</cost><time>Thu Apr 19 16:46:10 CST 2018</time></record><record><card>0</card><canteen>食堂1</canteen><machine>机器1</machine><cost>4.0</cost><time>Thu Apr 19 16:46:14 CST 2018</time></record></consumption>";
        String result = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            result = bufferedReader.readLine();

            // Always close files.
            bufferedReader.close();

        } catch (Exception e) {
            log(e);
        }
        assert result.equals(test);

    }

}
