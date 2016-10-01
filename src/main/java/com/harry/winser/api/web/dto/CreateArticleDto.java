package com.harry.winser.api.web.dto;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateArticleDto {

    @Valid
    @NotNull
    @Min(3)
    private String type;
    @NotNull
    @Min(3)
    private String title;
    @NotNull
    @Min(3)
    private String cleanTitle;
    @NotNull
    @Min(3)
    private String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCleanTitle() {
        return cleanTitle;
    }

    public void setCleanTitle(String cleanTitle) {
        this.cleanTitle = cleanTitle;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
