package ch.renewinkler.repository;

import ch.renewinkler.model.Category;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CategoryRepository extends Repository<Category, Long> {

    List<Category> findAll();

    List<Category> save(Iterable<Category> categories);

}
