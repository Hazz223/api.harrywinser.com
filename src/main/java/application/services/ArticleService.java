package application.services;

import application.web.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    ArticleDto getArticleByIdentifier(String id);

    Page<ArticleDto> getArticlesByType(String type, Pageable pageable);

    Page<ArticleDto> searchForArticles(String searchTerm, Pageable pageable);

}
