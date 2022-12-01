package ru.se4oev.mysite;

/**
 * Created by karpenko on 29.11.2022.
 * Description:
 */
public class Page {

    private String title;
    private String body;

    public Page() {
    }

    public Page(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
