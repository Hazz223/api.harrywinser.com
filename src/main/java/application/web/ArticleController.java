package application.web;


import application.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = "/article/{id}")
    public ArticleDto getArticleByIdentifier(@PathVariable("id") String id){

        return this.articleService.getArticleByIdentifier(id);

    }

}
