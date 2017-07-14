package ch.renewinkler.service;

import ch.renewinkler.model.Category;
import ch.renewinkler.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Secured("ROLE_ADMIN")
    public List<Category> findAll() {
        return repo.findAll();
    }
}
