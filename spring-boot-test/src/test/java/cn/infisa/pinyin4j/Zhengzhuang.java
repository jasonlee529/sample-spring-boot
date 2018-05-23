package cn.infisa.pinyin4j;

import cn.infisa.utils.Hanzi2Pinyin;
import cn.infisa.utils.JsonMapper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Zhengzhuang {

    Hanzi2Pinyin h2p = new Hanzi2Pinyin();

    @Test
    public void test1() throws BadHanyuPinyinOutputFormatCombination, IOException {
        Resource resource = new ClassPathResource("symptom.csv");
        Stream<String> stream = Files.lines(Paths.get(resource.getFile().getAbsolutePath()));
        List<Map<String, Object>> datas = new ArrayList();
        Map<String, Map<String, Object>> keys = new HashMap<>();
        stream.forEach(str -> {
            String[] arrs = StringUtils.split(str, ",");
            Map<String, Object> map = new HashMap<>();
            String key = arrs[1];
            if (keys.containsKey(key)) {
                map = keys.get(key);
            } else {
                keys.put(arrs[1], map);
                datas.add(map);
                map.put("label", arrs[1]);
                map.put("children", new ArrayList<Map<String, Object>>());
            }
            List<Map<String, Object>> children = (List<Map<String, Object>>) map.get("children");
            Map<String, Object> child = new HashMap();
            children.add(child);
            child.put("label", arrs[2]);
            child.put("type", "plain");
            child.put("search", parsePinyin(arrs[2]));
        });
        Files.write(Paths.get("zhenduan.txt"), JsonMapper.nonDefaultMapper().toJson(datas).getBytes());
    }

    private String parsePinyin(String in) {
        StringBuilder normal = new StringBuilder();
        StringBuilder uppper = new StringBuilder();
        StringBuilder lower = new StringBuilder();
        StringBuilder first = new StringBuilder();
        for (char c : in.toCharArray()) {
            String s = h2p.getCharPinYin(c);
            normal.append(s);
            uppper.append(s.toUpperCase());
            lower.append(s.toLowerCase());
            first.append(s.charAt(0));
        }
        String seq = "|";
        normal.append(seq).append(uppper).append(seq).append(lower).append(seq).append(first);
        return normal.toString();
    }

}
