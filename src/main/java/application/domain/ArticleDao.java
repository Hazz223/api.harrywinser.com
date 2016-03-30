package application.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

    Optional<Article> findByTitleIgnoreCaseOrCleanTitleIgnoreCaseOrId(String term, String cleanTitle, Long id);

    Page<Article> findByTitleContainingIgnoreCaseOrCleanTitleContainingIgnoreCaseOrDataContainingIgnoreCase(String term, String cleanTitle, String data, Pageable pageable);

    Page<Article> findByType(String type, Pageable pageable);

}
