package finonline.be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import finonline.be.domain.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	 public Optional<User> findByName(String name);
}
