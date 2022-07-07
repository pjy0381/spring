package com.example.firstProject.controller;

import com.example.firstProject.dto.ArticleForm;
import com.example.firstProject.dto.CommentDto;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import com.example.firstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String viewArtilce(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        // 1. DTO를 변환! Entity!
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        model.addAttribute("article",article);
        model.addAttribute("commentDtos", commentDtos);

        return "/articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleList = articleRepository.findAll();

        model.addAttribute("articleList",articleList);

        return "/articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String update(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        Article articleEntity = form.toEntity();

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id){
        log.info("삭제 요청이 들어왔습니다");

        Article target = articleRepository.findById(id).orElse(null);
        if(target != null){
            articleRepository.delete(target);
        }

        return "redirect:/articles";
    }
}
