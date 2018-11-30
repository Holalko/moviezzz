package vsb.vis.cz.moviezzz.mappers;

import org.springframework.stereotype.Service;
import vsb.vis.cz.moviezzz.models.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AddressMapper {

    @PersistenceContext
    private EntityManager em;

    public void insert(Address address) {
        em.persist(address);
    }

    public void update(Address address) {
        em.merge(address);
    }

    public Address findById(Long id) {
        return em.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Address> findAll() {
        return em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

}
