package dev.lueem.ai;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import dev.lueem.model.Article;
import java.util.List;

public class OpenAiFunctions {

    public static class ArticlesRequest {

        @JsonProperty(required = true)
        @JsonPropertyDescription("Der Text, aus dem die Artikel extrahiert werden sollen")
        private String text;

        // Standardkonstruktor
        public ArticlesRequest() {
        }

        public ArticlesRequest(String text) {
            this.text = text;
        }

        // Getter und Setter
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ArticlesResponse {

        @JsonPropertyDescription("Die Liste der extrahierten Artikel")
        private List<Article> articles;

        public ArticlesResponse() {
        }

        public ArticlesResponse(List<Article> articles) {
            this.articles = articles;
        }

        // Getter und Setter
        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }

}
