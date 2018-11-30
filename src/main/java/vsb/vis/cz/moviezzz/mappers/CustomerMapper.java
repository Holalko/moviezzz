package vsb.vis.cz.moviezzz.mappers;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsb.vis.cz.moviezzz.models.Customer;
import vsb.vis.cz.moviezzz.models.Employee;
import vsb.vis.cz.moviezzz.models.LoginModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Customer> user = em.createQuery("SELECT a FROM Customer a WHERE a.name = :email", Customer.class)
                .setParameter("email", loginModel.getEmail())
                .getResultList();
        if (user.isEmpty()) {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.name = :email", Employee.class)
                    .setParameter("email", loginModel.getEmail())
                    .getResultList();
            if(employees.isEmpty()){
                throw new NotFoundException("Could not log in");
            }
            boolean checkPasswords = checkPassword(employees.get(0).getId(), loginModel.getPassword());
            if(checkPasswords){
                return employees.get(0).getId();
            } else {
                throw new NotFoundException("Could not log in");
            }
        }
        boolean checkPasswords = checkPassword(user.get(0).getId(), loginModel.getPassword());
        if(checkPasswords){
            return user.get(0).getId();
        } else {
            throw new NotFoundException("Could not log in");
        }
    }

    private boolean checkPassword(Long userId, String password){
        Map<Long, String> passwords = loadPasswords();
        String pass = passwords.get(userId);
        return password.equals(pass);
    }

    private Map<Long, String> loadPasswords(){
        File passwordFile = new File("passwords.csv");
        Map<Long, String> passwordMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(passwordFile))) {
            String line;
            String[] vals;
            while ((line = br.readLine()) != null) {
                vals = line.split(",");
                passwordMap.put(
                        Long.valueOf(vals[0]),
                        vals[1]
                );
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return passwordMap;
    }

}
