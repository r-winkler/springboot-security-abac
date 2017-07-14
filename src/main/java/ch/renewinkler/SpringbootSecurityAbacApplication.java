package ch.renewinkler;

import ch.renewinkler.model.Category;
import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.model.security.Role;
import ch.renewinkler.model.security.User;
import ch.renewinkler.repository.CategoryRepository;
import ch.renewinkler.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootSecurityAbacApplication implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepo;

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
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        categoryRepo.save(category3);
        categoryRepo.save(category4);
        categoryRepo.save(category5);


        // roles and users
        Role adminRole = Role.builder().name("ROLE_ADMIN").build();
        Role userRole = Role.builder().name("USER_ADMIN").build();
        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .enabled(true).build();
        User user = User.builder().username("user")
                .password(passwordEncoder.encode("user"))
                .enabled(true).build();
        adminRole.setUsers(Arrays.asList(admin));
        userRole.setUsers(Arrays.asList(user));
        roleRepo.save(adminRole);
        roleRepo.save(userRole);

        // custom privilege
        CustomPrivilege customPrivilege1 = CustomPrivilege.builder()
                .type(PrivilegeType.READ)
                .category(category2)
                .user(user)
                .build();
        CustomPrivilege customPrivilege2 = CustomPrivilege.builder()
                .type(PrivilegeType.READ)
                .category(category4)
                .user(user)
                .build();
        List<CustomPrivilege> customPrivileges = new ArrayList<>();
        customPrivileges.add(customPrivilege1);
        customPrivileges.add(customPrivilege2);
        user.setCustomPrivileges(customPrivileges);

        category2.setCustomPrivileges(Arrays.asList(customPrivilege1));
        category4.setCustomPrivileges(Arrays.asList(customPrivilege2));

        categoryRepo.save(category2);
        categoryRepo.save(category4);





    }
}
