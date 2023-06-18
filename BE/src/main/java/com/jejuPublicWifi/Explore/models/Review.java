package com.jejuPublicWifi.Explore.models;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID

    @NotBlank
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDate date; // 날짜

    @NotBlank
    @Range(min=1,max=5)
    private Integer score; // 점수
    private String detail; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wifi_id")
    private Wifi wifi;

    public Review() {
    }

    public Review(LocalDate date, Integer score, String detail, User user, Wifi wifi ) {
        this.date = date;
        this.score = score;
        this.detail = detail;
        this.user = user;
        this.wifi = wifi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
    }


}
