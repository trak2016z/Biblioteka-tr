package pl.ilonamajchrowska.biblioteka.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.ReviewsDAO;
import pl.ilonamajchrowska.biblioteka.model.Reviews;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
@Repository("reviewsDAO")
@Transactional
public class ReviewsDAOImpl extends AbstractDAO<Integer, Reviews> implements ReviewsDAO {
    @Override
    public Reviews findById(Integer id) {
        return findById(id);
    }

    @Override
    public void saveReview(Reviews review) {
        saveUpdate(review);
    }

    @Override
    public void deleteReview(Integer id) {
        delete(findById(id));
    }

    @Override
    public List<Reviews> getForBook(Integer id) {
        Criteria criteria = getSession().createCriteria(Reviews.class);
        criteria.add(Restrictions.eq("book.id", id));
        criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY this_.id desc"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Reviews>) criteria.list();
    }
}
