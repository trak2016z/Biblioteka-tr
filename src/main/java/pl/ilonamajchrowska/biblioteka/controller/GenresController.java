package pl.ilonamajchrowska.biblioteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.GenresDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;
import pl.ilonamajchrowska.biblioteka.model.Genres;

import javax.validation.Valid;
import java.sql.SQLException;

/**
 * Created by Ilona on 2017-01-31.
 */

@Controller
public class GenresController {

    @Autowired
    private GenresDAO genresDAO;

    @RequestMapping(value = "/admin/genres", method = RequestMethod.GET)
    public String genres(ModelMap model) {
        model.addAttribute("genres", genresDAO.getAll());
        return "genres";
    }

    @RequestMapping(value = "/admin/newgenre", method = RequestMethod.GET)
    public String newgenre(ModelMap model) {
        Genres genres = new Genres();
        model.addAttribute("genre", genres);
        return "newgenre";
    }

    @RequestMapping(value = "/admin/newgenre", method = RequestMethod.POST)
    public String newgenreadd(@Valid @ModelAttribute("genre") Genres genres, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
           return "newgenre";
        }

        genresDAO.saveGenre(genres);
        return "newgenresuccess";
    }

    @RequestMapping(value = "/admin/editgenre/{id}", method = RequestMethod.GET)
    public String editgenre(ModelMap model, @PathVariable String id) {
        Genres genres = genresDAO.findById(Integer.parseInt(id));
        model.addAttribute("genre", genres);
        return "editgenre";
    }

    @RequestMapping(value = "/admin/editgenre/{id}", method = RequestMethod.POST)
    public String editgenreadd(@Valid @ModelAttribute("genre") Genres genres, BindingResult result, ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "editgenre";
        }

        Genres genre = genresDAO.findById(Integer.parseInt(id));
        genres.setBooks(genre.getBooks());
        genresDAO.saveGenre(genres);
        return "editgenresuccess";
    }

    @RequestMapping(value = "/admin/deletegenre/{id}", method = RequestMethod.GET)
    public String deletegenre(ModelMap model, @PathVariable String id) {
        Genres genres = genresDAO.findById(Integer.parseInt(id));
        try{
            genresDAO.deleteGenre(Integer.parseInt(id));
            return "redirect:/admin/genres";
        }catch (Exception e){
            model.addAttribute("message", "Nie udało się usunać - kategoria posiada przypisane do siebie książki.");
        }
        return "deletegenre";
    }


}
