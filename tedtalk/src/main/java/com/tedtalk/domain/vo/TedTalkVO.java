package com.tedtalk.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TedTalkVO {
    Long id;
    @NotEmpty
    @JsonProperty("title")
    String title;

    @NotEmpty
    @JsonProperty("author")
    String author;

    @JsonProperty("date")
    LocalDate date;
    @JsonProperty("views")
    Long views;
    @JsonProperty("likes")
    Long likes;
    @JsonProperty("link")
    String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
