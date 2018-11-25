package vsb.vis.cz.moviezzz.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee extends Person {

    @OneToMany
    private List<Borrowed> borroweds;

    public Employee(){}

    public Employee(List<Borrowed> borroweds) {
        this.borroweds = borroweds;
    }

    public Employee(String name, List<Borrowed> borroweds) {
        super(name);
        this.borroweds = borroweds;
    }

    public List<Borrowed> getBorroweds() {
        return borroweds;
    }

    public void setBorroweds(List<Borrowed> borroweds) {
        this.borroweds = borroweds;
    }
}
