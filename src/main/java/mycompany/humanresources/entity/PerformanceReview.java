package mycompany.humanresources.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "performance_review")
public class PerformanceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate reviewDate;

    private String reviewer;

    private String comments;

    private Integer rating;

    //private BigDecimal salaryAdjustmentAmount;

    private LocalDate adjustmentDate;

    @OneToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

}
