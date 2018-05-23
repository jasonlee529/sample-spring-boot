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
 * 体征
 */
public class Tizheng {

    Hanzi2Pinyin h2p = new Hanzi2Pinyin();

    @Test
    public void test1() throws BadHanyuPinyinOutputFormatCombination, IOException {
        Resource resource = new ClassPathResource("tizheng.csv");
        Stream<String> stream = Files.lines(Paths.get(resource.getFile().getAbsolutePath()));
        List<Map<String, Object>> datas = new ArrayList();
        stream.forEach(str -> {
            String[] arrs = StringUtils.split(str, ",");
            for (String s : arrs) {
                Map<String, Object> map = new HashMap<>();
                datas.add(map);
                map.put("label", s);
                map.put("search", h2p.parsePinyin(s));
            }
        });
        Files.write(Paths.get("tizheng.json"), JsonMapper.nonDefaultMapper().toJson(datas).getBytes());
    }


}
