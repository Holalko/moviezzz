package vsb.vis.cz.moviezzz.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends Person {

    private Integer age;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Borrowed> borroweds;

    @OneToMany
    private List<Movie> reservedList;

    @ManyToMany
    @JoinTable(name = "movie_followed_customer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> followedList;

    @Transient
    private List<Movie> historyList;

    @ManyToMany
    @JoinTable(name = "customer_category",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> favoriteCategories;


    public Customer(){
        super();
    }

    public Customer(String password, String name) {
        super(name, password);
    }

    public Customer(String password, Integer age, Address address, List<Borrowed> borroweds, List<Movie> reservedList, List<Movie> followedList, List<Movie> historyList, List<Category> favoriteCategories) {
        super(password);
        this.age = age;
        this.address = address;
        this.borroweds = borroweds;
        this.reservedList = reservedList;
        this.followedList = followedList;
        this.historyList = historyList;
        this.favoriteCategories = favoriteCategories;
    }

    public Customer(String name, String password, Integer age, Address address, List<Borrowed> borroweds, List<Movie> reservedList, List<Movie> followedList, List<Movie> historyList, List<Category> favoriteCategories) {
        super(name, password);
        this.age = age;
        this.address = address;
        this.borroweds = borroweds;
        this.reservedList = reservedList;
        this.followedList = followedList;
        this.historyList = historyList;
        this.favoriteCategories = favoriteCategories;
    }

    public void addToFollowedList(Movie movie){
        this.followedList.add(movie);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Borrowed> getBorroweds() {
        return borroweds;
    }

    public void setBorroweds(List<Borrowed> borroweds) {
        this.borroweds = borroweds;
    }

    public List<Movie> getReservedList() {
        return reservedList;
    }

    public void setReservedList(List<Movie> reservedList) {
        this.reservedList = reservedList;
    }

    public List<Movie> getFollowedList() {
        return followedList;
    }

    public void setFollowedList(List<Movie> followedList) {
        this.followedList = followedList;
    }

    public List<Movie> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<Movie> historyList) {
        this.historyList = historyList;
    }

    public List<Category> getFavoriteCategories() {
        return favoriteCategories;
    }

    public void setFavoriteCategories(List<Category> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }
}
