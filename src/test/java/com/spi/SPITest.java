package com.spi;

import com.spi.loader.ExtensionLoader;
import com.spi.log.Log;
import org.junit.Test;

/**
 * @author wanchongyang
 * @date 2019-06-30 21:17
 */
public class SPITest {
    @Test
    public void test() {
        // 获取默认实现类
        Log defaultExtension = ExtensionLoader.getExtensionLoader(Log.class)
                .getDefaultExtension();
        defaultExtension.say();

        // 获取指定的特定实现类
        Log englishLog = ExtensionLoader.getExtensionLoader(Log.class)
                .getExtension("english");
        englishLog.say();
    }
}
