package mycompany.humanresources.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String departmentName;

    //  one-to-many with Project
    @OneToMany(mappedBy = "department")
    private Set<Project> projects;

    // one-to-many with Employee
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

}