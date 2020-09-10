package com.charles.gads2020leaderboard.modal;

import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class LearnerInfo{
    private String name;
    private int hours;
    private int score;
    private String country;
    private String badgeUrl;

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "LearnerInfo{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", hours=" + score +
                ", country='" + country + '\'' +
                ", badgeUrl=" + badgeUrl +
                '}';
    }
}
