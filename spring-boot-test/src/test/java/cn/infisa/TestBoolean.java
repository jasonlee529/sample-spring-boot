package cn.infisa;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基本测试，加入断言
 */
public class TestBoolean {
    @Test
    public void testTrue() {
        Assert.assertTrue(true);
    }

    @Test
    public void testFalse() {
        Assert.assertFalse(false);
    }
}
