package vsb.vis.cz.moviezzz.mappers;

import org.springframework.stereotype.Service;
import vsb.vis.cz.moviezzz.models.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomerMapper {

    @PersistenceContext
    private EntityManager em;

    public void insert(Customer customer) {
        em.persist(customer);
    }

    public void update(Customer customer) {
        em.merge(customer);
    }

    public Customer findById(Long id) {
        return em.createQuery("SELECT a FROM Customer a WHERE a.id = :id", Customer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Customer> findAll() {
        return em.createQuery("SELECT a FROM Customer a", Customer.class).getResultList();
    }

}
