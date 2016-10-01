package com.harry.winser.api.services;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.web.dto.ArticleDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

public class ArticleDtoDateComparator implements Comparator<ArticleDto> {

    @Override
    public int compare(ArticleDto o1, ArticleDto o2) {

        int result;

        if ((o1 == null || o2 == null) || (o1.getDate() == null || o2.getDate() == null)) {
            result = 0;
        } else {

            result = o2.getDate().compareTo(o1.getDate());
        }

        return result;
    }
}
