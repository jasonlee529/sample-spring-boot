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

/**
 * 历史
 */
public class Jiancha {

    Hanzi2Pinyin h2p = new Hanzi2Pinyin();

    @Test
    public void test1() throws BadHanyuPinyinOutputFormatCombination, IOException {
        Resource resource = new ClassPathResource("jiancha.csv");
        Stream<String> stream = Files.lines(Paths.get(resource.getFile().getAbsolutePath()));
        List<Map<String, Object>> datas = new ArrayList();
        Map<String, Map<String, Object>> keys = new HashMap<>();
        stream.forEach(str -> {
            String[] arrs = StringUtils.split(str, ",");
            String key = arrs[0];
            Map<String, Object> map = new HashMap<>();
            if (keys.containsKey(key)) {
                map = keys.get(key);
            } else {
                keys.put(key, map);
                datas.add(map);
                map.put("label", key);
                map.put("children", new ArrayList<Map<String, Object>>());
            }
            List<Map<String, Object>> fChildren = (List<Map<String, Object>>) map.get("children");
            Map<String, Object> tmap = new HashMap<>();
            fChildren.add(tmap);
            tmap.put("label",arrs[1]);
            List<Map<String, Object>> children = new ArrayList<>();
            tmap.put("children",children);
            for(int i=2;i<arrs.length;i++){
                String skey = arrs[i];
                Map<String, Object> child = new HashMap();
                child.put("label", skey);
                child.put("search", h2p.parsePinyin(skey));
                children.add(child);
            }
        });
        Files.write(Paths.get("jiancha.json"), JsonMapper.nonDefaultMapper().toJson(datas).getBytes());
    }


}
