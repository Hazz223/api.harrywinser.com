package com.harry.winser.api.services;

import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.domain.article.ArticleDao;
import com.harry.winser.api.services.converters.ArticleToDtoConverter;
import com.harry.winser.api.services.converters.CreateArticleToArticleConverter;
import com.harry.winser.api.services.exceptions.ArticleNotFoundException;
import com.harry.winser.api.web.dto.ArticleDto;
import com.harry.winser.api.web.dto.CreateArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;
    private ArticleToDtoConverter dtoConverter;
    private CreateArticleToArticleConverter createArticleToArticleConverter;
    private ArticleDateComparator articleDateComparator;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao,
                              ArticleToDtoConverter dtoConverter,
                              CreateArticleToArticleConverter createArticleToArticleConverter,
                              ArticleDateComparator comparator) {

        this.articleDao = articleDao;
        this.dtoConverter = dtoConverter;
        this.createArticleToArticleConverter = createArticleToArticleConverter;
        this.articleDateComparator = comparator;
    }

    @Override
    public ArticleDto getArticleByIdentifier(String term) {

        Long searchId = this.convertSearchTermToLong(term);
        Optional<Article> articleOpt;

        if (searchId != null) {

            articleOpt = this.articleDao.findById(searchId);
        } else {
            articleOpt = this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(term, term);
        }

        return this.dtoConverter.convert(articleOpt.orElseThrow(() -> new ArticleNotFoundException("No article found for given term: " + term)));
    }

    @Override
    public Page<ArticleDto> getArticlesByType(String type, Pageable pageable) {

        Page<Article> pageArticles;

        pageArticles = this.articleDao.findByType(type, pageable);

        List<ArticleDto> result = pageArticles.getContent().stream()
                .map(this.dtoConverter::convert)
                .collect(Collectors.toList());

        Collections.sort(result, this.articleDateComparator);

        return new PageImpl<>(result, pageable, pageArticles.getTotalElements());
    }

    @Override
    public Page<ArticleDto> searchForArticles(String searchTerm, Pageable pageable) {

        Page<Article> titleArticles = this.articleDao.findByTitleContainingIgnoreCase(searchTerm, pageable);
        Page<Article> cleanTitleArticles = this.articleDao.findByCleanTitleContainingIgnoreCase(searchTerm, pageable);
        Page<Article> dataArticles = this.articleDao.findByDataContainingIgnoreCase(searchTerm, pageable);

        Set<Article> pageArticles = new HashSet<>();

        pageArticles.addAll(titleArticles.getContent());
        pageArticles.addAll(cleanTitleArticles.getContent());
        pageArticles.addAll(dataArticles.getContent());

        Long totalElements =
                titleArticles.getTotalElements() + cleanTitleArticles.getTotalElements() + dataArticles.getTotalElements();


        List<ArticleDto> result = pageArticles.stream()
                .map(this.dtoConverter::convert)
                .collect(Collectors.toList());

        Collections.sort(result, this.articleDateComparator);

        return new PageImpl<>(result, pageable, totalElements);
    }

    @Override
    public Page<ArticleDto> searchForArticlesByTypes(List<String> type, Pageable pageable) {

        Page<Article> pageArticles = this.articleDao.findByTypeInOrderByCreateDateDesc(type, pageable);

        List<ArticleDto> result = pageArticles.getContent().stream()
                .map(this.dtoConverter::convert)
                .collect(Collectors.toList());

        Collections.sort(result, (o1, o2) -> {
            if (o1.getDate() == null || o2.getDate() == null)
                return 0;
            return o2.getDate().compareTo(o1.getDate());
        });

        return new PageImpl<>(result, pageable, pageArticles.getTotalElements());
    }

    @Override
    public void createArticle(CreateArticleDto articleDto) {

        Article newArticle = this.createArticleToArticleConverter.convert(articleDto);

        this.articleDao.save(newArticle);
    }

    private Long convertSearchTermToLong(String searchTerm) {

        Long result = null;

        try {
            result = Long.valueOf(searchTerm);
        } catch (NumberFormatException ex) {
        }

        return result;
    }
}
