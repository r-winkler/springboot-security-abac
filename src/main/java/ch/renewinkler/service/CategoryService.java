package ch.renewinkler.service;

import ch.renewinkler.model.Category;
import ch.renewinkler.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.hasCustomPrivilege(authentication.name, T(ch.renewinkler.model.security.PrivilegeType).READ)")
    public List<Category> findAll() {
        return repo.findAll();
    }
}
