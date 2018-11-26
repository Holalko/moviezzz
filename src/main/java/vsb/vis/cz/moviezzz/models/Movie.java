package vsb.vis.cz.moviezzz.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer yearOfRelease;
    private Boolean forAdults;
    private Double price;

    @OneToOne
    private Borrowed borrowed;

    @ManyToMany
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToOne
    private Customer reservedBy;

    @ManyToMany
    @JoinTable(name = "movie_followed_customer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> followedBy;

    @Transient
    private List<Customer> borrowedInHistoryBy;

    public Movie() {
    }

    public Movie(Long id, String name, Integer yearOfRelease, Boolean forAdults){
        this.id = id;
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.forAdults = forAdults;
    }

    public Movie(Long id, String name,
                 String description, Integer yearOfRelease, Boolean forAdults, Double price, Borrowed borrowed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearOfRelease = yearOfRelease;
        this.forAdults = forAdults;
        this.price = price;
        this.borrowed = borrowed;
    }

    public Borrowed getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Borrowed borrowed) {
        this.borrowed = borrowed;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Customer getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(Customer reservedBy) {
        this.reservedBy = reservedBy;
    }

    public List<Customer> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(List<Customer> followedBy) {
        this.followedBy = followedBy;
    }

    public List<Customer> getBorrowedInHistoryBy() {
        return borrowedInHistoryBy;
    }

    public void setBorrowedInHistoryBy(List<Customer> borrowedInHistoryBy) {
        this.borrowedInHistoryBy = borrowedInHistoryBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Boolean getForAdults() {
        return forAdults;
    }

    public void setForAdults(Boolean forAdults) {
        this.forAdults = forAdults;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
