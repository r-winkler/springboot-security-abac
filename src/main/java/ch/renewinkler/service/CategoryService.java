package ch.renewinkler.service;

import ch.renewinkler.model.Category;
import ch.renewinkler.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @PreAuthorize("hasAnyRole({'ROLE_MANAGER', 'ROLE_EMPLOYEE', 'ROLE_ANY'})")
    // This was the original PostFilter SPEL. Subsequently, it was placed in a separate class.
    // @PostFilter("hasRole('ROLE_MANAGER') or hasRole('ROLE_EMPLOYEE') or filterObject.hasCustomPrivilege(authentication.name, T(ch.renewinkler.model.security.PrivilegeType).READ)")
    @PostFilter("hasRole('ROLE_MANAGER') or hasRole('ROLE_EMPLOYEE') or hasPermission(filterObject, 'read')")
    public List<Category> findAll() {
        return repo.findAll();
    }

}
