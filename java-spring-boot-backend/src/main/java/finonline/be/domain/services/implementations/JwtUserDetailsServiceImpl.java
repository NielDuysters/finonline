package finonline.be.domain.services.implementations;

import org.springframework.stereotype.Service;

import finonline.be.domain.model.User;
import finonline.be.persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username).orElse(null);
		
		List<String> roles = new ArrayList<>();
		roles.add("USER");
		
		UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getName())
                        .password(user.getPass())
                        .roles(roles.toArray(new String[0]))
                        .build();
		
		return userDetails;
	}
}
