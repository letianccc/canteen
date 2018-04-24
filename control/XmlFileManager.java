package control;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static util.Util.log;



public class XmlFileManager extends FileManager {
    String path = "configure.xml";

    public void saveRecord(Record record) throws XMLStreamException, FileNotFoundException, IOException {
        // ArrayList<XMLEvent> events;
        // if (emptyFile(path)) {
        //     events = initFile();
        // }else {
        //     events = readRecords(path);
        //
        // }
        if (emptyFile(path)) {
            initFile();
        }
        ArrayList<XMLEvent> events = readRecords(path);
        int endOfrecords = events.size() - 2;
        ArrayList<XMLEvent> res = genRecordEvents(record);
        events.addAll(endOfrecords, res);
        writeRecords(path, events);
    }

    private void appendRecord(Record record) throws XMLStreamException, FileNotFoundException, IOException {
       ArrayList<XMLEvent> events = readRecords(path);
       int endOfrecords = events.size() - 2;
       ArrayList<XMLEvent> res = genRecordEvents(record);
       events.addAll(endOfrecords, res);
       writeRecords(path, events);
    }

    private void initFile() throws XMLStreamException, IOException {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        ArrayList<XMLEvent> events = new ArrayList<>();
        events.add(factory.createStartDocument());
        events.add(factory.createStartElement("", "", "consumption"));
        events.addAll(addElement("CheckPoint", ""));
        events.add(factory.createEndElement("", "", "consumption"));
        events.add(factory.createEndDocument());
        writeRecords(path, events);
        // return events;
        ArrayList<XMLEvent> newEvents = readRecords(path);

        writeRecords(path, newEvents);
        newEvents = readRecords(path);
        log(newEvents);
    }

    private boolean isConsumptionEnd(XMLEvent event) {
        if (event.isEndElement()) {
            String tag = event.asEndElement().getName().toString();
            if (tag.equals("consumption")) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<XMLEvent> genRecordEvents(Record record) {
        XMLEventFactory factory = XMLEventFactory.newInstance();
        ArrayList<XMLEvent> recordEvents = new ArrayList<>();
        recordEvents.add(factory.createStartElement("", "", "record"));
        ArrayList<XMLEvent> events = genFieldsEvents(record);
        recordEvents.addAll(events);
        recordEvents.add(factory.createEndElement("", "", "record"));
        return recordEvents;
    }

    private ArrayList<XMLEvent> genFieldsEvents(Record record) {
        ArrayList<XMLEvent> target = new ArrayList<>();
        LinkedHashMap<String, String> fields = record.getFields();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            ArrayList<XMLEvent> events = addElement(name, value);
            target.addAll(events);
        }
        return target;
    }

    private ArrayList<XMLEvent> addElement(String tagName, String value) {
        ArrayList<XMLEvent> events = new ArrayList<>();
        XMLEventFactory factory = XMLEventFactory.newInstance();
        XMLEvent start = factory.createStartElement("", "", tagName);
        XMLEvent chars = factory.createCharacters(value);
        XMLEvent end = factory.createEndElement("", "", tagName);
        events.add(start);
        events.add(chars);
        events.add(end);
        return events;
    }

    public static ArrayList<XMLEvent> readRecords(String path) throws XMLStreamException, FileNotFoundException {
        XMLEventReader reader =
                XMLInputFactory.newInstance().createXMLEventReader(new FileReader(path));
        ArrayList<XMLEvent> events = new ArrayList<>();
        while (reader.hasNext()) {
            XMLEvent e = reader.nextEvent();
            events.add(e);
        }
        reader.close();
        return events;
    }

    public static void writeRecords(String path, ArrayList<XMLEvent> events) throws XMLStreamException, IOException {
        XMLEventWriter writer =
                XMLOutputFactory.newInstance().createXMLEventWriter(new FileWriter(path));
        for (XMLEvent e: events) {
            writer.add(e);
        }
        writer.flush();
        writer.close();
    }

//    private boolean emptyFile(String path) {
//        File file = new File(path);
//        boolean empty = !file.exists() || file.length() == 0;
//        return empty;
//    }

}
