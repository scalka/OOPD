package com.scalka.movieswebservices.model;

import android.graphics.Bitmap;
//Model for a single movie
public class Movie {

    private String title;
    private String directors;
    private double rating;
    private int year;
    private String genres;
    private String photo;
    private Bitmap bitmap; //member of the Android.graphics package

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Bitmap getBitmap(){
        return bitmap;
    };
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
}
