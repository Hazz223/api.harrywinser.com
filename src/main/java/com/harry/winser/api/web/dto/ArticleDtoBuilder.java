package com.harry.winser.api.web.dto;

import java.util.Date;

/**
 * Created by Windfall on 01/10/2016.
 */
public final class ArticleDtoBuilder {
    private Long id;
    private Date date; // TODO: Why is this a String?! What's wrong with you Harry???
    private String type;
    private String title;
    private String cleanTitle;
    private String data;

    private ArticleDtoBuilder() {
    }

    public static ArticleDtoBuilder anArticleDto() {
        return new ArticleDtoBuilder();
    }

    public ArticleDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ArticleDtoBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public ArticleDtoBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ArticleDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleDtoBuilder withCleanTitle(String cleanTitle) {
        this.cleanTitle = cleanTitle;
        return this;
    }

    public ArticleDtoBuilder withData(String data) {
        this.data = data;
        return this;
    }

    public ArticleDto build() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(id);
        articleDto.setDate(date);
        articleDto.setType(type);
        articleDto.setTitle(title);
        articleDto.setCleanTitle(cleanTitle);
        articleDto.setData(data);
        return articleDto;
    }
}
