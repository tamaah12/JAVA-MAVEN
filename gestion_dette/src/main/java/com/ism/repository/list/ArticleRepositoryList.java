package com.ism.repository.list;

import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Article;

public class ArticleRepositoryList {
    private List<Article> articles = new ArrayList<>();

    public void insert(Article article) {
        articles.add(article);
    }

    public List<Article> selectAll() {
        return articles;
    }

    public List<Article> filterByDisponibility() {
        return articles.stream()
                .filter(article -> article.getQuantiteEnStock() > 0)
                .toList();
    }

    public void updateQuantiteStock(Article article, int quantite) {
        article.setQuantiteEnStock(quantite);
    }

    public void updateStock(String nom, int quantite) {
        for (Article article : articles) {
            if (article.getNom().equals(nom)) {
                int newQuantite = article.getQuantiteEnStock() + quantite;
                article.setQuantiteEnStock(newQuantite);
                break;
            }
        }
    }
}
