import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConcurrentHashMap {


    public static void main(String[] args) {
        concurrentHashMap();
        hashTable();
    }

    private static void hashTable() {
        long start = System.currentTimeMillis();
        final Map map = new Hashtable();
        Executor executor = Executors.newFixedThreadPool(3, new NamedThreadFactory("hashTable"));
        for (int j = 0; j < 10; j++) {
            executor.execute(new Runnable() {
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        map.put(i, i);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println(Thread.currentThread() + " : " + (end - start) + ".");
                }
            });
        }
        ((ExecutorService) executor).shutdown();
        long end = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap time : " + (end - start) + ".");
    }

    private static void concurrentHashMap() {
        long start = System.currentTimeMillis();
        final Map map = new ConcurrentHashMap();
        Executor executor = Executors.newFixedThreadPool(3, new NamedThreadFactory("concurrentHashMap"));
        for (int j = 0; j < 10; j++) {
            executor.execute(new Runnable() {
                public void run() {
                    long start = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        map.put(i, i);
                    }
                    long end = System.currentTimeMillis();
                    System.out.println(Thread.currentThread() + " : " + (end - start) + ".");
                }
            });
        }
        ((ExecutorService) executor).shutdown();
        long end = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap time : " + (end - start) + ".");
    }
}
