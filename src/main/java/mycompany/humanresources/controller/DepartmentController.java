package mycompany.humanresources.controller;


import lombok.RequiredArgsConstructor;
import mycompany.humanresources.entity.Department;
import mycompany.humanresources.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable UUID id) {
        return ResponseEntity.of(departmentService.getDepartment(id));
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.findAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department
                                                               department) {
        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable UUID id,
            @RequestBody Department updatedDepartment) {
        updatedDepartment.setId(id);
        departmentService.saveDepartment(updatedDepartment);
        return ResponseEntity.ok(updatedDepartment);
    }

}
