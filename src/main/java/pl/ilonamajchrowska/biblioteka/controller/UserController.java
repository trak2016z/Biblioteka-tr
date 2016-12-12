package pl.ilonamajchrowska.biblioteka.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import pl.ilonamajchrowska.biblioteka.core.MailMail;
import pl.ilonamajchrowska.biblioteka.model.Tokens;
import pl.ilonamajchrowska.biblioteka.model.User;
import pl.ilonamajchrowska.biblioteka.service.TokenService;
import pl.ilonamajchrowska.biblioteka.service.UserService;
import pl.ilonamajchrowska.biblioteka.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userService;
	
	@Autowired
	@Qualifier("UserService")
	UserService uService;
	
	@Autowired
	@Qualifier("TokenService")
	TokenService tService;
	
	@Autowired
	UserValidator userValidator;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Locale locale, Model model, HttpServletRequest request)
	{

		if (!request.isUserInRole("USER") && !request.isUserInRole("ADMIN")) {
			if (error != null) {
				model.addAttribute("msg", "Podane dane są nieprawidłowe");
			}
			if (logout != null) {
				model.addAttribute("msg", "Zostałeś wylogowany.");
			}
			return "login";
		} else {
				return "redirect:/";
		}
	}

	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		if (!request.isUserInRole("USER") && !request.isUserInRole("ADMIN")) {
			User userDto = new User();
			model.addAttribute("user", userDto);
			return "registration";
		} else {
			return "redirect:/";
		}
	}
	
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User userForm, BindingResult bindingResult, Model model) throws JsonGenerationException, JsonMappingException, IOException {

		userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        uService.saveUser(userForm); 
                
        model.addAttribute("users", userForm);
		return "registrationok";
    }
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(WebRequest request, Model model) {
		if (!request.isUserInRole("USER") && !request.isUserInRole("ADMIN")) {
			return "forgotpassword";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String sendMail(WebRequest request, Model model) {

		if(uService.checkEmail(request.getParameter("email"))) {

			ApplicationContext context =
					new ClassPathXmlApplicationContext("Spring-Mail.xml");
			String token = new Md5PasswordEncoder().encodePassword(request.getParameter("email"), "super");
			MailMail mm = (MailMail) context.getBean("mailMail");
			mm.sendMail("biblioteka@gmail.com",
					request.getParameter("email"),
					"Odzyskiwanie hasła",
					"Aby zresetować swoje hasło przejdź na ten adres: \n http://localhost:8080/biblioteka-1.0.0/newpassword/" + request.getParameter("email") + "/" + token);

			Tokens tokn = new Tokens(request.getParameter("email"), token);
			tService.addToken(tokn);
		}

		model.addAttribute("message", "W celu zmiany hasła sprawdź swoją pocztę.");
	    return "forgotpassword";
	}
	
	@RequestMapping(value = "/newpassword/{email:.+}/{token}", method = RequestMethod.GET)
	public String newPassword(@PathVariable("email") String email, @PathVariable("token") String token, Model model) {
		
		if(tService.checkToken(email, token))
			return "newpassword";
		else
			return "403";
	}
	
	@RequestMapping(value = "/newpassword/{email:.+}/{token}", method = RequestMethod.POST)
	public String addNewPassword(@PathVariable("email") String email, @PathVariable("token") String token, WebRequest request, Model model) {
		if(tService.checkToken(email, token))
		{
			if(request.getParameter("password").length() <6){
				model.addAttribute("message", "Podane hasło jest za krótkie.");
				return "newpassword";
			}
			else if(!request.getParameter("password").equals(request.getParameter("matchingPassword")))
			{
				model.addAttribute("message", "Podane hasła nie są takie same.");
				return "newpassword";
			}
			else
			{
				uService.changePassword(email, token, request.getParameter("password"));
				return "redirect:/login";
			}
		}
		else
			return "403";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addAttribute("username", userDetail.getUsername());
		}

		return "403";
	}

}
