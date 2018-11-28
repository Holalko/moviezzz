package vsb.vis.cz.moviezzz.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsb.vis.cz.moviezzz.models.Borrowed;
import vsb.vis.cz.moviezzz.models.Customer;
import vsb.vis.cz.moviezzz.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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
        return em.createQuery("SELECT m FROM Movie m" +
                " WHERE m.id = :id", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // Lazy load
    public List<Movie> findAll() {
        return em.createQuery("SELECT new Movie(m.id, m.name, m.yearOfRelease, m.forAdults) FROM Movie m", Movie.class)
                .getResultList();
    }

    @Transactional
    public Boolean reserveMovie(Long movieId, Long userId) {
        Movie movie = findById(movieId);
        Customer customer = customerMapper.findById(userId);
        if (movie.getReservedBy() != null || movie.getBorrowed() != null) {
            if (!customer.equals(movie.getReservedBy())) {
                customer.addToFollowedList(movie);
                customerMapper.update(customer);
            }
            return false;
        }
        movie.setReservedBy(customer);
        update(movie);
        return true;
    }

    public List<Movie> findByName(String name) {
        return em.createQuery("SELECT new Movie(m.id, m.name, m.yearOfRelease, m.forAdults) FROM Movie m " +
                "WHERE m.name LIKE :name", Movie.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<Movie> findBorrowedMoviesBy(Long customerId) {
        return em.createQuery("" +
                "SELECT m.id as id, m.name as name, " +
                "m.yearOfRelease as yearOfRelease, " +
                "m.forAdults as forAdults, " +
                "m.borrowed.dueDate as dueDate " +
                "FROM Movie m " +
                "WHERE m.borrowed.customer.id = :customerId")
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Transactional
    public Boolean extendReservation(Long movieId) {
        Long howManyTimesFollowed = em.createQuery("SELECT COUNT(c) FROM Customer c JOIN c.followedList as f WHERE f.id = :movieId", Long.class)
                .setParameter("movieId", movieId).getSingleResult();
        if(howManyTimesFollowed > 0){
            return false;
        }

        Movie movie = findById(movieId);
        if(movie.getBorrowed() == null){
            movie.setBorrowed(
                    em.createQuery("SELECT b FROM Borrowed b WHERE b.movie.id = :movieId", Borrowed.class)
                    .setParameter("movieId", movieId)
                    .getSingleResult()
            );
        }
        LocalDate dueDate = movie.getBorrowed().getDueDate();
        dueDate = dueDate.plusMonths(1);
        movie.getBorrowed().setDueDate(dueDate);
        update(movie);
        return true;
    }
}
