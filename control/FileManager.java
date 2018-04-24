package control;


import java.io.File;
import java.util.ArrayList;

public class FileManager {



    protected boolean emptyFile(String path) {
        File file = new File(path);
        boolean empty = !file.exists() || file.length() == 0;
        return empty;
    }

}
