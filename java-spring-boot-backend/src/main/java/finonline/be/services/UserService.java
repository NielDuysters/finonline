package finonline.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finonline.be.domain.model.User;
import finonline.be.domain.request.PatchUser;
import finonline.be.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean userExists(String name) {
		Optional<User> user = userRepository.findByName(name);	
        return user.isPresent();
	}
	
	public User createUser(User user) {
		if (userExists(user.getName())) {
			return null;
		}		
		
		return userRepository.save(user);
	}
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User getUserByName(String name) {
		return userRepository.findByName(name).orElse(null);
	}
	
	public User patchUser(Integer userId, PatchUser newUser) {
		User user = userRepository.findById(userId).orElse(null);
		
		if (user == null) {
			return null;
		}
		
		if (newUser.getNewStartCapital() == null) {
			throw new IllegalArgumentException("Start capital cannot be null.");
		}
		
		user.setStartCapital(newUser.getNewStartCapital());
		return userRepository.save(user);
	}
}
