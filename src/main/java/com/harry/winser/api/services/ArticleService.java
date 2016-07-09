package com.harry.winser.api.services;

import com.harry.winser.api.web.dto.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    ArticleDto getArticleByIdentifier(String id);

    Page<ArticleDto> getArticlesByType(String type, Pageable pageable);

    Page<ArticleDto> searchForArticles(String searchTerm, Pageable pageable);

    Page<ArticleDto> searchForArticlesByTypes(List<String> type, Pageable pageable);

}
