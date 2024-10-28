package mycompany.humanresources.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private String title;

    @Lob
    private String description;

    @Lob
    private String personalData;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Contract contract;

    //many-to-one relationship with department
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    //  many-to-many relationship with  Project
    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private Set<Project> projects;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmployeeDetails employeeDetails;

    @OneToMany(mappedBy = "employee",  cascade = CascadeType.ALL)
    private List<PerformanceReview> performanceReviews = new ArrayList<>();

}