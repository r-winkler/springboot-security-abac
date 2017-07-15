package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
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

    @Builder.Default
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ROLE_USER",
            joinColumns = {@JoinColumn(name = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

    public void addGlobalPrivilege(GlobalPrivilege globalPrivilege) {
        globalPrivileges.add(globalPrivilege);
        globalPrivilege.setRole(this);
    }

    public void removeGlobalPrivilege(GlobalPrivilege globalPrivilege) {
        globalPrivileges.remove(globalPrivilege);
        globalPrivilege.setRole(null);
    }

}
