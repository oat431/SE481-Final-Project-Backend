package se481.project.transmatter.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se481.project.transmatter.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
