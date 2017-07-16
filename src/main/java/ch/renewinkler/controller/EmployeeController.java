package ch.renewinkler.controller;

import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.service.EmployeeService;
import ch.renewinkler.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper mapper;

    private ObjectWriter viewWriter;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public String findAll() throws JsonProcessingException {
        if (hasRole("ROLE_MANAGER") || hasPrivilege(PrivilegeType.SALARY)) {
            viewWriter = mapper.writerWithView(JsonViews.SalaryView.class);
        } else {
            viewWriter = mapper.writerWithView(JsonViews.BasicView.class);
        }
        return viewWriter.writeValueAsString(employeeService.findAll());
    }

    private boolean hasRole(String role) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.hasRole(username, role);
    }

    private boolean hasPrivilege(PrivilegeType type) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.hasPrivilege(username, type);
    }


}
