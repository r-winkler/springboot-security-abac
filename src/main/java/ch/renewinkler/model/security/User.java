package ch.renewinkler.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private List<CustomPrivilege> customPrivileges = new ArrayList<>();

}
