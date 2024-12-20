package com.ism.entities;

import lombok.Data;

@Data
public class Detail {
    private Article Article;
    private int Quantite;


    
    public Detail(Article article, int quantite) {
        this.Article = article;
        this.Quantite = quantite;
    }
    
    @Override
    public String toString() {
        return "Detail [article=" + Article + ", quantite=" + Quantite + "]";
    }
}
