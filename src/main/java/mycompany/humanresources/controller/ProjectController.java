package mycompany.humanresources.controller;

import lombok.RequiredArgsConstructor;
import mycompany.humanresources.entity.Project;
import mycompany.humanresources.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProject(@PathVariable UUID id) {
        return ResponseEntity.of(projectService.getProject(id));
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.findAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project
                                                         project) {
        projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable UUID id,
            @RequestBody Project updatedProject) {
        updatedProject.setId(id);
        projectService.saveProject(updatedProject);
        return ResponseEntity.ok(updatedProject);
    }

}
