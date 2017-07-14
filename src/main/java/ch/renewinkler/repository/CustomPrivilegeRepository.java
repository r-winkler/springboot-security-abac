package ch.renewinkler.repository;

import ch.renewinkler.model.security.CustomPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomPrivilegeRepository extends JpaRepository<CustomPrivilege, Long> {
}
