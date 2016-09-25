package com.harry.winser.api.services.converters;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.web.dto.ArticleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleToDtoConverter implements Converter<Article, ArticleDto> {

    @Override
    public ArticleDto convert(Article source) {

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(source.getId());
        articleDto.setCleanTitle(source.getCleanTitle());
        articleDto.setTitle(source.getTitle());
        articleDto.setType(source.getType().toString().toLowerCase());
        articleDto.setData(source.getData());
        articleDto.setDate(source.getCreateDate());

        return articleDto;
    }
}
