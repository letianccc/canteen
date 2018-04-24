
package control;



import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.stream.XMLStreamException;



class Upload {
    protected void upload(Record record) throws SQLException {
        insertRecord(record);
        updateBalance(record);
    }

    private void insertRecord(Record record) throws SQLException {
        String sql = "insert into record(card_id, canteen_id, machine_id, cost, time)" +
            "values(?, ?, ?, ?, ?);";
        String[] args = {record.cardId, record.canteenId, record.machineId, record.cost, record.time};
        Database.update(sql, args);
    }

    private void updateBalance(Record record) throws SQLException {
        String sql = "update card set balance = balance - ? where id = ?;";
        String[] args = {record.cost, record.cardId};
        Database.update(sql, args);
    }

    protected boolean emptyFile(String path) {
        File file = new File(path);
        boolean empty = !file.exists() || file.length() == 0;
        return empty;
    }

}
