package io.microservices.resources;

import io.microservices.models.CatalogItem;
import io.microservices.models.Movie;
import io.microservices.models.RatingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

    // initalization in Spring is Lazy, while creating this class, it will see some object as autowired then it will find and create it once. next
    // time someone call. it is was already initialised So it is assigned
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        // get all rated movie IDs
        RatingList ratings = restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingdata/users/" + userId, RatingList.class);

        // For each movie ID call movie service and get details
        // put them all together
        return ratings.getRating().stream().map(rating -> {
            // one bad thing is hardcoding is done, which need to put in some properties files
            Movie movie = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/" + rating.getMovieId(), Movie.class);

          //use of WebClient example, as it is abit verbose, because it is using asynchronous programming, we using block method to make it as sync programe
          // it is coming from Webflux : another module in Spring boot.
          // all type of method are available get, post, put, delete
           /* Movie movie =  webClient.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/


            return  new CatalogItem(movie.getName(),"Desc",rating.getRating());
        }).collect(Collectors.toList());


    }

}
