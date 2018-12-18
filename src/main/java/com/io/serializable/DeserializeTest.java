package com.io.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author wanchongyang
 * @date 2018-11-30 18:09
 */
public class DeserializeTest {
    public static void main(String[] args) {
        Serial serial2;
        try {
            FileInputStream fis = new FileInputStream("serialTest.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            serial2 = (Serial) ois.readObject();
            ois.close();
            System.out.println("Object Deserialize" + serial2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
