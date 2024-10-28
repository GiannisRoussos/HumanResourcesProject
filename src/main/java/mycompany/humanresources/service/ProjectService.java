package mycompany.humanresources.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.EmployeeDetails;
import mycompany.humanresources.entity.Project;
import mycompany.humanresources.repository.EmployeeDetailsRepository;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeDetailsRepository employeeDetailsRepository;

    // Create or Update projectName
    public void saveProject(Project project) {
        log.debug("#saveProject: project={}", project);
        projectRepository.save(project);
    }

    // Read Project by ID
    public Optional<Project> getProject(UUID id) {
        log.debug("#getProject: id={}", id);
        return projectRepository.findById(id);
    }

    // Read all Projects
    public List<Project> findAllProjects() {
        log.debug("#findAllProjects");
        return projectRepository.findAll();
    }

    // Delete Project by ID
    public void deleteProject(UUID id) {
        log.debug("#deleteProject: id={}", id);
        projectRepository.deleteById(id);
    }

    public void attachAndAssignEmployeeDetailsToProject(UUID employeeId, UUID projectId) {
        // Retrieve the Employee by employeeId
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("not found"));
        EmployeeDetails employeeDetails = employee.getEmployeeDetails();

        // Retrieve the Project by projectId
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("not found"));

        // Check if employee's skills match project requirements and if employee is available
        boolean skillsMatch = project.getRequiredSkills().equalsIgnoreCase(employeeDetails.getSkills());
        boolean isAvailable = employeeDetails.isAvailability();

        if (skillsMatch && isAvailable) {
            // Assign Employee to Project
            project.getEmployees().add(employee);
            employee.getProjects().add(project);
            // Update the employee's availability to false
            employeeDetails.setAvailability(false);
            // Save Project to persist the association
            projectRepository.save(project);
            employeeRepository.save(employee);
        }

    }
}


