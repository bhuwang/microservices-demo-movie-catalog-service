/**
 * 
 */
package com.exampledemo.microservices.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exampledemo.microservices.model.CatalogItem;
import com.exampledemo.microservices.model.Movie;
import com.exampledemo.microservices.model.UserRating;

/**
 * @author bhuwangautam
 *
 */

@RestController
@RequestMapping("/catalog")
public class MoviceCatalogServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        System.out.println("userId: " + userId);

        UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/users/" + userId, UserRating.class);

        return userRating.getUserRatings().stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), movie.getName(), rating.getRating());
        }).collect(Collectors.toList());

    }
}
