package pl.ilonamajchrowska.biblioteka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ilonamajchrowska.biblioteka.dao.interfaces.TokenDAO;
import pl.ilonamajchrowska.biblioteka.model.Tokens;

@Service("TokenService")
public class TokenService {

	@Autowired
	TokenDAO tokenDao;
	
	public void addToken(Tokens token) {
		tokenDao.saveOrUpdate(token);
	}

	public Boolean checkToken(String email, String token) {
		if(tokenDao.checkToken(email, token)!=null)
			return true;
		else
			return false;
	}

}
