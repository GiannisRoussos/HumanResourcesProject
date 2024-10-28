package mycompany.humanresources.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class TimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name= "project_id" , nullable = false)
    private Project project;

    @Column(nullable = false)
    private BigDecimal hours;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;
}
