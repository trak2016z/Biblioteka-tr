package pl.ilonamajchrowska.biblioteka.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BooksDAO;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.GenresDAO;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.UserDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ilona on 2017-01-27.
 */

@Controller
public class BookController {

    @Autowired
    private GenresDAO genresDAO;

    @Autowired
    private BooksDAO booksDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String newbook(ModelMap model) {
        Books book = new Books();
        model.addAttribute("book", book);
        model.addAttribute("genres", genresDAO.getAll());
        return "newbook";
    }

    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    public String newbookadd(@Valid @ModelAttribute("book") Books book, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("genres", genresDAO.getAll());
            System.out.println(result.toString());
            return "newbook";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);
        book.setUser(user);
        booksDAO.saveBook(book);
        return "newbooksuccess";
    }

}
