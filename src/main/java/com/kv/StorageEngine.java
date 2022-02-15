package com.kv;

import java.io.IOException;

/**
 * @author wanchongyang
 * @date 2022/2/7 3:38 PM
 */
public interface StorageEngine {

    byte[] get(byte[] key) throws IOException;

    void save(byte[] key, byte[] value) throws IOException;

    boolean update(byte[] key, byte[] value);

    boolean delete(byte[] key) throws IOException;
}
