package ch.renewinkler.model;

import ch.renewinkler.controller.JsonViews;
import ch.renewinkler.model.security.PrivilegeEntity;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class Employee extends PrivilegeEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String firstname;

    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;

    @NotNull
    @JsonView(JsonViews.SalaryView.class)
    private Long salary;

}
