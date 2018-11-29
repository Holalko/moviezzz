package vsb.vis.cz.moviezzz.models.dtos;

public class MovieDTO {

    private Long id;
    private String name;
    private String description;
    private Integer yearOfRelease;
    private Boolean forAdults;
    private Double price;

    public MovieDTO() {
    }

    public MovieDTO(Long id, String name, String description, Integer yearOfRelease, Boolean forAdults, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearOfRelease = yearOfRelease;
        this.forAdults = forAdults;
        this.price = price;
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
