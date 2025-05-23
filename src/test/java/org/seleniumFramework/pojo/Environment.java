package org.seleniumFramework.pojo;

public class Environment {

    private String url;
    private int MAX_RETRY_COUNT;

    public int getMAX_RETRY_COUNT() {
        return MAX_RETRY_COUNT;
    }

    public void setMAX_RETRY_COUNT(int MAX_RETRY_COUNT) {
        this.MAX_RETRY_COUNT = MAX_RETRY_COUNT;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
