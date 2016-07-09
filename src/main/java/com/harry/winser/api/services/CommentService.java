package com.harry.winser.api.services;


import com.harry.winser.api.web.dto.CommentDto;

import java.util.Set;

public interface CommentService {

    void saveComment(CommentDto comment);

    Set<CommentDto> getCommentsByArticleTitle(String articleTitle);

}
