package ch.renewinkler;

import ch.renewinkler.model.Category;
import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.model.security.Role;
import ch.renewinkler.model.security.User;
import ch.renewinkler.repository.CategoryRepository;
import ch.renewinkler.repository.CustomPrivilegeRepository;
import ch.renewinkler.repository.RoleRepository;
import ch.renewinkler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootSecurityAbacApplication implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CustomPrivilegeRepository customPrivilegeRepo;

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
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        categoryRepo.save(category3);
        categoryRepo.save(category4);
        categoryRepo.save(category5);

        // roles and users
        Role adminRole = Role.builder().name("ADMIN").build();
        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .enabled(true).build();
        List<User> users = new ArrayList<>();
        users.add(admin);
        adminRole.setUsers(users);
        roleRepo.save(adminRole);

        // custom privilege
        CustomPrivilege customPrivilege1 = CustomPrivilege.builder()
                .type(PrivilegeType.READ)
                .category(category2)
                .build();
        customPrivilegeRepo.save(customPrivilege1);
        List<CustomPrivilege> customPrivileges = new ArrayList<>();
        customPrivileges.add(customPrivilege1);
        admin.setCustomPrivileges(customPrivileges);
        userRepo.save(admin);
    }
}
