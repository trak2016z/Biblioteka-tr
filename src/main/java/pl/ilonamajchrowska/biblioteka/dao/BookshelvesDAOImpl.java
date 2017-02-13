package pl.ilonamajchrowska.biblioteka.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BookshelvesDAO;
import pl.ilonamajchrowska.biblioteka.model.Bookshelves;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */

@Repository("BookshelvesDAO")
@Transactional
public class BookshelvesDAOImpl extends AbstractDAO<Integer, Bookshelves> implements BookshelvesDAO {
    @Override
    public Bookshelves findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void saveBookshelve(Bookshelves book) {
        saveUpdate(book);
    }

    @Override
    public List<Bookshelves> getAll() {
        Criteria criteria = getSession().createCriteria(Bookshelves.class);
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Bookshelves>) criteria.list();
    }

    @Override
    public List<Bookshelves> getAllUsers(Integer id) {
        Criteria criteria = getSession().createCriteria(Bookshelves.class);
        criteria.add(Restrictions.eq("user.id", id));
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Bookshelves>) criteria.list();
    }

    @Override
    public void deleteBookshelves(int i) {
        delete(findById(i));
    }

    @Override
    public void deleteBook(int b, int bs) {
        getSession().createSQLQuery("DELETE FROM books_bookshelves where book_id = "+b+" and bookshelf_id = "+bs).executeUpdate();
    }
}
