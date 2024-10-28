package mycompany.humanresources;

import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.service.TimeTrackingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class TimeTrackingTest {

    @Autowired
    private TimeTrackingService timeTrackingService;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testTimeTracking() {
        List<Employee> employees = employeeRepository.findAllById(List.of
                (UUID.fromString("c0c09e2a-165a-44be-aaa3-816266b9b009"),
                        UUID.fromString("1fb82d1f-92e8-43a9-803b-f3ab3de5612b"),
                        UUID.fromString("98a38e4c-054e-4dec-816f-1f4e464142a8"),
                        UUID.fromString("e0bd6db2-c736-4746-af8c-2c65deda4be1")));

        for (Employee employee : employees) {

            for (int j = 0; j < 21; j++) {
                Random random = new Random();
                double minValue = 4.01;
                double maxValue = 10.0;
                double randomDouble = minValue + (maxValue - minValue) * random.nextDouble();
                timeTrackingService.logHours(employee.getId(),
                        UUID.fromString("cfe48529-05a0-4a81-9f5d-65d3b5fe7a33"),
                        BigDecimal.valueOf(randomDouble).setScale(10, BigDecimal.ROUND_HALF_UP),
                        LocalDate.now());
            }
        }


    }
}