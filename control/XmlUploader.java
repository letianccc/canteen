package control;



import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;

import static util.Util.log;


public class XmlUploader extends Upload {
    String path = "configure.xml";


    public void uploadData() throws XMLStreamException, SQLException, IOException, ClassNotFoundException {
        if (emptyFile(path)) {
            return;
        }
        ArrayList<XMLEvent> events = XmlFileManager.readRecords(path);
        uploadToDb(events);
        updateCheckPoint(events);
    }

    private void uploadToDb(ArrayList<XMLEvent> events) throws XMLStreamException, FileNotFoundException, SQLException{
        int pos = getEndOfCheckPoint(events);
        int recordEventNum = 17;
        for (int i = pos; i < events.size() - 2; i += recordEventNum) {
            updateDb(events, i);
        }
    }

    private int getEndOfCheckPoint(ArrayList<XMLEvent> events) {
        int pos = getCheckPoint(events);
        int cpEventNum = 2;
        int end = pos + cpEventNum;
        return end;
    }

    private int getCheckPoint(ArrayList<XMLEvent> events) {
        for (XMLEvent e: events) {
            if (isCheckPoint(e)) {
                return events.indexOf(e);
            }
        }
        assert false;
        return -1;
    }

    private void updateCheckPoint(ArrayList<XMLEvent> events) throws XMLStreamException, IOException {
        int pos = getCheckPoint(events);
        XMLEvent start = events.remove(pos);
        XMLEvent end = events.remove(pos);
        int endOfRecords = events.size() - 2;
        events.add(endOfRecords, start);
        events.add(endOfRecords+1, end);
        XmlFileManager.writeRecords(path, events);
    }

    private void updateDb (ArrayList<XMLEvent> events, int recordIndex) throws XMLStreamException, SQLException {
        int fieldNum = 5;
        String[] fields = new String[5];
        // 跳过<Record>Element
        int j = recordIndex + 1;
        int elementEventNum = 3;
        for (int i = 0; i < fieldNum; i++) {
            fields[i] = getElementValue(events, j);
            // 跳过<Element><chars></Element>三个Event
            j += elementEventNum;
        }
        Record record = new Record(fields[0], fields[1], fields[2], fields[3], fields[4]);
        upload(record);
    }

    private String getElementValue(ArrayList<XMLEvent> events, int elementIndex) throws XMLStreamException {
        int charsEventIndex = elementIndex + 1;
        XMLEvent chars = events.get(charsEventIndex);
        String value = chars.asCharacters().toString();
        return value;
    }

    private boolean isCheckPoint(XMLEvent event) {
        if (event.isStartElement()) {
            String tag = event.asStartElement().getName().toString();
            if (tag.equals("CheckPoint")) {
                return true;
            }
        }
        return false;
    }

    private String getCardId() {
        return "10000";
    }


}
