package pl.ilonamajchrowska.biblioteka.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.ilonamajchrowska.biblioteka.dao.interfaces.UserDAO;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
	
		pl.ilonamajchrowska.biblioteka.model.User user = userDao.findByUserEmail(username);
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority(user.getRole());

		return buildUserForAuthentication(user, authorities);
		
	}

	private User buildUserForAuthentication(pl.ilonamajchrowska.biblioteka.model.User user,
                                            List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
			(user.isEnabled().equals(1)?true:false), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Integer role) {

		List<String> roles = new ArrayList<String>();
        if (role.intValue() == 1) {
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");
        } else if (role.intValue() == 0) {
            roles.add("ROLE_USER");
        }
        		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String rolec : roles) {
            authorities.add(new SimpleGrantedAuthority(rolec));
        }
        return authorities;
	}

}