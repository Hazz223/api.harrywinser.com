package com.harry.winser.api.web;


import com.harry.winser.api.exceptions.ResourceNotFoundException;
import com.harry.winser.api.services.ArticleService;
import com.harry.winser.api.services.exceptions.ArticleNotFoundException;
import com.harry.winser.api.services.exceptions.ArticleServiceException;
import com.harry.winser.api.web.dto.ArticleDto;
import com.harry.winser.api.web.dto.CreateArticleDto;
import com.harry.winser.api.web.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/article/{id}")
    public ArticleDto getArticleByIdentifier(@PathVariable("id") String id) {

        return this.articleService.getArticleByIdentifier(id);
    }

    @RequestMapping(value = "/article/type/{term}")
    public Page<ArticleDto> getArticlesByType(@PathVariable("term") String term, Pageable pageable) {

        if (term == null || term.isEmpty()) {
            throw new ResourceNotFoundException("Type cannot be null or empty");
        }

        if (!"blog".equalsIgnoreCase(term) && !"technology".equalsIgnoreCase(term) & !"review".equalsIgnoreCase(term)) {
            throw new ResourceNotFoundException("Type given was not found. Valid types are 'blog', 'technology', and 'review'");
        }

        return this.articleService.getArticlesByType(term, pageable);
    }

    // todo: Search for articles by type. Basically the ones above cover this. This is why this is kinda dumb.
    @RequestMapping(value = "/article/type")
    public Page<ArticleDto> searchForType(@RequestParam("search") List<String> type, Pageable pageable) {

        return this.articleService.searchForArticlesByTypes(type, pageable);
    }

    @RequestMapping(value = "/article")
    public Page<ArticleDto> searchForArticle(@RequestParam(value = "search", required = false) String searchTerm, Pageable pageable) {

        Page<ArticleDto> page;

        if(Objects.isNull(searchTerm) || searchTerm.isEmpty()){
            page = this.articleService.getAllArticles(pageable);
        } else {
            page = this.articleService.searchForArticles(searchTerm, pageable);
        }

        return page;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({ArticleNotFoundException.class})
    public ErrorDto articleNotFound(Exception ex) {

        return new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ArticleServiceException.class})
    public ErrorDto interalServerError(Exception ex) {

        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
