package com.app.materiel.Service;

import com.app.materiel.Entity.Article;
import com.app.materiel.Repository.ArticleReposirtory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleReposirtory articleReposirtory;

    public void addArticle(Article article){
        articleReposirtory.save(article);
    }
    public List<Article> findArticle(){
        return articleReposirtory.findAll();
    }

}
