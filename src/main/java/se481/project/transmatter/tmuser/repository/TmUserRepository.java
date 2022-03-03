package se481.project.transmatter.tmuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se481.project.transmatter.tmuser.entity.TmUser;

public interface TmUserRepository extends JpaRepository<TmUser,Long> {
}
