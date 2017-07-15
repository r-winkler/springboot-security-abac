package ch.renewinkler.service;

import ch.renewinkler.model.Employee;
import ch.renewinkler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @PostFilter("hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'read')")
    public List<Employee> findAll() {
        return repo.findAll();
    }

}
