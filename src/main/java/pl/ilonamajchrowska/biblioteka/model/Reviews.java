package pl.ilonamajchrowska.biblioteka.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Ilona on 2016-12-12.
 */

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "opinion")
    @NotNull
    @NotEmpty
    private String opinion;

    @Column (name = "mark")
    @NotNull
    @NotEmpty
    private Integer mark;

    @Column (name = "spoiler")
    @NotNull
    @NotEmpty
    private Boolean spoiler;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    public Reviews() { super(); }

    public Reviews(String opinion, Integer mark, Boolean spoiler, User user, Books book) {
        this.opinion = opinion;
        this.mark = mark;
        this.spoiler = spoiler;
        this.user = user;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Boolean spoiler) {
        this.spoiler = spoiler;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}
