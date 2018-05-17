package cn.infisa.io.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class IdNums {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testA() throws IOException {
        String sql = " SELECT distinct t.RESIDENT_ID from id_card t where LENGTH(t.RESIDENT_ID)>=15 and left(t.RESIDENT_ID,1)<>'0' order by RESIDENT_ID ";
        List<String> ids = jdbcTemplate.queryForList(sql, String.class);
        for (String id : ids) {
            Document doc = Jsoup.connect("http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=" + id + "&B1=%B2%E9+%D1%AF").get();
            Elements node = doc.select("table[width=520]");
            Element table = node.get(1);
            System.out.print(id);
            if (table.select("table").size() > 0) {
                Element child = table.select("table:eq(0)").first();
                if(child !=null && child.select("tr").size() >1){
                    String src="";
                    String dest = "";
                    if(child.select("tr").size()>4){
                         src = child.select("tr:eq(3)").select("td:eq(1)").text();
                         dest = child.select("tr:eq(4)").select("td:eq(1)").first().ownText();
                    }else{
                        dest = child.select("tr:eq(3)").select("td:eq(1)").first().ownText();
                    }
                    String updateSql = "update id_card set src_area='" + src + "' , now_area='" + dest + "' where RESIDENT_ID='" + id+"'";
                    jdbcTemplate.execute(updateSql);
                    System.out.print("    "+src+"    "+dest);
                }
            }
            System.out.println("   ");
        }
    }

}
