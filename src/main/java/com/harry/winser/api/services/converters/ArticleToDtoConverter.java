package com.harry.winser.api.services.converters;

import com.harry.winser.api.domain.Article;
import com.harry.winser.api.web.ArticleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by harry on 30/03/2016.
 */
@Component
public class ArticleToDtoConverter implements Converter<Article, ArticleDto> {

    @Override
    public ArticleDto convert(Article source) {

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(source.getId());
        articleDto.setCleanTitle(source.getCleanTitle());
        articleDto.setTitle(source.getTitle());
        articleDto.setType(source.getType());
        articleDto.setData(source.getData());
        articleDto.setDate(source.getCreateDate());

        return articleDto;
    }
}
