
package control;

import java.io.Serializable;

public class CheckPoint extends Data implements Serializable {
    int value;
    public CheckPoint() {
        value = 1;
    }
}
