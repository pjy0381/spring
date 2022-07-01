package com.example.firstProject.service;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;


    @Test
    void index() {
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        Article article = articleService.show(id);

        assertEquals(expected.toString(), article.toString());
    }
    
    @Test
    void show_실패__id() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공__title__content() {
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article article = articleService.create(dto);

        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void create_실패() {
        Long id = -1L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_2개() {
        Long id = 1L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(1L, title, content);
        Article expected = new Article(id, title, content);

        Article article = articleService.update(id,dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_title() {
        Long id = 1L;
        String title = "라라라라";
        ArticleForm dto = new ArticleForm(1L, title, null);
        Article expected = new Article(id, title, null);

        Article article = articleService.update(id,dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_cotent() {
        Long id = 1L;
        String content = "4444";
        ArticleForm dto = new ArticleForm(1L, null, content);
        Article expected = new Article(id, null, content);

        Article article = articleService.update(id,dto);

        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void update_실패() {
        Long id = -1L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(-1L, title, content);
        Article expected = null;

        Article article = articleService.update(id,dto);

        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        Article article = articleService.delete(id);

        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void delete_실패_id() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.delete(id);

        assertEquals(expected, article);
    }

    @Test
    void createArticles() {
    }
}