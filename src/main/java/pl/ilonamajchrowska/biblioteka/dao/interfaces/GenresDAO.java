package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Genres;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
public interface GenresDAO {
    Genres findById(Integer id);
    void saveGenre(Genres genre);
    void deleteGenre(Integer id);
    List<Genres> getAll();
}
