package control;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Record extends Data implements Serializable {
    String cardId;
    String canteenId;
    String machineId;
    String cost;
    String time;
    public Record(String cardId, String canteenId, String machineId, String cost, String time) {
        this.cardId = cardId;
        this.canteenId = canteenId;
        this.machineId = machineId;
        this.cost = cost;
        this.time = time;
    }

    public String card() {
        return cardId;
    }

    public String canteen() {
        return canteenId;
    }

    public String machine() {
        return machineId;
    }

    public String cost() {
        return cost;
    }

    public String time() {
        return time;
    }

    public boolean match(Record other) {
        if (card().equals(other.card())) {
            if (canteen().equals(other.canteen())) {
                if (machine().equals(other.machine())) {
                    if (cost().equals(other.cost())) {
                        if (time().equals(other.time())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public LinkedHashMap<String, String> getFields() {
        // ArrayList<Map<String, String>> fields = new ArrayList<>();
        // LinkedHashMap<String, String> card = new HashMap<String, String>("cardId", card());
        // LinkedHashMap<String, String> canteen = new HashMap<>("canteenId", canteen());
        // LinkedHashMap<String, String> machine = new HashMap<>("machineId", machine());
        // LinkedHashMap<String, String> cost = new HashMap<>("cost", cost());
        // LinkedHashMap<String, String> time = new HashMap<>("time", time());

        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("cardId", card());
        fields.put("canteenId", canteen());
        fields.put("machineId", machine());
        fields.put("cost", cost());
        fields.put("time", time());

        // fields.add(card);
        // fields.add(canteen);
        // fields.add(machine);
        // fields.add(cost);
        // fields.add(time);
        return fields;
    }
}
