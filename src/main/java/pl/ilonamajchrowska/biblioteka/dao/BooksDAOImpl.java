package pl.ilonamajchrowska.biblioteka.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BooksDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;

import java.util.List;
/**
 * Created by Ilona on 2016-12-12.
 */
@Repository("BooksDAO")
@Transactional
public class BooksDAOImpl extends AbstractDAO<Integer, Books> implements BooksDAO {

    @Override
    public Books findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void saveBook(Books book) {
        saveUpdate(book);
    }

    @Override
    public List<Books> getAll() {
        Criteria criteria = getSession().createCriteria(Books.class);
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Books>) criteria.list();
    }

    @Override
    public List<Books> getAllUsers(Integer id) {
        Criteria criteria = getSession().createCriteria(Books.class);
        criteria.add(Restrictions.eq("user.id", id));
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Books>) criteria.list();
    }

    @Override
    public void deleteBook(int i) {
        delete(findById(i));
    }
}
