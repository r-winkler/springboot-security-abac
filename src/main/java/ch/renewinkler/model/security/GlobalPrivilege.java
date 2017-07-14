package ch.renewinkler.model.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class GlobalPrivilege {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private PrivilegeType type;
    
}
