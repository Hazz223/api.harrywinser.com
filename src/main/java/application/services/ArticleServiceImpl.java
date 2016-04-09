package application.services;

import application.domain.Article;
import application.domain.ArticleDao;
import application.services.converters.ArticleToDtoConverter;
import application.web.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;
    private ArticleToDtoConverter dtoConverter;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, ArticleToDtoConverter articleToDtoConverter) {
        this.articleDao = articleDao;
        this.dtoConverter = articleToDtoConverter;
    }

    @Override
    public ArticleDto getArticleByIdentifier(String term) {

        Long searchId = this.convertSearchTermToLong(term);

        Optional<Article> articleOptional =
                this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCaseOrId(term, term, searchId);

        Article article = articleOptional.orElseThrow(() -> new RuntimeException("Article not found"));

        return this.dtoConverter.convert(article);
    }

    @Override
    public Page<ArticleDto> getArticlesByType(String type, Pageable pageable) {

        Page<Article> pageArticles;

        if("all".equalsIgnoreCase(type)){
            pageArticles = this.articleDao.findAll(pageable);
        }

        pageArticles = this.articleDao.findByType(type, pageable);

        List<ArticleDto> result = pageArticles.getContent().stream()
                        .map(this.dtoConverter::convert)
                        .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, pageArticles.getTotalElements());
    }

    @Override
    public Page<ArticleDto> searchForArticles(String searchTerm, Pageable pageable) {

        Page<Article> titleArticles = this.articleDao.findByTitleContainingIgnoreCase(searchTerm, pageable);
        Page<Article> cleanTitleArticles = this.articleDao.findByCleanTitleContainingIgnoreCase(searchTerm, pageable);
        Page<Article> dataArticles = this.articleDao.findByDataContainingIgnoreCase(searchTerm, pageable);


        List<Article> pageArticles = new ArrayList<>();

        pageArticles.addAll(titleArticles.getContent());
        pageArticles.addAll(cleanTitleArticles.getContent());
        pageArticles.addAll(dataArticles.getContent());

        Long totalElements =
                titleArticles.getTotalElements() + cleanTitleArticles.getTotalElements() + dataArticles.getTotalElements();


        List<ArticleDto> result = pageArticles.stream()
                .map(this.dtoConverter::convert)
                .collect(Collectors.toList());


        return new PageImpl<>(result, pageable, totalElements);
    }

    private Long convertSearchTermToLong(String searchTerm){

        Long result = 0L;

        try{
            result = Long.valueOf(searchTerm);
        }catch(NumberFormatException ex){
            // Should log here
        }

        return result;
    }
}
