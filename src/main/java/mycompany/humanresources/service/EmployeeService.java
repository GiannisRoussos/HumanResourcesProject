package mycompany.humanresources.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mycompany.humanresources.dto.EmployeeUpdateRequestDto;
import mycompany.humanresources.entity.Department;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.EmployeeDetails;
import mycompany.humanresources.repository.DepartmentRepository;
import mycompany.humanresources.repository.EmployeeDetailsRepository;
import mycompany.humanresources.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private final EmployeeDetailsRepository employeeDetailsRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    // Create or Update Employee
    public void saveEmployee(Employee employee) {
        log.debug("#saveEmployee: employee={}", employee);
        employeeRepository.save(employee);
    }

    // Read Employee by ID
    public Optional<Employee> getEmployee(UUID id) {
        log.debug("#getEmployee: id={}", id);
        return employeeRepository.findById(id);
    }

    // Read all Employees
    public List<Employee> findAllEmployees() {
        log.debug("#findAllEmployees");
        return employeeRepository.findAll();
    }

    // Delete Employee by ID
    public void deleteEmployee(UUID id) {
        log.debug("#deleteEmployee: id={}", id);
        employeeRepository.deleteById(id);
    }

    public void updateEmployeeAndLinkDepartment(EmployeeUpdateRequestDto updateRequest) {
        UUID employeeId = updateRequest.getEmployeeId();
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        // Update employee fields
        employee.setFirstName(updateRequest.getFirstName());
        employee.setLastName(updateRequest.getLastName());
        employee.setEmail(updateRequest.getEmail());
        employee.setPhoneNumber(updateRequest.getPhoneNumber());
        employee.setHireDate(updateRequest.getHireDate());
        employee.setTitle(updateRequest.getTitle());
        employee.setDescription(updateRequest.getDescription());
        employee.setPersonalData(updateRequest.getPersonalData());
        // Find and link department
        Department department = departmentRepository.findById(updateRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        // Assuming these fields exist in the Employee entity or need custom setting
        if (employee.getEmployeeDetails() == null) {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployee(employee);
            employeeDetails.setDepartmentHead(updateRequest.isHead());
            employeeDetails.setAvailability(updateRequest.isAvailability());
            employeeDetails.setSkills(updateRequest.getSkills());
            employeeDetailsRepository.save(employeeDetails);
        } else {
            EmployeeDetails employeeDetails = employee.getEmployeeDetails();
            employeeDetails.setEmployee(employee);
            employeeDetails.setDepartmentHead(updateRequest.isHead());
            employeeDetails.setAvailability(updateRequest.isAvailability());
            employeeDetails.setSkills(updateRequest.getSkills());
            employeeDetailsRepository.save(employeeDetails);
        }

        employeeRepository.save(employee);
    }
}

