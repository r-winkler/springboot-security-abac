package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class Role extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

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
