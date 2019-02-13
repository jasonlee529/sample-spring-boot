import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 实现数组的序列化信息
 *
 * @author Jason Lee
 */
public class Question1 {

    public static void main(String[] args) {
        HashMap[] map = new HashMap[5];
        map[0] = new HashMap();
        LinkedHashMap<String, String>[] a = new LinkedHashMap[5];
        a[0] = new LinkedHashMap<String, String>();
        a[0].put("k1", "v1");
        a[0].put("k2", "v3");
        a[1] = new LinkedHashMap<String, String>();
        a[1].put("A", "XXXX");
        String str = store(a);
        LinkedHashMap<String, String>[] b = load(str);

    }

    /**
     * 数组序列化为字符串
     *
     * @param a
     * @return
     */
    public static String store(LinkedHashMap[] a) {
        return "";
    }

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    public static LinkedHashMap[] load(String str) {

        return null;
    }
}
