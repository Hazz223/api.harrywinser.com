package com.harry.winser.api.services;

import com.harry.winser.api.domain.comment.Comment;
import com.harry.winser.api.domain.comment.CommentDao;
import com.harry.winser.api.services.converters.CommentDtoToCommentConverter;
import com.harry.winser.api.web.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDtoToCommentConverter dtoConverter;
    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDtoToCommentConverter dtoConverter, CommentDao commentDao) {
        this.dtoConverter = dtoConverter;
        this.commentDao = commentDao;
    }

    @Override
    public void saveComment(CommentDto dto) {

        Comment comment = this.dtoConverter.convert(dto);

        this.commentDao.save(comment);
    }

    @Override
    public Set<CommentDto> getCommentsByArticleTitle(String articleTitle) {
        // i really need lunch...
        return null;
    }
}
