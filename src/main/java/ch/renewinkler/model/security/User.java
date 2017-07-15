package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class User extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    private String password;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomPrivilege> customPrivileges;

    public void addCustomPrivilege(CustomPrivilege customPrivilege) {
        customPrivileges.add(customPrivilege);
        customPrivilege.setUser(this);
    }

    public void removeCustomPrivilege(CustomPrivilege customPrivilege) {
        customPrivileges.remove(customPrivilege);
        customPrivilege.setUser(null);
    }

}
