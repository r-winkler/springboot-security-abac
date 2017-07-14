package ch.renewinkler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() // for h2 console
//                .headers().frameOptions().disable() // for h2 console
//        .and().authorizeRequests().anyRequest().permitAll();

        http
                .csrf().disable() // for h2 console
                .headers().frameOptions().disable() // for h2 console
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                // authorities are the roles
                .authoritiesByUsernameQuery("select u.username, a.name from user u join role a " +
                        "on u.role_id = a.id where username = ?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("253cr3t");
    }

}

