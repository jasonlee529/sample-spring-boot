
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class testBitCal {


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.println("i=" + i + ",j=" + j + ",i&j=" + (i & j));
            }
        }
    }


    @Test
    public void testHash() {
        Integer i = 0;
        for (i = 0; i < 100; i++) {
            String key = "str" + i;
            int h = key.hashCode();
            System.out.println(key + " : " + h + " : " + (h >>> 16) + " :  " + hash(key) + " :  " + (hash(key) & 15));
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void testPut() {
        Map map = new HashMap();
        map.put("abc", "32");
    }
}
