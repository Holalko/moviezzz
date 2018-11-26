package vsb.vis.cz.moviezzz.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsb.vis.cz.moviezzz.database.JDBCconnection;
import vsb.vis.cz.moviezzz.models.Customer;
import vsb.vis.cz.moviezzz.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MovieMapper {


    @PersistenceContext
    private EntityManager em;

    private CustomerMapper customerMapper;

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public void insert(Movie movie) {
        em.persist(movie);
    }

    @Transactional
    public void update(Movie movie) {
        em.merge(movie);
    }

    public Movie findById(Long id) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // Lazy load
    public List<Movie> findAll() {
        return em.createQuery("SELECT new Movie(m.id, m.name, m.yearOfRelease, m.forAdults) FROM Movie m", Movie.class).getResultList();
    }

    @Transactional
    public Boolean reserveMovie(Long movieId, Long userId) {
        Movie movie = findById(movieId);
        Customer customer = customerMapper.findById(userId);
        if (movie.getReservedBy() != null) {
            if (!movie.getReservedBy().equals(customer)) {
                customer.addToFollowedList(movie);
                customerMapper.update(customer);
            }
            return false;
        }
        movie.setReservedBy(customer);
        update(movie);
        return true;
    }
}
