import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wanchongyang
 * @date 2018/7/21
 */
public class TestSample {
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(ThreadLocalRandom.current().nextLong(100000L));
        }
    }
}
