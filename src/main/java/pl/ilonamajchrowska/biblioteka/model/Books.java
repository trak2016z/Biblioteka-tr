package pl.ilonamajchrowska.biblioteka.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ilona on 2016-12-12.
 */

@Entity
@Table(name = "books")

public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 60)
    @NotNull
    @NotEmpty
    @Size(min=1, max=60)
    private String title;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "pages")
    @NotNull
    private Integer pages;

    @Column(name = "ISBN")
    @NotNull
    private Integer ISBN;

    @Column(name = "publisher", length = 100)
    @NotNull
    @NotEmpty
    @Size(min=1, max=100)
    private String publisher;

    @Column(name = "author", length = 70)
    @NotNull
    @NotEmpty
    @Size(min=1, max=70)
    private String author;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genres genre;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "books")
    private Set<Bookshelves> bookshelves = new HashSet<Bookshelves>(0);

    public Books() {
        super();
    }

    public Books(String title, Date date, Integer pages, Integer ISBN, String publisher, String author, User user, Genres genre) {
        this.title = title;
        this.date = date;
        this.pages = pages;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.author = author;
        this.user = user;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public Set<Bookshelves> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Set<Bookshelves> bookshelves) {
        this.bookshelves = bookshelves;
    }
}