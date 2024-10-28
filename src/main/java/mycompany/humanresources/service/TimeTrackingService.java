package mycompany.humanresources.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.Project;
import mycompany.humanresources.entity.TimeEntry;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.repository.ProjectRepository;
import mycompany.humanresources.repository.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class TimeTrackingService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private final TimeEntryRepository timeEntryRepository;
    @Autowired
    private final ProjectRepository projectRepository;

    public void logHours(UUID employeeId, UUID projectId, BigDecimal hours, LocalDate entryDate) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Project project = projectRepository.findById(projectId).orElseThrow();
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEmployee(employee);
        timeEntry.setProject(project);
        timeEntry.setHours(hours);
        timeEntry.setEntryDate(entryDate);
        timeEntryRepository.save(timeEntry);
    }

}
