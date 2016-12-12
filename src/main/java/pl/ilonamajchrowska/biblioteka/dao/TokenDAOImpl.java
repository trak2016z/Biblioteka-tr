package pl.ilonamajchrowska.biblioteka.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.ilonamajchrowska.biblioteka.dao.interfaces.TokenDAO;
import pl.ilonamajchrowska.biblioteka.model.Tokens;

@Repository("TokenDAO")
@Transactional
public class TokenDAOImpl extends AbstractDAO<Integer, Tokens> implements TokenDAO {

	@Override
	public void saveOrUpdate(Tokens token) {
		saveUpdate(token);
	}

	@Override
	public Tokens checkToken(String email, String token) {

		List<Tokens> tokens = new ArrayList<Tokens>();

		tokens = getSession()
			.createQuery("from Tokens where email=? and token=?")
			.setParameter(0, email)
			.setParameter(1, token)
			.list();

		if (tokens.size() > 0) {
			return tokens.get(0);
		} else {
			return null;
		}
	}

}
