package com.harry.winser.api.services;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.web.dto.ArticleDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;

public class ArticleDateComparator implements Comparator<ArticleDto>{

    @Override
    public int compare(ArticleDto o1, ArticleDto o2) {
        if (o1.getDate() == null || o2.getDate() == null)
            return 0;
        return o2.getDate().compareTo(o1.getDate());
    }
}
