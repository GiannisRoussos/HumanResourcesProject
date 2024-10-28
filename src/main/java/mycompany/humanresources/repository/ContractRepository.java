package mycompany.humanresources.repository;

import mycompany.humanresources.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {
}
