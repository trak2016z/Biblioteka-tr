package pl.ilonamajchrowska.biblioteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.BooksDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;

import java.util.List;

/**
 * Created by Ilona on 2017-01-31.
 */

@Controller
public class ViewController {

    @Autowired
    private BooksDAO booksDAO;

    @RequestMapping(value = "/viewall", method = RequestMethod.GET)
    public String viewall(ModelMap model) {
        List<Books> books = booksDAO.getAll();
        model.addAttribute("books", books);
        return "viewall";

    }
}
