package com.app.materiel.Controllers;

import com.app.materiel.Entity.Article;
import com.app.materiel.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;




    @GetMapping("/article/new")
    public String pagenewarticle(Model model){
        model.addAttribute("article", new Article());
        return "article-new";
    }

    @GetMapping("/article/list")
    public String pagelistarticle(Model model){

        List<Article> articles = articleService.findArticle();
        model.addAttribute("articles", articles);

        return "article-list";
    }


    @PostMapping("/addarticle")
    public String addMedication(@ModelAttribute Article article, RedirectAttributes redirectAttributes) {

        articleService.addArticle(article);
        redirectAttributes.addFlashAttribute("articleajouter", "Article ajouté avec succès au stock !");
        return "redirect:/article/new";
    }
}
