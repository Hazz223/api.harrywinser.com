package com.harry.winser.api.domain.comment;

import java.util.Date;

/**
 * Created by harry on 09/07/2016.
 */
public final class CommentBuilder {
    private Long id;
    private String guid;
    private String userName;
    private String comment;
    private Date createDate;
    private String articleTitle;

    private CommentBuilder() {
    }

    public static CommentBuilder aComment() {
        return new CommentBuilder();
    }

    public CommentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder withGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public CommentBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public CommentBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommentBuilder withCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public CommentBuilder withArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
        return this;
    }

    public Comment build() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setGuid(guid);
        comment.setUserName(userName);
        comment.setComment(comment);
        comment.setCreateDate(createDate);
        comment.setArticleTitle(articleTitle);
        return comment;
    }
}
