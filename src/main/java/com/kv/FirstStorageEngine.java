package com.kv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author wanchongyang
 * @date 2022/2/7 3:39 PM
 */
public class FirstStorageEngine implements StorageEngine {
    private RandomAccessFile randomAccessFile;


    public FirstStorageEngine(String fileName) throws FileNotFoundException {
        randomAccessFile = new RandomAccessFile(fileName, "rw");
    }

    public synchronized byte[] get(final byte[] key) throws IOException {
        randomAccessFile.seek(0);
        while (true) {
            byte[] keyLengthData = new byte[4];
            byte[] valueLengthData = new byte[4];
            int read = randomAccessFile.read(keyLengthData);
            if (read == -1) {
                return null;
            }
            byte b = randomAccessFile.readByte();

            int read1 = randomAccessFile.read(valueLengthData);
            if (read1 == -1) {
                return null;
            }
            int keyLength = Bytes.toInt(keyLengthData);
            int valueLength = Bytes.toInt(valueLengthData);


            byte[] keyData = new byte[keyLength];
            randomAccessFile.read(keyData);
            if (Arrays.equals(key, keyData)) {
                if (b == 1) {
                    byte[] valueData = new byte[valueLength];
                    randomAccessFile.read(valueData);
                    return valueData;
                } else if (b == 2) {
                    System.out.println("被标记删除");
                }

            } else {
                randomAccessFile.skipBytes(valueLength);
            }
        }

    }

    public synchronized void save(byte[] key, byte[] value) throws IOException {
        int keyLength = key.length;
        byte[] keyLengthData = Bytes.toBytes(keyLength);

        int valueLength = key.length;
        byte[] valueLengthData = Bytes.toBytes(valueLength);

        randomAccessFile.write(keyLengthData);
        randomAccessFile.write(new byte[]{1});
        randomAccessFile.write(valueLengthData);
        randomAccessFile.write(key);
        randomAccessFile.write(value);
    }

    public boolean update(byte[] key, byte[] value) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(byte[] key) throws IOException {
        randomAccessFile.seek(0);
        long pos = 0;

        while (true) {
            byte[] keyLengthData = new byte[4];
            byte[] valueLengthData = new byte[4];

            int read = randomAccessFile.read(keyLengthData);
            if (read == -1) {
                return false;
            }
            int keyLength = Bytes.toInt(keyLengthData);
            pos += 4;

            byte b = randomAccessFile.readByte();
            pos += 1;

            int read1 = randomAccessFile.read(valueLengthData);
            if (read1 == -1) {
                return false;
            }
            int valueLength = Bytes.toInt(valueLengthData);
            pos += 4;



            byte[] keyData = new byte[keyLength];
            randomAccessFile.read(keyData);
            pos += keyLength;

            if (Arrays.equals(key, keyData)) {
                if (b == 1) {
                    randomAccessFile.seek(pos - keyLength - 5);
                    randomAccessFile.write(new byte[]{2});
                    randomAccessFile.seek(pos);
                    System.out.println("删除成功");
                    return true;
                } else if (b == 2) {
                    System.out.println("已经被标记删除");
                    return false;
                }

            } else {
                pos += valueLength;
                randomAccessFile.skipBytes(valueLength);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FirstStorageEngine firstStorageEngine = new FirstStorageEngine("test.db");
        String k1 = UUID.randomUUID().toString();
        String v1 = UUID.randomUUID().toString();
        firstStorageEngine.save(k1.getBytes(), v1.getBytes());
        System.out.println(v1.equals(new String(firstStorageEngine.get(k1.getBytes()))));

        String k2 = UUID.randomUUID().toString();
        String v2 = UUID.randomUUID().toString();
        firstStorageEngine.save(k2.getBytes(), v2.getBytes());
        System.out.println(v2.equals(new String(firstStorageEngine.get(k2.getBytes()))));

        String k3 = UUID.randomUUID().toString();
        String v3 = UUID.randomUUID().toString();
        firstStorageEngine.save(k3.getBytes(), v3.getBytes());
        System.out.println(v3.equals(new String(firstStorageEngine.get(k3.getBytes()))));
        firstStorageEngine.delete(k3.getBytes());
        System.out.println(firstStorageEngine.get(k3.getBytes()));
    }

}
