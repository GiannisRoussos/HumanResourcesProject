package mycompany.humanresources.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycompany.humanresources.entity.Department;
import mycompany.humanresources.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j

public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    // Create or Update DepartmentName
    public void saveDepartment(Department department) {
        log.debug("#saveDepartment: department={}", department);
        departmentRepository.save(department);
    }

    // Read Department by ID
    public Optional<Department> getDepartment(UUID id) {
        log.debug("#getDepartment: id={}", id);
        return departmentRepository.findById(id);
    }

    // Read all Departments
    public List<Department> findAllDepartments() {
        log.debug("#findAllDepartments");
        return departmentRepository.findAll();
    }

    // Delete Department by ID
    public void deleteDepartment(UUID id) {
        log.debug("#deleteDepartment: id={}", id);
        departmentRepository.deleteById(id);
    }
}
