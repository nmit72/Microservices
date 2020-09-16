package io.microservices.models;

import java.util.List;

public class RatingList {

    List<Rating> rating;

    public RatingList() {
    }
    public RatingList(List<Rating> rating) {
        this.rating = rating;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }
}
