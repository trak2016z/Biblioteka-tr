package pl.ilonamajchrowska.biblioteka.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.GenresDAO;
import pl.ilonamajchrowska.biblioteka.model.Genres;

import java.util.List;

/**
 * Created by Ilona on 2016-12-12.
 */
@Repository("genresDAO")
@Transactional

public class GenresDAOImpl extends AbstractDAO<Integer, Genres> implements GenresDAO {
    @Override
    public Genres findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public void saveGenre(Genres genre) {
        saveUpdate(genre);
    }

    @Override
    public List<Genres> getAll() {
        Criteria criteria = getSession().createCriteria(Genres.class);
        criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY ID"));
        return (List<Genres>) criteria.list();
    }
}
