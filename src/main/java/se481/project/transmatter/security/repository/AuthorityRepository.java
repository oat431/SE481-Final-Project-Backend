package se481.project.transmatter.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se481.project.transmatter.security.entity.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
