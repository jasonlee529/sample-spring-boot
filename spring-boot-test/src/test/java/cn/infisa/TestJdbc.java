package cn.infisa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnect() throws SQLException {
        Assert.assertTrue(jdbcTemplate.getDataSource().getConnection() != null);
    }
}
