package vsb.vis.cz.moviezzz.mappers;

import org.springframework.stereotype.Service;
import vsb.vis.cz.moviezzz.models.Category;
import vsb.vis.cz.moviezzz.models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryMapper {

    @PersistenceContext
    private EntityManager em;

    public void insert(Category category) {
        em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }

    public Category findById(Long id) {
        return em.createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

}
