package com.tedtalk.domain.utilitypojo;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class TedTalkCSV {
    @CsvBindByName(column = "title")
    String title;

    @CsvBindByName(column = "author")
    String author;

    @CsvBindByName(column = "date")
    String date;

    @CsvBindByName(column = "views")
    String views;

    @CsvBindByName(column = "likes")
    String likes;

    @CsvBindByName(column = "link")
    String link;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "TedTalkCSV{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", views='" + views + '\'' +
                ", likes='" + likes + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
