package ch.renewinkler.config;

import ch.renewinkler.model.security.EntityPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

/**
 * The PermissionEvaluator has to be placed in a separate configuration.
 * https://stackoverflow.com/questions/23638462/how-do-i-add-method-based-security-to-a-spring-boot-project
 * https://github.com/spring-projects/spring-boot/issues/4875
 */
@Configuration
public class PermissionEvaluatorConfig {

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new EntityPermissionEvaluator());
        return expressionHandler;
    }

}
