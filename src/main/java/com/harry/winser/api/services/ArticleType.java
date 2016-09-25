package com.harry.winser.api.services;


public enum ArticleType {

    technology("technology"),
    blog("blog"),
    review("review");

    private String type;

    private ArticleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
