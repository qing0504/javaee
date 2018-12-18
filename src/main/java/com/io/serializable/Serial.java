package com.io.serializable;

import java.io.Serializable;

/**
 * @author wanchongyang
 * @date 2018-11-30 18:05
 */
public class Serial implements Serializable {
    // private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;

    public Serial(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Serial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
