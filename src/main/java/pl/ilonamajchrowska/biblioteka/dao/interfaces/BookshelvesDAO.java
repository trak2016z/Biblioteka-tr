package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Bookshelves;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
public interface BookshelvesDAO {
    Bookshelves findById(Integer id);
    void saveBookshelve(Bookshelves book);
    List<Bookshelves> getAll();
    List<Bookshelves> getAllUsers(Integer id);
    void deleteBookshelves(int i);
    void deleteBook(int b, int bs);
}
