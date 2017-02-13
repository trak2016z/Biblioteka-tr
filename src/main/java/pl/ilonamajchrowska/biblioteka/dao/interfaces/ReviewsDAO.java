package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Reviews;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
public interface ReviewsDAO {
    Reviews findById(Integer id);
    void saveReview(Reviews review);
    void deleteReview(Integer id);
    List<Reviews> getForBook(Integer id);
}
