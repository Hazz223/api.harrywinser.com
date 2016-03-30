package application.services;

import application.domain.Article;
import application.domain.ArticleDao;
import application.services.converters.ArticleToDtoConverter;
import application.web.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return null;
    }

    @Override
    public Page<ArticleDto> getArticlesByName(String name, Pageable pageable) {
        return null;
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
