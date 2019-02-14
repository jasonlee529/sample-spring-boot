package cn.jason.lee.advance;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 实现数组的序列化信息
 *
 * @author Jason Lee
 */
public class Question1 {
    private static final String EQ = "=";
    private static final String SEMI = ";";
    private static final String LINE = "\\n";

    public static void main(String[] args) {
        HashMap[] map = new HashMap[5];
        map[0] = new HashMap();
        LinkedHashMap<String, String>[] a = new LinkedHashMap[2];
        a[0] = new LinkedHashMap<String, String>();
        a[0].put("k1", "v1");
        a[0].put("k2", "v3");
        a[1] = new LinkedHashMap<String, String>();
        a[1].put("A", "XXXX");
        //k1=v1;k2=v2\nA=XXX
        String str = store(a);
        System.out.println(str);
        LinkedHashMap<String, String>[] b = load(str);

    }

    /**
     * 数组序列化为字符串
     *
     * @param a
     * @return
     */
    public static String store(LinkedHashMap<String, String>[] a) {
        StringBuilder sb = new StringBuilder();
        String seq = "";
        for (LinkedHashMap<String, String> map : a) {
            sb.append(seq);
            seq = "";
            for (String key : map.keySet()) {
                sb.append(seq).append(key).append(EQ).append(map.get(key));
                seq = SEMI;
            }
            seq = LINE;
        }
        return sb.toString();
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
