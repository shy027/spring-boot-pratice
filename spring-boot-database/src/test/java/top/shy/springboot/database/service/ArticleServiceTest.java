package top.shy.springboot.database.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.shy.springboot.database.entity.Article;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ArticleServiceTest {
    @Resource
    private ArticleService articleService;

    @Test
    void saveArticle() {
        Article article = Article.builder()
                .title("springBoot")
                .author("张三")
                .content("springBoot 从入门到精通")
                .build();
        Article savedArticle = articleService.saveArticle(article);
        log.info(String.valueOf(savedArticle));
    }

    @Test
    void updateArticle() {
        Article article = articleService.getArticle(3L);
        article.setTitle("springBoot123");
        articleService.updateArticle(article);
    }

    @Test
    void getArticle() {
        Article article = articleService.getArticle(3L);
        log.info(String.valueOf(article));
    }

    @Test
    void getAll(){
        List<Article> articles = articleService.getAll();
        articles.forEach(article -> log.info(String.valueOf(article)));
    }

    @Test
    void deleteArticle(){
        articleService.deleteArticle(3L);
    }

}