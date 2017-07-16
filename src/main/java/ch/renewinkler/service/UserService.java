package ch.renewinkler.service;

import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.model.security.User;
import ch.renewinkler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public boolean hasPrivilege(String username, PrivilegeType type) {
        User user = userRepo.findByUsername(username);
        return user.getRoles().stream()
                .flatMap(r -> r.getGlobalPrivileges().stream())
                .map(p -> p.getType())
                .anyMatch(t -> t.equals(type));
    }

    @Transactional
    public boolean hasRole(String username, String rolename) {
        User user = userRepo.findByUsername(username);
        return user.getRoles().stream()
                .map(r -> r.getName())
                .anyMatch(rn -> rn.equals(rolename));
    }
}
