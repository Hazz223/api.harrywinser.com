package application.web;


import application.domain.Article;
import application.exceptions.ResourceNotFoundException;
import application.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        if(!"blog".equalsIgnoreCase(term) && !"tech".equalsIgnoreCase(term) & !"review".equalsIgnoreCase(term)){
            throw new ResourceNotFoundException("Type given was not found. Valid types are 'blog', 'tech', and 'review'");
        }

        return this.articleService.getArticlesByType(term, pageable);
    }

}
