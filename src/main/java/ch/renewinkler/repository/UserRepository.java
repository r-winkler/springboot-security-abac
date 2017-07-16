package ch.renewinkler.repository;

import ch.renewinkler.model.security.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User findByUsername(String username);
}
