package ch.renewinkler.model;

import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CustomPrivilege> customPrivileges = new ArrayList<>();

    public boolean hasCustomPrivilege(String name, PrivilegeType type) {
        return customPrivileges.stream()
                .filter(c -> c.getUser().getUsername().equals(name))
                .map(c -> c.getType())
                .filter(t -> t.equals(type))
                .findAny()
                .isPresent();
    }

}
