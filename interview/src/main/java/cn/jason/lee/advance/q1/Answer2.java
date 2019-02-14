package cn.jason.lee.advance.q1;

import java.util.LinkedHashMap;

/**
 * key/value 不能包含 =  ; \\n \\\\n 这几种特殊字符
 */
public class Answer2 {
    private static final String EQ = "=";
    private static final String SEMI = ";";
    private static final String LINE = "\\n";
    private static final String LINE2 = "\\\\n";

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
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
        System.out.println(store(b));
    }
    private static void test2() {
        LinkedHashMap<String, String>[] a = new LinkedHashMap[3];
        a[0] = new LinkedHashMap<String, String>();
        a[0].put("k1", "v1");
        a[0].put("k2", "v3");
        a[1] = new LinkedHashMap<String, String>();
        a[2] = new LinkedHashMap<String, String>();
        a[2].put("B", ";");
        a[2].put("A", "\\n");
        a[2].put("C", "XXX2X");
        //k1=v1;k2=v2\nA=XXX
        String str = store(a);
        System.out.println(str);
        LinkedHashMap<String, String>[] b = load(str);
        System.out.println(store(b));

    }
    private static void test3() {

    }
    private static void test4() {

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
        String[] strs = str.split(LINE2);
        LinkedHashMap<String, String>[] a = new LinkedHashMap[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            a[i] = map;
            String[] kvs = s.split(SEMI);
            for (String kv : kvs) {
                if("".equals(kv) || null==kv){

                }else{
                    String[] kk = kv.split(EQ);
                    if (kk.length == 0) {

                    } else if (kk.length == 1) {
                        map.put(kk[0], "");
                    } else if (kk.length == 2) {
                        map.put(kk[0], kk[1]);
                    } else {

                    }
                }

            }
        }
        return a;
    }
}
