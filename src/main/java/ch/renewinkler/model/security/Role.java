package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class Role extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<User> users;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<GlobalPrivilege> globalPrivileges;

    public void addUser(User user) {
        users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRole(null);
    }

    public void addGlobalPrivilege(GlobalPrivilege globalPrivilege) {
        globalPrivileges.add(globalPrivilege);
        globalPrivilege.setRole(this);
    }

    public void removeGlobalPrivilege(GlobalPrivilege globalPrivilege) {
        globalPrivileges.remove(globalPrivilege);
        globalPrivilege.setRole(null);
    }

}
