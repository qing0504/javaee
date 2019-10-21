package com.jvm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * https://www.cnblogs.com/softidea/p/9725156.html
 * https://www.2uo.de/myths-about-urandom/
 * @author wanchongyang
 * @date 2019/10/21 11:00 上午
 */
public class JstackTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("securerandom.strongAlgorithms:" + Security.getProperty("securerandom.strongAlgorithms"));
        // SecureRandom random = SecureRandom.getInstanceStrong();
        // SecureRandom random = SecureRandom.getInstance("NativePRNG");
        // SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom random = SecureRandom.getInstance("NativePRNGBlocking");
        // SecureRandom random = SecureRandom.getInstance("NativePRNGNonBlocking");
        System.out.println(random.nextInt(12));
    }
}
