package pl.ilonamajchrowska.biblioteka.validation;


import org.springframework.stereotype.Component;
import pl.ilonamajchrowska.biblioteka.model.User;
import pl.ilonamajchrowska.biblioteka.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;
	
    @Override
    public boolean supports(final Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {

        User user = (User) obj;

        if(!(user.getEmail().equals(user.getMatchingEmail())))
        	errors.rejectValue("matchingEmail", "message.matchingEmail", "Wpisane emaile różnią się od siebie.");
        else
        	if(userService.checkEmail(user.getEmail()))
            	errors.rejectValue("email", "message.email", "Ten email już istnieje w bazie danych.");
        
        if(!(user.getPassword().equals(user.getMatchingPassword())))
        	errors.rejectValue("matchingPassword", "message.matchingPassword", "Wpisane hasła różnią się od siebie.");
    }

}