package pl.ilonamajchrowska.biblioteka.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ilona on 2016-12-12.
 */

@Entity
@Table(name = "genres")
public class Genres {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description")
    @NotNull
    @NotEmpty
    private String description;

    @OneToMany
    @JoinColumn(name = "book_id")
    private Set<Books> books = new HashSet<Books>(0);

    public Genres() { super(); }

    public Genres(String name, String description, Set<Books> books) {

        this.name = name;
        this.description = description;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }

}
