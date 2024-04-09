package finonline.be.domain.services.interfaces;

import finonline.be.domain.model.User;
import finonline.be.web.request.PatchUser;

public interface UserService {
	
	public boolean userExists(String name);
	public User createUser(User user);
	public User getUserById(Integer id);
	public User getUserByName(String name);
	public User patchUser(Integer userId, PatchUser newUser);
}
