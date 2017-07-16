package ch.renewinkler.repository;

import ch.renewinkler.config.SecurityConfig;
import ch.renewinkler.model.security.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(SecurityConfig.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void findByUsernameShouldReturnUser() {
        this.entityManager.persist(User.builder().username("renewinkler").password("123").enabled(true).build());
        User user = this.repo.findByUsername("renewinkler");

        assertThat(user.getUsername()).isEqualTo("renewinkler");
        assertThat(user.getPassword()).isEqualTo("123");
        assertThat(user.isEnabled()).isTrue();
    }

}