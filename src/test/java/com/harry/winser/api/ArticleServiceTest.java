package com.harry.winser.api;


import com.harry.winser.api.domain.article.Article;
import com.harry.winser.api.domain.article.ArticleBuilder;
import com.harry.winser.api.domain.article.ArticleDao;
import com.harry.winser.api.services.ArticleDateComparator;
import com.harry.winser.api.services.ArticleService;
import com.harry.winser.api.services.ArticleServiceImpl;
import com.harry.winser.api.services.ArticleType;
import com.harry.winser.api.services.converters.ArticleToDtoConverter;
import com.harry.winser.api.services.converters.CreateArticleToArticleConverter;
import com.harry.winser.api.services.exceptions.ArticleNotFoundException;
import com.harry.winser.api.web.dto.ArticleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    @Mock
    private ArticleDao articleDao;

    private ArticleService articleService;
    private ArticleToDtoConverter articleToDtoConverter = new ArticleToDtoConverter();
    private CreateArticleToArticleConverter createArticleToArticleConverter = new CreateArticleToArticleConverter();
    private ArticleDateComparator articleDateComparator = new ArticleDateComparator();
    private Article firstArticle;
    private String firstArticleCreateDate;


    @Before
    public void init() {

        this.articleService = new ArticleServiceImpl(
                this.articleDao,
                this.articleToDtoConverter,
                this.createArticleToArticleConverter,
                this.articleDateComparator);

        Date firstArticleDate = new Date();
        this.firstArticle = ArticleBuilder.anArticle()
                .withType(ArticleType.blog)
                .withTitle("Article 1")
                .withData("This is the first Article")
                .withCreateDate(firstArticleDate)
                .withCleanTitle("Article_1")
                .withId(1L)
                .build();

        this.firstArticleCreateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(firstArticleDate);
    }

    private void givenNoArticles(){

        when(this.articleDao.findById(eq(firstArticle.getId()))).thenReturn(Optional.empty());
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq("1"), eq("1"))).thenReturn(Optional.empty());
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq(firstArticle.getTitle()), eq(firstArticle.getTitle()))).thenReturn(Optional.empty());
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq(firstArticle.getCleanTitle()), eq(firstArticle.getCleanTitle()))).thenReturn(Optional.empty());
    }

    private void givenASavedArticle() {

        when(this.articleDao.findById(eq(firstArticle.getId()))).thenReturn(Optional.of(firstArticle));
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq("1"), eq("1"))).thenReturn(Optional.empty());
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq(firstArticle.getTitle()), eq(firstArticle.getTitle()))).thenReturn(Optional.of(firstArticle));
        when(this.articleDao.findByTitleIgnoreCaseOrCleanTitleIgnoreCase(eq(firstArticle.getCleanTitle()), eq(firstArticle.getCleanTitle()))).thenReturn(Optional.of(firstArticle));
    }

    @Test
    public void shouldGetArticleDtoByCleanTitle() {

        this.givenASavedArticle();

        ArticleDto result = this.articleService.getArticleByIdentifier(this.firstArticle.getCleanTitle());

        assertThat(result.getCleanTitle()).isEqualTo(this.firstArticle.getCleanTitle());
        assertThat(result.getTitle()).isEqualTo(this.firstArticle.getTitle());
        assertThat(result.getData()).isEqualTo(this.firstArticle.getData());
        assertThat(result.getType()).isEqualTo(this.firstArticle.getType().toString());
        assertThat(result.getId()).isEqualTo(this.firstArticle.getId());
        assertThat(result.getDate()).isEqualTo(this.firstArticleCreateDate);
    }

    @Test
    public void shouldGetArticleDtoByTitle() {

        this.givenASavedArticle();

        ArticleDto result = this.articleService.getArticleByIdentifier(this.firstArticle.getTitle());

        assertThat(result.getCleanTitle()).isEqualTo(this.firstArticle.getCleanTitle());
        assertThat(result.getTitle()).isEqualTo(this.firstArticle.getTitle());
        assertThat(result.getData()).isEqualTo(this.firstArticle.getData());
        assertThat(result.getType()).isEqualTo(this.firstArticle.getType().toString());
        assertThat(result.getId()).isEqualTo(this.firstArticle.getId());
        assertThat(result.getDate()).isEqualTo(this.firstArticleCreateDate);
    }

    @Test
    public void shouldGetArticleDtoById() {

        this.givenASavedArticle();

        ArticleDto result = this.articleService.getArticleByIdentifier(String.valueOf(this.firstArticle.getId()));

        assertThat(result.getCleanTitle()).isEqualTo(this.firstArticle.getCleanTitle());
        assertThat(result.getTitle()).isEqualTo(this.firstArticle.getTitle());
        assertThat(result.getData()).isEqualTo(this.firstArticle.getData());
        assertThat(result.getType()).isEqualTo(this.firstArticle.getType().toString());
        assertThat(result.getId()).isEqualTo(this.firstArticle.getId());
        assertThat(result.getDate()).isEqualTo(this.firstArticleCreateDate);
    }

    @Test
    public void shouldThrowExceptionWhenNoArticleFoundForGivenId(){

        this.givenNoArticles();

        try{
            this.articleService.getArticleByIdentifier(String.valueOf(this.firstArticle.getId()));
            assertTrue("should have thrown exception", false);
        }catch(ArticleNotFoundException ex){

            assertThat(ex.getMessage()).contains("No article found for given term");
        }
    }

    @Test
    public void shouldThrowExceptionWhenNoArticleFoundForGivenTerm(){

        this.givenNoArticles();

        try{
            this.articleService.getArticleByIdentifier(this.firstArticle.getTitle());
            assertTrue("should have thrown exception", false);
        }catch(ArticleNotFoundException ex){

            assertThat(ex.getMessage()).contains("No article found for given term");
        }
    }
}
