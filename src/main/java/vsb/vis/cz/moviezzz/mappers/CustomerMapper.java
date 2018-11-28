package vsb.vis.cz.moviezzz.mappers;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsb.vis.cz.moviezzz.models.Customer;
import vsb.vis.cz.moviezzz.models.LoginModel;

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

    @Transactional
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

    public Long authenticateUser(LoginModel loginModel) throws NotFoundException {
        List<Customer> user = em.createQuery("SELECT a FROM Customer a WHERE a.name = :email AND a.password = :password", Customer.class)
                .setParameter("email", loginModel.getEmail())
                .setParameter("password", loginModel.getPassword()).getResultList();
        if(user.isEmpty()){
            throw new NotFoundException("Could not log in");
        }
        return user.get(0).getId();
    }

}
