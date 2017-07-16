package ch.renewinkler.repository;

import ch.renewinkler.model.security.Role;
import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, Long> {

    Role save(Role role);

}
