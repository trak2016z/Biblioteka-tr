package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Books;
import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
public interface BooksDAO {
    Books findById(Integer id);
    void saveBook(Books book);
    List<Books> getAll();
}
