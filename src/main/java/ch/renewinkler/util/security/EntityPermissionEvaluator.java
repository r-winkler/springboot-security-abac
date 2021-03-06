package ch.renewinkler.util.security;

import ch.renewinkler.model.security.PrivilegeEntity;
import ch.renewinkler.model.security.PrivilegeType;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class EntityPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if (target instanceof PrivilegeEntity) {
            PrivilegeEntity entity = (PrivilegeEntity) target;
            if ("create".equals(permission)) {
                return entity.hasUserPrivilege(authentication.getName(), PrivilegeType.CREATE);
            }
            if ("read".equals(permission)) {
                return entity.hasUserPrivilege(authentication.getName(), PrivilegeType.READ);
            }
            if ("update".equals(permission)) {
                return entity.hasUserPrivilege(authentication.getName(), PrivilegeType.UPDATE);
            }
            if ("delete".equals(permission)) {
                return entity.hasUserPrivilege(authentication.getName(), PrivilegeType.DELETE);
            }
        }
        throw new UnsupportedOperationException(
                "hasPermission not supported for object <" + target
                        + "> and permission <" + permission + ">");
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

}
