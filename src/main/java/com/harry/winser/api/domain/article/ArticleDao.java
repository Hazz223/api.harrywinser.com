package com.harry.winser.api.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

    Page<Article> findAll(Pageable pageable);

    Optional<Article> findByTitleIgnoreCaseOrCleanTitleIgnoreCase(String term, String cleanTitle);

    Optional<Article> findById(Long id);

    Page<Article> findByType(String type, Pageable pageable);

    Page<Article> findByTitleContainingIgnoreCase(String term, Pageable pageable);

    Page<Article> findByCleanTitleContainingIgnoreCase(String term, Pageable pageable);

    Page<Article> findByDataContainingIgnoreCase(String term, Pageable page);

    Page<Article> findByTypeInOrderByCreateDateDesc(List<String> terms, Pageable pageable);

}
