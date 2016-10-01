package com.harry.winser.api.services.converters;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.web.dto.ArticleDto;
import com.harry.winser.api.web.dto.ArticleDtoBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleToDtoConverter implements Converter<Article, ArticleDto> {

    @Override
    public ArticleDto convert(Article source) {

        return ArticleDtoBuilder.anArticleDto()
                .withId(source.getId())
                .withCleanTitle(source.getCleanTitle())
                .withTitle(source.getTitle())
                .withType(source.getType())
                .withData(source.getData())
                .withDate(source.getCreateDate())
                .build();
    }
}
