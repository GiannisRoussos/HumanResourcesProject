package mycompany.humanresources;

import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.entity.Department;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.repository.ContractRepository;
import mycompany.humanresources.repository.DepartmentRepository;
import mycompany.humanresources.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HumanResourcesApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void testCreateEmployee() {
        // Create department
        Department department = new Department();
        department.setDepartmentName("CC");
        department = departmentRepository.save(department);

        // create employee
        Employee employee = new Employee();
        employee.setFirstName("Tasos");
        employee.setLastName("Georgiadis");
        employee.setEmail("tasgeo@example.com");
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        //create contract
        Contract contract = new Contract();
        contract.setEmployee(employee);
        contract.setStartDate(LocalDate.of(2022, 1, 15));
        contract.setEndDate(LocalDate.of(2023, 1, 15));
        contract.setSalary(new BigDecimal("50000.00"));
        contractRepository.save(contract);

        // check if employee saved
        Optional<Employee> retrievedEmployee = employeeRepository.findById(employee.getId());
        assertTrue(retrievedEmployee.isPresent());
        assertEquals("Tasos", retrievedEmployee.get().getFirstName());
        assertEquals("Georgiadis", retrievedEmployee.get().getLastName());
        assertEquals(department.getId(), retrievedEmployee.get().getDepartment().getId());
    }
}


