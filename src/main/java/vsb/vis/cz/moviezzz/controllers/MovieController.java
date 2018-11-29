package vsb.vis.cz.moviezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vsb.vis.cz.moviezzz.mappers.MovieMapper;
import vsb.vis.cz.moviezzz.models.Movie;
import vsb.vis.cz.moviezzz.models.dtos.MovieDTO;

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
    public Long saveMovie(@RequestBody MovieDTO movie) {
       return movieMapper.insert(movie);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/rewrite")
    public Long rewriteMovie(@RequestBody MovieDTO movie) {
        return movieMapper.rewrite(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateMovie(@RequestParam Movie movie) {
        movieMapper.update(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reserve")
    public Boolean reserveMovie(@RequestParam Long movieId, @RequestParam Long userId){
        return movieMapper.reserveMovie(movieId, userId);
    }

    /**
     *
     * @param id of user
     * @return
     */
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> findUsersMovies(@PathVariable Long id){
        return movieMapper.findBorrowedMoviesBy(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-by-name")
    public List<Movie> findByName(@RequestParam String name){
        return movieMapper.findByName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/extend-reservation/{movieId}")
    public Boolean extendReservation(@PathVariable Long movieId){
        return movieMapper.extendReservation(movieId);
    }


}
