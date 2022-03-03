package com.lb;

/**
 * @author wanchongyang
 * @date 2022/3/2 5:53 PM
 */
public class Node implements Comparable<Node> {
    protected String url;

    protected Integer weight;

    protected Integer active;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
