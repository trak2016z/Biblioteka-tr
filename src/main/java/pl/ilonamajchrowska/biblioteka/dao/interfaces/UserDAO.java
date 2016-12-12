package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Tokens;
import pl.ilonamajchrowska.biblioteka.model.User;

public interface UserDAO {
	User findByUserEmail(String email);
	Integer saveUser(User user);
	void changePassword(Tokens token);
	User get(String email);
}
