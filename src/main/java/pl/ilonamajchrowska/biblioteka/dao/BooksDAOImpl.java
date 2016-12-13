package pl.ilonamajchrowska.biblioteka.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BooksDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;

/**
 * Created by Ilona on 2016-12-12.
 */
@Repository("BooksDAO")
@Transactional
public class BooksDAOImpl extends AbstractDAO<Integer, Books> implements BooksDAO {

}
