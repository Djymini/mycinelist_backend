package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.MovieSelectionDayDao;
import com.mycinelist.mycinelist_backend.entities.MovieSelectionDay;
import com.mycinelist.mycinelist_backend.entities.TmdbResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movieSelection")
public class MovieSelectionDayController {
    private final MovieSelectionDayDao movieSelectionDayDao;

    public MovieSelectionDayController(MovieSelectionDayDao movieSelectionDayDao) {
        this.movieSelectionDayDao = movieSelectionDayDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieSelectionDay>> getAllMovie() {
        return ResponseEntity.ok(movieSelectionDayDao.findAll());
    }

    @GetMapping("/test")
    public ResponseEntity<List<MovieSelectionDay>> test() {
        String url = "http://localhost:8080/movieSelection/all";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MovieSelectionDay>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieSelectionDay>>() {}
        );
        return result;

    }

    @GetMapping("/test2")
    public ResponseEntity<TmdbResponse> test2() {
        String url = "https://api.themoviedb.org/3//movie/popular";
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", "bbb18aeef38e4e6b4d2e948ab04abb3d")
                .queryParam("page", 1)
                .queryParam("language", "fr-FR")
                .queryParam("region", "FR")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TmdbResponse> result = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TmdbResponse>() {}
        );
        return ResponseEntity.ok(result.getBody());
    }

    @PutMapping("/all")
    public void updateMovie() {

        int page;
        String[] url = {
                "https://api.themoviedb.org/3/movie/popular",
                "https://api.themoviedb.org/3/movie/top_rated",
                "https://api.themoviedb.org/3/discover/movie?release_date.lte=2005-12-31&sort_by=popularity.desc",
                "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&with_origin_country=FR",
                "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&with_genres=16",
                "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&with_genres=99"
        };

        for (int i = 0; i < url.length; i++) {
            if (i == 0) {
                page = 1;
            }
            else {
                page = (int)(Math.random() * 100);
            }

            String urlWithParams = UriComponentsBuilder.fromHttpUrl(url[i])
                    .queryParam("api_key", "bbb18aeef38e4e6b4d2e948ab04abb3d")
                    .queryParam("include_adult", false)
                    .queryParam("page", page)
                    .queryParam("language", "fr-FR")
                    .queryParam("region", "FR")
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<TmdbResponse> result = restTemplate.exchange(
                    urlWithParams,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<TmdbResponse>() {}
            );

            int movieId = result.getBody().getResults().get((int)(Math.random() * 20)).getId();
            String date = LocalDate.now().toString();

            MovieSelectionDay data = new MovieSelectionDay(0, movieId, date);

            MovieSelectionDay updateMovie = movieSelectionDayDao.update(i+1, data);
        }
    }



    /*@PutMapping("/{id}")
    public ResponseEntity<MovieSelectionDay> updateMovie(@PathVariable int id, @RequestBody MovieSelectionDay movieSelectionDay) {
        MovieSelectionDay updateMovie = movieSelectionDayDao.update(id, movieSelectionDay);
        return ResponseEntity.ok(updateMovie);
    }*/
}
