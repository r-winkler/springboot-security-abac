package ch.renewinkler.repository;

import ch.renewinkler.model.Employee;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Long> {

    List<Employee> findAll();

    List<Employee> save(Iterable<Employee> employees);

}
