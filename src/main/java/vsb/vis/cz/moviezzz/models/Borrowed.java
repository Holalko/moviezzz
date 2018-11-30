package vsb.vis.cz.moviezzz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Borrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;

    @OneToOne
    @JsonBackReference(value = "movie-borrowed")
    private Movie movie;

    @ManyToOne
    @JsonBackReference(value = "customer-borrowed")
    private Customer customer;

    @ManyToOne
    @JsonBackReference(value = "employee-borrowed")
    private Employee employee;

    public Borrowed() {
    }

    public Borrowed(LocalDate dueDate, Movie movie, Customer customer, Employee employee) {
        this.dueDate = dueDate;
        this.movie = movie;
        this.customer = customer;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
