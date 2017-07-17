package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles", "userPrivileges"})
@Builder
@Data
public class User extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    // Must be unique since authentication infos are fetched by username.
    // This is nothing special. Works like this in most applications.
    private String username;

    @NotNull
    private String password;

    private boolean enabled;

    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPrivilege> userPrivileges = new ArrayList<>();

    public void addUserPrivilege(UserPrivilege userPrivilege) {
        userPrivileges.add(userPrivilege);
        userPrivilege.setUser(this);
    }

    public void removeUserPrivilege(UserPrivilege userPrivilege) {
        userPrivileges.remove(userPrivilege);
        userPrivilege.setUser(null);
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

}
