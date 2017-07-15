package ch.renewinkler.model.security;

import ch.renewinkler.model.BaseEntity;
import ch.renewinkler.model.Category;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class CustomPrivilege extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PrivilegeType type;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
