package com.ism.services.impl;

import java.util.List;

import com.ism.entities.Article;
import com.ism.repository.list.ArticleRepositoryList;

public class ArticlesServiceImpl {
    private ArticleRepositoryList articleRepository = new ArticleRepositoryList();

    public void createArticle(Article article) {
        articleRepository.insert(article);
    }

    public List<Article> findAll() {
        return articleRepository.selectAll();
    }

    public void updateStock(String nom, int quantite) {
        articleRepository.updateStock(nom, quantite);
    }

    public List<Article> filterAvailableArticles() {
        return articleRepository.filterByDisponibility();
    }

    public void updateQuantiteStock(Article article, int quantite) {
        articleRepository.updateQuantiteStock(article, quantite);
    }
}
