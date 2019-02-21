import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestHashMapAndTable {

    public static void main(String[] args) {
        int loop = 10;
        for(int i=0;i<10;i++){
            testHashMap(loop);
            testHashTable(loop);
            testConcurrentHashMap(loop);
            loop = loop*10;
        }
    }

    public static void testHashMap(int loop) {
        long start = System.currentTimeMillis();
        Map map = new HashMap();
        for (int i = 0; i < loop; i++) {
            map.put(i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("HashMap           time : " + (end - start) + ". loop times : " + loop);
    }

    public static void testHashTable(int loop) {
        long start = System.currentTimeMillis();
        Map map = new Hashtable();
        for (int i = 0; i < loop; i++) {
            map.put(i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("HashTable         time : " + (end - start) + ". loop times : " + loop);
    }

    public static void testConcurrentHashMap(int loop) {
        long start = System.currentTimeMillis();
        Map map = new ConcurrentHashMap();
        for (int i = 0; i < loop; i++) {
            map.put(i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap time : " + (end - start) + ". loop times : " + loop);
    }
}
