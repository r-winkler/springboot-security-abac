package ch.renewinkler;

import ch.renewinkler.model.Category;
import ch.renewinkler.model.Employee;
import ch.renewinkler.model.security.*;
import ch.renewinkler.repository.CategoryRepository;
import ch.renewinkler.repository.EmployeeRepository;
import ch.renewinkler.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootSecurityAbacApplication implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    EmployeeRepository employeeRepos;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityAbacApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        // categories
        Category category1 = Category.builder().name("category1").build();
        Category category2 = Category.builder().name("category2").build();
        Category category3 = Category.builder().name("category3").build();
        Category category4 = Category.builder().name("category4").build();
        Category category5 = Category.builder().name("category5").build();
        categoryRepo.save(Arrays.asList(category1, category2, category3, category4, category5));

        // employees
        Employee employee1 = Employee.builder().firstname("AAA").lastname("BBB").salary(5000l).build();
        Employee employee2 = Employee.builder().firstname("CCC").lastname("DDD").salary(10000l).build();
        Employee employee3 = Employee.builder().firstname("EEE").lastname("FFF").salary(2000l).build();
        Employee employee4 = Employee.builder().firstname("GGG").lastname("HHH").salary(35000l).build();
        employeeRepos.save(Arrays.asList(employee1, employee2, employee3, employee4));

        // roles and users
        Role managerRole = Role.builder().name("ROLE_MANAGER").build();
        Role employeeRole = Role.builder().name("ROLE_EMPLOYEE").build();
        Role anyRole = Role.builder().name("ROLE_ANY").build();
        User manager = User.builder().username("manager")
                .password(passwordEncoder.encode("manager"))
                .enabled(true).build();
        User employee = User.builder().username("user")
                .password(passwordEncoder.encode("user"))
                .enabled(true).build();
        User any = User.builder().username("any")
                .password(passwordEncoder.encode("any"))
                .enabled(true).build();
        manager.addRole(managerRole);
        employee.addRole(employeeRole);
        any.addRole(anyRole);

        // global privilege
        GlobalPrivilege globalPrivilege = GlobalPrivilege.builder()
                .type(PrivilegeType.SALARY)
                .role(anyRole)
                .build();
        anyRole.addGlobalPrivilege(globalPrivilege);

        roleRepo.save(managerRole);
        roleRepo.save(employeeRole);
        roleRepo.save(anyRole);

        // user privileges
        UserPrivilege userPrivilege1 = UserPrivilege.builder()
                .type(PrivilegeType.READ)
                .user(any)
                .build();
        UserPrivilege userPrivilege2 = UserPrivilege.builder()
                .type(PrivilegeType.READ)
                .user(any)
                .build();
        UserPrivilege userPrivilege3 = UserPrivilege.builder()
                .type(PrivilegeType.READ)
                .user(any)
                .build();
        UserPrivilege userPrivilege4 = UserPrivilege.builder()
                .type(PrivilegeType.READ)
                .user(any)
                .build();
        category2.addUserPrivilege(userPrivilege1);
        category4.addUserPrivilege(userPrivilege2);
        employee3.addUserPrivilege(userPrivilege3);
        employee4.addUserPrivilege(userPrivilege4);
        categoryRepo.save(Arrays.asList(category2, category4));
        employeeRepos.save(Arrays.asList(employee3, employee4));

    }
}
