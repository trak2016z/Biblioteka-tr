package pl.ilonamajchrowska.biblioteka.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ilonamajchrowska.biblioteka.model.Tokens;
import pl.ilonamajchrowska.biblioteka.model.User;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.UserDAO;

@Repository("UserDAO")
@Transactional
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

	@Override
	public User findByUserEmail(String username) {

		List<User> users = new ArrayList<User>();

		users = getSession().createQuery("from User where email=?")
				.setParameter(0, username)
				.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	public Integer saveUser(User user) {
		save(user);
		return 1;

	}

	@Override
	public void changePassword(Tokens token) {
		List<User> users = new ArrayList<User>();

		users = getSession().createQuery("from User where email=?")
			.setParameter(0, token.getEmail())
			.list();
		users.get(0).setMatchingEmail(token.getEmail());
		users.get(0).setMatchingPassword(token.getPassword());
		users.get(0).setPassword(token.getPassword());


		getSession()
				.createQuery("delete from Tokens where email=:ii")
				.setParameter("ii", token.getEmail()).executeUpdate();

	}

	@Override
	public User get(String email) {
		List<User> users = new ArrayList<User>();

		users = getSession()
			.createQuery("from User where email=?")
			.setParameter(0, email)
			.list();
		return users.get(0);
	}
}
