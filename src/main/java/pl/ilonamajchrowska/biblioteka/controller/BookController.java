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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

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

    @Autowired
    private ReviewsDAO reviewsDAO;

    @Autowired
    private BookshelvesDAO bookshelvesDAO;

    @RequestMapping(value = "/books/view/{book_id}", method = RequestMethod.GET)
    public String viewbook(ModelMap model, @PathVariable String book_id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);

        model.addAttribute("book", booksDAO.findById(Integer.parseInt(book_id)));
        model.addAttribute("review", new Reviews());
        model.addAttribute("bookshelve", new Bookshelves());
        if(user != null) {
            model.addAttribute("bookshelves", bookshelvesDAO.getAllUsers(user.getId()));
        }else{
            model.addAttribute("bookshelves", bookshelvesDAO.getAllUsers(0));
        }
        return "viewbook";
    }

    @RequestMapping(value = "/books/view/{book_id}", method = RequestMethod.POST)
    public String viewbookadd(@Valid @ModelAttribute("review") Reviews review, BindingResult result, ModelMap model, @PathVariable String book_id) {

        if (result.hasErrors()) {
            model.addAttribute("book", booksDAO.findById(Integer.parseInt(book_id)));
            return "viewbook";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);


        review.setBook(booksDAO.findById(Integer.parseInt(book_id)));
        review.setUser(user);

        reviewsDAO.saveReview(review);
        return "redirect:/books/view/"+book_id;
    }

    @RequestMapping(value = "/books/addtoshelf", method = RequestMethod.POST)
    public String addtoshelves(HttpServletRequest request) {
        Books book = booksDAO.findById(Integer.parseInt(request.getParameter("id")));
        Set<Bookshelves> lis = book.getBookshelves();
        lis.add(bookshelvesDAO.findById(Integer.parseInt(request.getParameter("shelvesid"))));
        book.setBookshelves(lis);

        try{
            booksDAO.saveBook(book);
        }catch (Exception e){
            //asd
        }

        return "redirect:/books/view/"+request.getParameter("id");
    }

    @RequestMapping(value = "/books/viewall", method = RequestMethod.GET)
    public String viewallbooks(ModelMap model) {

        model.addAttribute("books", booksDAO.getAll());
        return "viewallbooks";
    }

    @RequestMapping(value = "/user/books", method = RequestMethod.GET)
    public String usersbooks(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        pl.ilonamajchrowska.biblioteka.model.User user = userDAO.findByUserEmail(name);

        model.addAttribute("books", booksDAO.getAllUsers(user.getId()));
        return "viewusersbooks";
    }

    @RequestMapping(value = "/user/books/new", method = RequestMethod.GET)
    public String newbook(ModelMap model) {
        Books book = new Books();
        model.addAttribute("book", book);
        model.addAttribute("genres", genresDAO.getAll());
        return "newbook";
    }

    @RequestMapping(value = "/user/books/new", method = RequestMethod.POST)
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

    @RequestMapping(value = "/user/books/edit/{id}", method = RequestMethod.GET)
    public String editbook(ModelMap model, @PathVariable String id) {
        Books book = booksDAO.findById(Integer.parseInt(id));
        model.addAttribute("book", book);
        model.addAttribute("genres", genresDAO.getAll());
        return "editbook";
    }

    @RequestMapping(value = "/user/books/edit/{id}", method = RequestMethod.POST)
    public String editbookadd(@Valid @ModelAttribute("book") Books book, BindingResult result, ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            model.addAttribute("genres", genresDAO.getAll());
            return "editbook";
        }

        booksDAO.saveBook(book);
        return "editbooksuccess";
    }

    @RequestMapping(value = "/user/books/delete/{id}", method = RequestMethod.GET)
    public String deletebook(ModelMap model, @PathVariable String id) {
        Books book = booksDAO.findById(Integer.parseInt(id));
        try{
            booksDAO.deleteBook(Integer.parseInt(id));
            return "redirect:/user/books";
        }catch (Exception e){
            model.addAttribute("message", "Nie udało się usunać - książka posiada przypisane do siebie komentarze.");
        }
        return "deletebook";
    }

}
