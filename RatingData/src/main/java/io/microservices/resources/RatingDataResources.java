package io.microservices.resources;

import io.microservices.models.Rating;
import io.microservices.models.RatingList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResources {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("/users/{userId}")
    public RatingList getMovieInfo(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("Nitin1234", 14),
                new Rating("Nitin5678", 3));
        return new RatingList(ratings);
    }
}
