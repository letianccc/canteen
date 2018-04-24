package control;



import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;


import static util.Util.log;

public class Uploader {
    XmlUploader xu;
    SerialUploader su;
    
    public Uploader() {
        xu = new XmlUploader();
        su = new SerialUploader();
    }

    public void uploadData() throws XMLStreamException, SQLException, ClassNotFoundException, IOException{
        xu.uploadData();
        su.uploadData();
    }
}
