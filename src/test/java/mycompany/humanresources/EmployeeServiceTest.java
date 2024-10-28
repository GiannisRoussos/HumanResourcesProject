package mycompany.humanresources;


import mycompany.humanresources.dto.EmployeeUpdateRequestDto;
import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.entity.Department;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.EmployeeDetails;
import mycompany.humanresources.repository.DepartmentRepository;
import mycompany.humanresources.service.ContractService;
import mycompany.humanresources.service.DepartmentService;
import mycompany.humanresources.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ContractService contractService;

    @Test
    public void testCreateEmployee() {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        Employee employee = new Employee();

        //create employeeDetails and employee
        employeeDetails.setAvailability(true);
        employeeDetails.setSkills("Java");
        employee.setEmployeeDetails(employeeDetails);
        employeeDetails.setEmployee(employee);

        employee.setFirstName("Giannis");
        employee.setLastName("Roussos");
        employee.setEmail("roussos_giannis@hotmail.com");
        employee.setPhoneNumber("6988906653");
        employee.setHireDate(LocalDate.of(2024, 9, 12));
        //Create Department before save the employee
        Department department = new Department();
        department.setDepartmentName("Engineering");
        Department savedDepartment = departmentRepository.save(department);
        employee.setDepartment(savedDepartment);
        employee.setTitle("Mathematician");
        employeeService.saveEmployee(employee);
        //create contract for this employee in this department
        Contract contract = new Contract();
        contract.setEmployee(employee);
        contract.setStartDate(employee.getHireDate());
        contract.setSalary(new BigDecimal("1200"));
        contractService.saveContract(contract);

        Employee employee1 = new Employee();
        employee1.setFirstName("Tassos");
        employee1.setLastName("Georgiadis");
        employee1.setEmail("tasgeo@hotmail.com");
        employee1.setPhoneNumber("6988906654");
        employee1.setHireDate(LocalDate.of(2024, 9, 13));
        //Create Department before save the employee
        // Department department = new Department();
        //department.setDepartmentName("Engineering");
        //employee.setDepartment(department);
        employee1.setTitle("IT");
        employeeService.saveEmployee(employee1);
    }

    @Test
    public void testCreateRandomEmployees() {
        for (int i = 0; i <= 3; i++){
            Employee employeei = new Employee();
            employeei.setFirstName("Tassos"+ i);
            employeei.setLastName("Georgiadis"+i);
            employeei.setEmail("tasgeo@hotmail.com");
            employeei.setPhoneNumber("6988906654");
            employeei.setHireDate(LocalDate.of(2024, 9, 13));
            employeei.setTitle("Java");
            employeeService.saveEmployee(employeei);
        }
    }

    @Test
    void testViewCustomer() {
        employeeService.getEmployee(UUID.fromString("b4f32baa-bfde-4b21-a725-744da106acad"));
    }

    @Test
    void testDeleteEmployee() {
        employeeService.deleteEmployee(UUID.fromString("b4f32baa-bfde-4b21-a725-744da106acad"));
    }

    @Test
    public void testCreateOtherEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Argyris");
        employee1.setLastName("Pap");
        employee1.setEmail("argypap@hotmail.com");
        employee1.setPhoneNumber("6977686711");
        employee1.setHireDate(LocalDate.of(2024, 9, 24));
        employee1.setTitle("Engineering");
        employeeService.saveEmployee(employee1);
        Employee employee2 = new Employee();
        employee2.setFirstName("Tasos");
        employee2.setLastName("Kar");
        employee2.setEmail("kartas@hotmail.com");
        employee2.setPhoneNumber("6977686712");
        employee2.setHireDate(LocalDate.of(2024, 9, 24));
        employee2.setTitle("Engineering");
        employeeService.saveEmployee(employee2);
        Employee employee3 = new Employee();
        employee3.setFirstName("Gagik");
        employee3.setLastName("Petrosian");
        employee3.setEmail("petrgak@hotmail.com");
        employee3.setPhoneNumber("6977686714");
        employee3.setHireDate(LocalDate.of(2024, 9, 24));
        employee3.setTitle("Engineering");
        employeeService.saveEmployee(employee3);
    }

    @Test
    void testAssignEmployeeToDepartment() {
        EmployeeUpdateRequestDto employeeUpdateRequestDto = new EmployeeUpdateRequestDto();
        // Set all necessary fields in employeeUpdateRequestDto
        employeeUpdateRequestDto.setEmployeeId(UUID.fromString("25b27a08-1844-41f4-847a-52420f72100c"));
        employeeUpdateRequestDto.setFirstName("Giannis");
        employeeUpdateRequestDto.setLastName("Antetokounmpo");
        employeeUpdateRequestDto.setEmail("giannis@example.com");
        employeeUpdateRequestDto.setPhoneNumber("1234567890");
        employeeUpdateRequestDto.setHireDate(LocalDate.of(2023, 10, 10));
        employeeUpdateRequestDto.setTitle("Senior Developer");
        employeeUpdateRequestDto.setDescription("Experienced in full stack development");
        employeeUpdateRequestDto.setPersonalData("Some personal data");
        employeeUpdateRequestDto.setDepartmentId(UUID.fromString("ba00e49f-84bf-4152-9437-542b9e4fc450"));
        employeeUpdateRequestDto.setHead(true);
        employeeUpdateRequestDto.setAvailability(true);
        employeeUpdateRequestDto.setSkills("Frontend");

        employeeService.updateEmployeeAndLinkDepartment(employeeUpdateRequestDto);
    }
}
