package com.mservice.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mservice.moviecatalogservice.model.CatalogItem;
import com.mservice.moviecatalogservice.model.UserRating;
import com.mservice.moviecatalogservice.services.MovieInfo;
import com.mservice.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingInfo.getUserRating(userId);
		return userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}

// List<Rating> ratings = Arrays.asList(new Rating("1", 4), new Rating("2", 5),
// new Rating("3", 6));

//Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
//.retrieve().bodyToMono(Movie.class).block();