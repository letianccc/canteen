
package control;

import org.codehaus.groovy.tools.shell.IO;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import static util.Util.log;



public class SerialFileManager extends FileManager {
    String path = "serialfile";

    public void saveRecord(Record record) throws XMLStreamException, ClassNotFoundException, IOException {
        ArrayList<Data> records;
        if (emptyFile(path)) {
            records = new ArrayList();
        } else {
            records = readRecords(path);
        }
        records.add(record);
        writeRecords(path, records);
    }

    public static void writeRecords(String path, Object o) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
    }

    public static ArrayList<Data> readRecords(String path) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Data> records = (ArrayList<Data>)ois.readObject();
        return records;
    }


}
