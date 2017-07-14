package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Role extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ROLE_ID")
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ROLE_ID")
    private List<GlobalPrivilege> globalPrivileges = new ArrayList<>();

}
