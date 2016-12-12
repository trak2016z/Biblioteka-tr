package pl.ilonamajchrowska.biblioteka.dao.interfaces;

import pl.ilonamajchrowska.biblioteka.model.Tokens;

public interface TokenDAO {
	public void saveOrUpdate(Tokens token);
	public Tokens checkToken(String email, String token);
}
