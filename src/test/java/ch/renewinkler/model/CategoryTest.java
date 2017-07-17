package ch.renewinkler.model;

import ch.renewinkler.model.security.PrivilegeType;
import ch.renewinkler.model.security.UserPrivilege;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CategoryTest {

    @Test
    public void testHasUserPrivilege() {
        Category category = Category.builder().name("category").build();
        UserPrivilege userPrivilege = UserPrivilege.builder().privilegeEntity(category).type(PrivilegeType.READ).build();
        List<UserPrivilege> userPrivileges = new ArrayList<>();
        userPrivileges.add(userPrivilege);
        category.setUserPrivileges(userPrivileges);

//        assertTrue(category.hasUserPrivilege(PrivilegeType.READ));
//        assertFalse(category.hasUserPrivilege(PrivilegeType.CREATE));
    }

}