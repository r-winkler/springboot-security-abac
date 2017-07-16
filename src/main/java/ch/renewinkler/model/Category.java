package ch.renewinkler.model;

import ch.renewinkler.model.security.PrivilegeEntity;
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
public class Category extends PrivilegeEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

}
