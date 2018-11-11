package vsb.vis.cz.moviezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.MovieMapper;
import vsb.vis.cz.moviezzz.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id){
        return movieMapper.findById(id);
    }

}
