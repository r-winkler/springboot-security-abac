package ch.renewinkler.model;

import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class Category extends PrivilegeEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public void addCustomPrivilege(CustomPrivilege customPrivilege) {
        super.getCustomPrivileges().add(customPrivilege);
        customPrivilege.setCategory(this);
    }

    public void removeCustomPrivilege(CustomPrivilege customPrivilege) {
        super.getCustomPrivileges().remove(customPrivilege);
        customPrivilege.setCategory(null);
    }

}
