package com.harry.winser.api.services.converters;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.domain.article.ArticleBuilder;
import com.harry.winser.api.web.dto.CreateArticleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateArticleToArticleConverter implements Converter<CreateArticleDto, Article> {

    @Override
    public Article convert(CreateArticleDto source) {

        return ArticleBuilder.anArticle()
                .withCleanTitle(source.getCleanTitle())
                .withCreateDate(new Date())
                .withData(source.getData())
                .withTitle(source.getTitle())
                .withType(source.getType())
                .build();
    }
}
