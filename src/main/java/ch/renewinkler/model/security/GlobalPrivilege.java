package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class GlobalPrivilege extends BaseEntity {

    @Enumerated
    private PrivilegeType type;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    
}
