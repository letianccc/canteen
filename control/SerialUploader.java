
package control;



import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;



public class SerialUploader extends Upload {
    String path = "serialfile";

    public void uploadData() throws SQLException, IOException, ClassNotFoundException {
        if (emptyFile(path)) {
            return;
        }
        ArrayList<Data> records = SerialFileManager.readRecords(path);
        updateDb(records);
        updateFile(records);
    }

    private void updateFile(ArrayList<Data> records) throws IOException {
        int pos = getCheckPointPosition(records);
        if (pos == -1) {
            records.add(new CheckPoint());
        } else {
            records.add(records.remove(pos));
        }
        SerialFileManager.writeRecords(path, records);
    }

    private void updateDb(ArrayList<Data> records) throws SQLException {
        int pos = getCheckPointPosition(records);
        for (int i = pos + 1; i < records.size(); i++) {
            Record record = (Record)records.get(i);
            upload(record);
        }
    }

    private int getCheckPointPosition(ArrayList<Data> records) {
        int last = records.size() - 1;
        for (int i = last; i > 0; i--) {
            Object o = records.get(i);
            if (o instanceof CheckPoint) {
                return i;
            }
        }
        return -1;
    }

}
