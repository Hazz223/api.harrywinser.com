package com.harry.winser.api.domain.article;

import com.harry.winser.api.services.ArticleType;

import java.util.Date;

/**
 * Created by Windfall on 25/09/2016.
 */
public final class ArticleBuilder {
    private long id;
    private Date createDate;
    private ArticleType type;
    private String title;
    private String cleanTitle;
    private String data;

    private ArticleBuilder() {
    }

    public static ArticleBuilder anArticle() {
        return new ArticleBuilder();
    }

    public ArticleBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ArticleBuilder withCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public ArticleBuilder withType(ArticleType type) {
        this.type = type;
        return this;
    }

    public ArticleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleBuilder withCleanTitle(String cleanTitle) {
        this.cleanTitle = cleanTitle;
        return this;
    }

    public ArticleBuilder withData(String data) {
        this.data = data;
        return this;
    }

    public Article build() {
        Article article = new Article();
        article.setId(id);
        article.setCreateDate(createDate);
        article.setType(type);
        article.setTitle(title);
        article.setCleanTitle(cleanTitle);
        article.setData(data);
        return article;
    }
}
