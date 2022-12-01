package ru.se4oev.mysite.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.se4oev.mysite.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karpenko on 30.11.2022.
 * Description:
 */
@Service
public class ArticleService {

    List<Page> articles = new ArrayList<>(Arrays.asList(
            new Page("Article 1", "<p>asdas 1</p> <a href='/main' class='link link_internal'>Return to main page</a>"),
            new Page("Article 2", "<p>asdas 2</p> <a href='/main' class='link link_internal'>Return to main page</a>"),
            new Page("Article 3", "<p>asdas 3</p> <a href='/main' class='link link_internal'>Return to main page</a>"),
            new Page("Article 4", "<p>asdas 4</p> <a href='/main' class='link link_internal'>Return to main page</a>")
    ));

    Page mainPage = new Page(
            "Main",
            "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut " +
                    "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                    "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                    "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                    "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p> " +
                    "<a href='/articles/1' class='link link_internal'>Article 1</a><br>" +
                    "<a href='/articles/2' class='link link_internal'>Article 2</a><br>" +
                    "<a href='/articles/3' class='link link_internal'>Article 3</a><br>" +
                    "<a href='/articles/4' class='link link_internal'>Article 4</a><br>");
    Page notFoundPage = new Page("404", "<p>Page not found</p> <a href='/main' class='link link_internal'>Return to main page</a>");

    public ResponseEntity<Page> spa(String page, Integer id) {
        if (page == null)
            page = "404";
        if (id == null)
            id = 0;

        if (page.equals("main"))
            return new ResponseEntity<>(mainPage, HttpStatus.OK);

        if (page.equals("articles"))
            if (id > 0)
                if (articles.size() >= id)
                    return new ResponseEntity<>(articles.get(id - 1), HttpStatus.OK);

        return new ResponseEntity<>(notFoundPage, HttpStatus.NOT_FOUND);
    }

}
