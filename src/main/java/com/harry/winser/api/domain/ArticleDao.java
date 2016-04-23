package com.harry.winser.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

    Optional<Article> findByTitleIgnoreCaseOrCleanTitleIgnoreCaseOrId(String term, String cleanTitle, Long id);

    Page<Article> findByTitleContainingIgnoreCaseOrCleanTitleContainingIgnoreCaseOrDataContainingIgnoreCase(String term, String cleanTitle, String data, Pageable pageable);

    Page<Article> findByType(String type, Pageable pageable);

    Page<Article> findByTitleContainingIgnoreCase(String term, Pageable pageable);

    Page<Article> findByCleanTitleContainingIgnoreCase(String term, Pageable pageable);

    Page<Article> findByDataContainingIgnoreCase(String term, Pageable page);

    Page<Article> findByTypeInOrderByCreateDateDesc(List<String> terms, Pageable pageable);

}
