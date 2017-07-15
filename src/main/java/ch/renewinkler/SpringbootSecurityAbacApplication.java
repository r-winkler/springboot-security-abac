package ch.renewinkler;

import ch.renewinkler.model.Category;
import ch.renewinkler.model.Employee;
import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.model.security.Role;
import ch.renewinkler.model.security.User;
import ch.renewinkler.repository.CategoryRepository;
import ch.renewinkler.repository.EmployeeRepository;
import ch.renewinkler.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
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

        // employees
        Employee employee1 = Employee.builder().firstname("AAA").lastname("BBB").salary(5000l).build();
        Employee employee2 = Employee.builder().firstname("CCC").lastname("DDD").salary(10000l).build();
        Employee employee3 = Employee.builder().firstname("EEE").lastname("FFF").salary(2000l).build();
        Employee employee4 = Employee.builder().firstname("GGG").lastname("HHH").salary(35000l).build();

        // roles and users
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        Role userRole = Role.builder().name("ROLE_USER").build();
        Role anyRole = Role.builder().name("ROLE_ANY").build();
        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .enabled(true).build();
        User user = User.builder().username("user")
                .password(passwordEncoder.encode("user"))
                .enabled(true).build();
        User any = User.builder().username("any")
                .password(passwordEncoder.encode("any"))
                .enabled(true).build();
        admin.addRole(adminRole);
        user.addRole(userRole);
        any.addRole(anyRole);
        roleRepo.save(adminRole);
        roleRepo.save(userRole);
        roleRepo.save(anyRole);

        // custom privilege
        CustomPrivilege customPrivilege1 = CustomPrivilege.builder()
                .type(PrivilegeType.READ)
                .privilegeEntity(category2)
                .user(user)
                .build();
        CustomPrivilege customPrivilege2 = CustomPrivilege.builder()
                .type(PrivilegeType.READ)
                .privilegeEntity(category4)
                .user(user)
                .build();
        user.addCustomPrivilege(customPrivilege1);
        user.addCustomPrivilege(customPrivilege2);
        category2.addCustomPrivilege(customPrivilege1);
        category4.addCustomPrivilege(customPrivilege2);

        categoryRepo.save(Arrays.asList(category1, category2, category3, category4, category5));
        employeeRepos.save(Arrays.asList(employee1, employee2, employee3, employee4));

    }
}
