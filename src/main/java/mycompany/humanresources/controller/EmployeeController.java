package mycompany.humanresources.controller;

import lombok.RequiredArgsConstructor;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable UUID id) {
        return ResponseEntity.of(employeeService.getEmployee(id));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    public void createEmployee(@RequestBody Employee
                                       employee) {
        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable UUID id,
            @RequestBody Employee updatedEmployee) {
        updatedEmployee.setId(id);
        employeeService.saveEmployee(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

}