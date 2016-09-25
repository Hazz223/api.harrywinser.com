package com.harry.winser.api.domain.article;

import com.harry.winser.api.services.ArticleType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ArticleType type;

    private String title;
    private String cleanTitle;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
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
