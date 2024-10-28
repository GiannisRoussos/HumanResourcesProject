package mycompany.humanresources;

import mycompany.humanresources.entity.Project;
import mycompany.humanresources.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ProjectTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setProjectName("HumanResourcesApp");
        project.setRequiredSkills("IT");
        projectService.saveProject(project);
    }

    @Test
    void testViewProject() {
        projectService.getProject(UUID.fromString("2"));
    }

    @Test
    void testDeleteProject() {
        projectService.deleteProject(UUID.fromString("2"));
    }

    @Test
    public void testAssignRequiredEmployeeToProject() {
        projectService.attachAndAssignEmployeeDetailsToProject(UUID.fromString("e9f16305-e230-49f8-ae8b-5d18a0929768"),UUID.fromString("05369889-b8ab-4df0-bb55-87960578d00d"));
    }

}
