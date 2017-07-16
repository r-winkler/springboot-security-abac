package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomPrivilege extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PrivilegeType type;

    @ManyToOne
    @JoinColumn(name = "PRIVILEGE_ENTITY_ID")
    private PrivilegeEntity privilegeEntity;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
