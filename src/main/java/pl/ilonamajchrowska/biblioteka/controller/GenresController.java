package pl.ilonamajchrowska.biblioteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ilonamajchrowska.biblioteka.dao.interfaces.GenresDAO;
import pl.ilonamajchrowska.biblioteka.model.Books;
import pl.ilonamajchrowska.biblioteka.model.Genres;

import javax.validation.Valid;

/**
 * Created by Ilona on 2017-01-31.
 */

@Controller
public class GenresController {

    @Autowired
    private GenresDAO genresDAO;

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

}
