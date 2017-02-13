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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.*;
import pl.ilonamajchrowska.biblioteka.model.Books;
import pl.ilonamajchrowska.biblioteka.model.Bookshelves;
import pl.ilonamajchrowska.biblioteka.model.Reviews;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ilona on 2017-02-11.
 */

@Controller
public class BookshelvesController {

    @Autowired
    private GenresDAO genresDAO;

    @Autowired
    private BooksDAO booksDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ReviewsDAO reviewsDAO;

    @Autowired
    private BookshelvesDAO bookshelvesDAO;

    @RequestMapping(value = "/user/bookshelves", method = RequestMethod.GET)
    public String viewbookshelve(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);

        model.addAttribute("bookshelves", bookshelvesDAO.getAllUsers(user.getId()));

        return "usersbookshelves";
    }

    @RequestMapping(value = "/user/bookshelves/new", method = RequestMethod.GET)
    public String newbookshelve(ModelMap model) {
        model.addAttribute("bookshelve", new Bookshelves());

        return "newbookshelves";
    }

    @RequestMapping(value = "/user/bookshelves/new", method = RequestMethod.POST)
    public String addnewbookshelve(@Valid @ModelAttribute("bookshelve") Bookshelves bookshelve, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "newbookshelve";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);
        bookshelve.setUser(user);
        bookshelvesDAO.saveBookshelve(bookshelve);
        return "newbookshelvesuccess";
    }

    @RequestMapping(value = "/user/bookshelves/edit/{id}", method = RequestMethod.GET)
    public String editbookshelve(ModelMap model, @PathVariable String id) {
        Bookshelves book = bookshelvesDAO.findById(Integer.parseInt(id));
        model.addAttribute("bookshelve", book);
        return "editbookshelves";
    }

    @RequestMapping(value = "/user/bookshelves/edit/{id}", method = RequestMethod.POST)
    public String editbookshelveadd(@Valid @ModelAttribute("bookshelve") Bookshelves book, BindingResult result, ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "editbookshelves";
        }

        bookshelvesDAO.saveBookshelve(book);
        return "editbookshelvesuccess";
    }

    @RequestMapping(value = "/user/bookshelves/delete/{id}", method = RequestMethod.GET)
    public String deletebook(ModelMap model, @PathVariable String id) {
        Bookshelves book = bookshelvesDAO.findById(Integer.parseInt(id));
        try {
            bookshelvesDAO.deleteBookshelves(Integer.parseInt(id));
            return "redirect:/user/bookshelves";
        } catch (Exception e) {
            model.addAttribute("message", "Nie udało się usunać - półka posiada przypisane do siebie książki.");
        }
        return "deletebookshelves";
    }

    @RequestMapping(value = "/user/bookshelves/view/{id}", method = RequestMethod.GET)
    public String viewbookshelve(ModelMap model, @PathVariable String id) {
        Bookshelves bookshelf = bookshelvesDAO.findById(Integer.parseInt(id));
        model.addAttribute("bookshelf", bookshelf);
        return "viewbookshelf";
    }

    @RequestMapping(value = "/user/bookshelves/book-delete/{b_id}/{bs_id}", method = RequestMethod.GET)
    public String deletebookfrombookshelf(ModelMap model, @PathVariable String b_id, @PathVariable String bs_id) {
        bookshelvesDAO.deleteBook(Integer.parseInt(b_id), Integer.parseInt(bs_id));
        return "redirect:/user/bookshelves/view/"+bs_id;
    }

}