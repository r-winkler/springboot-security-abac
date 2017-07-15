package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC")
public abstract class PrivilegeEntity extends BaseEntity {

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "privilegeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomPrivilege> customPrivileges = new ArrayList<>();

    public boolean hasCustomPrivilege(String name, PrivilegeType type) {
        return customPrivileges.stream()
                .filter(c -> c.getUser().getUsername().equals(name))
                .map(c -> c.getType())
                .filter(t -> t.equals(type))
                .findAny()
                .isPresent();
    }

    public void addCustomPrivilege(CustomPrivilege customPrivilege) {
        customPrivileges.add(customPrivilege);
        customPrivilege.setPrivilegeEntity(this);
    }

    public void removeCustomPrivilege(CustomPrivilege customPrivilege) {
        customPrivileges.remove(customPrivilege);
        customPrivilege.setPrivilegeEntity(null);
    }

}
