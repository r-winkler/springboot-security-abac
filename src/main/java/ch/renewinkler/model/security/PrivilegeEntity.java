package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
public abstract class PrivilegeEntity extends BaseEntity {

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
