package ch.renewinkler.model;

import ch.renewinkler.model.security.CustomPrivilege;
import ch.renewinkler.model.security.PrivilegeType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CategoryTest {

    @Test
    public void testHasCustomPrivilege() {
        Category category = Category.builder().id(1l).name("category").build();
        CustomPrivilege customPrivilege = CustomPrivilege.builder().category(category).type(PrivilegeType.READ).build();
        List<CustomPrivilege> customPrivileges = new ArrayList<>();
        customPrivileges.add(customPrivilege);
        category.setCustomPrivileges(customPrivileges);

//        assertTrue(category.hasCustomPrivilege(PrivilegeType.READ));
//        assertFalse(category.hasCustomPrivilege(PrivilegeType.CREATE));
    }

}