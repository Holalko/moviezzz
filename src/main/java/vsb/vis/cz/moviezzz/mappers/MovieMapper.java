package vsb.vis.cz.moviezzz.mappers;

import org.springframework.stereotype.Service;
import vsb.vis.cz.moviezzz.database.JDBCconnection;
import vsb.vis.cz.moviezzz.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MovieMapper {


    @PersistenceContext
    private EntityManager em;

    public void insert(Movie movie) {
        em.persist(movie);
    }

    public void update(Movie movie) {
        em.merge(movie);
    }

    // Lazy load
    public Movie findById(Long id) {
        return em.createQuery("SELECT m.id, m.name, m.yearOfRelease, m.forAdults FROM Movie m WHERE m.id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }


}
