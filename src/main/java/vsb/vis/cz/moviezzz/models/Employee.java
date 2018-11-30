package vsb.vis.cz.moviezzz.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee extends Person {

    @OneToMany
    @JsonManagedReference(value = "employee-borrowed")
    private List<Borrowed> borroweds;


    public Employee(List<Borrowed> borroweds) {
        this.borroweds = borroweds;
    }

    public Employee(String name, List<Borrowed> borroweds) {
        super(name);
        this.borroweds = borroweds;
    }

    public Employee() {
        super();
    }

    public List<Borrowed> getBorroweds() {
        return borroweds;
    }

    public void setBorroweds(List<Borrowed> borroweds) {
        this.borroweds = borroweds;
    }
}
