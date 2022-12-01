package ru.se4oev.mysite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.se4oev.mysite.service.ArticleService;
import ru.se4oev.mysite.Page;

/**
 * Created by karpenko on 29.11.2022.
 * Description:
 */
@Controller
@RequestMapping
public class HomeController {

    ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home() {
        return "html/home";
    }

    @GetMapping("/main")
    public String index() {
        return "html/index";
    }

    @GetMapping("/core")
    public ResponseEntity<Page> spa(@RequestParam String page,
                                    @RequestParam(required = false) Integer id) {
        return articleService.spa(page, id);
    }

}
