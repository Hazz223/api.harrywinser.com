package com.harry.winser.api.web;


import com.harry.winser.api.exceptions.ResourceNotFoundException;
import com.harry.winser.api.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/article/{id}")
    public ArticleDto getArticleByIdentifier(@PathVariable("id") String id){

        return this.articleService.getArticleByIdentifier(id);

    }

    @RequestMapping(value = "/article/type/{term}")
    public Page<ArticleDto> getArticlesByType(@PathVariable("term") String term, Pageable pageable){

        if(term == null || term.isEmpty()) {
            throw new ResourceNotFoundException("Type cannot be null or empty");
        }

        if(!"blog".equalsIgnoreCase(term) && !"technology".equalsIgnoreCase(term) & !"review".equalsIgnoreCase(term)){
            throw new ResourceNotFoundException("Type given was not found. Valid types are 'blog', 'technology', and 'review'");
        }

        return this.articleService.getArticlesByType(term, pageable);
    }

    @RequestMapping(value = "/article/type")
    public Page<ArticleDto> searchForType(@RequestParam("search") List<String> type, Pageable pageable){

        return this.articleService.searchForArticlesByTypes(type, pageable);
    }

    @RequestMapping(value = "/article")
    public Page<ArticleDto> searchForArticle(@RequestParam("search") String searchTerm, Pageable pageable){

        return this.articleService.searchForArticles(searchTerm, pageable);
    }
}
