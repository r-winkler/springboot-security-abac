package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Data
public class GlobalPrivilege extends BaseEntity {

    @Enumerated
    private PrivilegeType type;
    
}
