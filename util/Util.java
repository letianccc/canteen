package util;

public class Util {
    public static void log(Object ...messages) {
        for (Object m: messages) {
            System.out.println(m);
        }
    }
}
