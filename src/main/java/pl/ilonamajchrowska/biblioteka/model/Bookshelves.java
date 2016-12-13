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
@Table(name = "bookshelves")
public class Bookshelves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 20)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description", length = 60)
    @NotNull
    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "books_bookshelves", joinColumns = {
            @JoinColumn(name = "bookshelf_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "book_id",
                    nullable = false, updatable = false) })
    private Set<Books> books = new HashSet<Books>(0);

    public Bookshelves() { super(); }

    public Bookshelves(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }
}
