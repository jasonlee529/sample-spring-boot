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

public class Xiongtong {

    Hanzi2Pinyin h2p = new Hanzi2Pinyin();

    @Test
    public void test1() throws BadHanyuPinyinOutputFormatCombination, IOException {
        Resource resource = new ClassPathResource("xiongtong.csv");
        Stream<String> stream = Files.lines(Paths.get(resource.getFile().getAbsolutePath()));
        List<Map<String, Object>> datas = new ArrayList();
        Map<String, Map<String, Object>> keys = new HashMap<>();
        stream.forEach(str -> {
            String[] arrs = StringUtils.split(str, ",");
            Map<String, Object> map = new HashMap<>();
            datas.add(map);
            map.put("label",arrs[0]);
            List<Map<String, Object>> children = new ArrayList<>();
            map.put("children",children);
            for(int i=1;i<arrs.length;i++){
                String key = arrs[i];
                Map<String, Object> child = new HashMap();
                child.put("label", key);
                child.put("search", h2p.parsePinyin(key));
                children.add(child);
            }
        });
        Files.write(Paths.get("xiongtong.txt"), JsonMapper.nonDefaultMapper().toJson(datas).getBytes());
    }


}
