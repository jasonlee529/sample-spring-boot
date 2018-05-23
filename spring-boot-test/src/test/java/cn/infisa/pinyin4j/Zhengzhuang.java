package cn.infisa.pinyin4j;

import cn.infisa.utils.Hanzi2Pinyin;
import org.junit.Test;

public class Zhengzhuang {

    Hanzi2Pinyin h2p = new Hanzi2Pinyin();

    @Test
    public void test1() {
        String a = "中国";
        System.out.println(h2p.getStringPinYin(a));
    }

}
