package mycompany.humanresources;


import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.PerformanceReview;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.repository.PerformanceReviewRepository;
import mycompany.humanresources.service.ContractService;
import mycompany.humanresources.service.EmployeeService;
import mycompany.humanresources.service.SalaryAdjustmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

@SpringBootTest
public class SalaryAdjustmentTest {

    @Autowired
    private SalaryAdjustmentService salaryAdjustmentService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSavePerformanceReview() {
        salaryAdjustmentService.savePerformanceReview(UUID.fromString("4a4e5c83-209e-4578-8c89-009db85ac8d9"),
                LocalDate.of(2024, 9, 24), "Reviewer", "Nice Job,keep up", 3,
                LocalDate.now());
    }

    @Test
    void testGetPerformanceReviews() {
        Employee employee = employeeRepository.findById(
                UUID.fromString("4a4e5c83-209e-4578-8c89-009db85ac8d9")).orElseThrow();
        PerformanceReview performanceReviews = employee.getPerformanceReviews().get(1);
        System.out.println(performanceReviews);
    }

    @Test
    public void testApplyAdjustedSalary() {
        salaryAdjustmentService.applyAnnualSalaryAdjustment(UUID.fromString("4a4e5c83-209e-4578-8c89-009db85ac8d9"),
                LocalDate.of(2024, 9, 24));
    }


    @Test
    void testApplyAllSalaryAdjustedService() {
        salaryAdjustmentService.applyAllSalaryAdjustment();
    }

}
