package com.jejuPublicWifi.Explore.payload.response;

import java.time.LocalDate;

public class ReviewResponse {
    private Long id;

    private String username;

    private String email;

    private LocalDate date;

    private Integer score;

    private String detail;

    public ReviewResponse(Long id, String username, String email, LocalDate date, Integer score, String detail) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.date = date;
        this.score = score;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
