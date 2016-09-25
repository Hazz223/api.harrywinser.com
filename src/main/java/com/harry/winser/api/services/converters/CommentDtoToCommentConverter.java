package com.harry.winser.api.services.converters;

import com.harry.winser.api.domain.comment.Comment;
import com.harry.winser.api.domain.comment.CommentBuilder;
import com.harry.winser.api.web.dto.CommentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoToCommentConverter implements Converter<CommentDto, Comment> {


    @Override
    public Comment convert(CommentDto source) {

        return CommentBuilder.aComment()
                .withArticleTitle(source.getTitle())
                .withComment(source.getComment())
                .withCreateDate(source.getCreateDate())
                .withUserName(source.getUserName())
                .withGuid(java.util.UUID.randomUUID().toString())
                .build();
    }
}
