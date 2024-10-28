package mycompany.humanresources;


import mycompany.humanresources.entity.Department;
import mycompany.humanresources.service.DepartmentService;
import mycompany.humanresources.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setDepartmentName("Mathematics");
        departmentService.saveDepartment(department);
    }

    @Test
    void testViewDepartment() {
        departmentService.getDepartment(UUID.fromString(
                "ba00e49f-84bf-4152-9437-542b9e4fc450"));
    }

    @Test
    void testDeleteDepartment() {
        departmentService.deleteDepartment(UUID.fromString(""));
    }

}
