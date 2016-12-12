package pl.ilonamajchrowska.biblioteka.service;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ilonamajchrowska.biblioteka.model.User;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.UserDAO;
import pl.ilonamajchrowska.biblioteka.model.Tokens;

@Service("UserService")
public class UserService {

	@Autowired
	UserDAO userDao;
	
    public void saveUser(User user) throws JsonGenerationException, JsonMappingException, IOException {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setMatchingPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(0);
		user.setEnabled(1);
		userDao.saveUser(user);
    }
    
	public boolean checkEmail(String email) {
		if(userDao.findByUserEmail(email)==null)
			return false;
		else
			return true;
	}

	public void changePassword(Tokens token) {
		userDao.changePassword(token);
		
	}

	public void changePassword(String email, String token, String password) {
		Tokens tokn = new Tokens(email, token, password);
		userDao.changePassword(tokn);
	}
	
	public User get(String email) {
		return userDao.get(email);
	}
}
