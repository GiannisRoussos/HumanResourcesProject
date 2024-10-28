package mycompany.humanresources.repository;

import mycompany.humanresources.entity.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, UUID> {
}
