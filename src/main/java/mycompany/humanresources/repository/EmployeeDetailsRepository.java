package mycompany.humanresources.repository;

import mycompany.humanresources.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, UUID> {
}
