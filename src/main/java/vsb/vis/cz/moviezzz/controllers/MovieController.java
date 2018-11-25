package vsb.vis.cz.moviezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.MovieMapper;
import vsb.vis.cz.moviezzz.models.Movie;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin( origins = "*")
public class MovieController {

    private MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieMapper.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Movie> findAll() {
        return movieMapper.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveMovie(@RequestParam Movie movie) {
        movieMapper.insert(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateMovie(@RequestParam Movie movie) {
        movieMapper.update(movie);
    }

}
