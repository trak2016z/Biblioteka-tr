package pl.ilonamajchrowska.biblioteka.controller;

import java.io.IOException;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.BooksDAOImpl;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BooksDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;
import pl.ilonamajchrowska.biblioteka.model.Bookshelves;

/**
 * Handles requests for the application home page.
 */
@Controller 
public class HomeController {

	@Autowired
	BooksDAO booksDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {

		return "homepage";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return "adminpage";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return "userpage";
	}
		
}
