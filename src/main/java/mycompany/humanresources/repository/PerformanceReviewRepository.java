package mycompany.humanresources.repository;

import mycompany.humanresources.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, UUID> {
    List<PerformanceReview> findByEmployeeId(UUID employeeId);
}
