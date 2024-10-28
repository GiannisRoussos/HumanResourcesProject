package mycompany.humanresources.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.entity.PerformanceReview;
import mycompany.humanresources.repository.ContractRepository;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class SalaryAdjustmentService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;
    @Autowired
    private ContractRepository contractRepository;


    public void applyAllSalaryAdjustment() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            Contract contract = employee.getContract();
            if (contract != null) {
                BigDecimal baseSalary = contract.getSalary();
                // Retrieve performance reviews
                List<PerformanceReview> performanceReviews = employee.getPerformanceReviews();
                if (performanceReviews != null && !performanceReviews.isEmpty()) {
                    //take latest performance review
                    PerformanceReview latestReview = performanceReviews.get(performanceReviews.size() - 1);
                    Integer rating = latestReview.getRating();
                    //Adjust salary based on the rating
                    BigDecimal adjustedSalary = calculateAdjustedSalary(baseSalary, rating);
                    // Update the employee's contract with the new salary
                    contract.setSalary(adjustedSalary);
                    contractRepository.save(contract);
                }
            }
        }
    }

    public void savePerformanceReview(UUID employeeId, LocalDate reviewDate, String reviewer, String comments,
                                      Integer rating, LocalDate adjustmentDate) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Contract contract = employee.getContract();
        PerformanceReview performanceReview = new PerformanceReview();
        performanceReview.setEmployee(employee);
        performanceReview.setContract(contract);
        performanceReview.setReviewDate(reviewDate);
        performanceReview.setReviewer(reviewer);
        performanceReview.setComments(comments);
        performanceReview.setRating(rating);
        performanceReview.setAdjustmentDate(adjustmentDate);
        performanceReviewRepository.save(performanceReview);
    }

    public void applyAnnualSalaryAdjustment(UUID employeeId, LocalDate reviewDate) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Contract contract = employee.getContract();
        BigDecimal baseSalary = contract.getSalary();
        PerformanceReview performanceReview = contract.getPerformanceReview();
        int rating = performanceReview.getRating();
        BigDecimal adjustedSalary = calculateAdjustedSalary(baseSalary, rating);
        contract.setSalary(adjustedSalary);
        contractRepository.save(contract);
    }


    private BigDecimal calculateAdjustedSalary(BigDecimal baseSalary, Integer rating) {
        BigDecimal adjustmentPercentage;
        switch (rating) {
            case 5:
                adjustmentPercentage = new BigDecimal("0.10"); // 10% increase
                break;
            case 4:
                adjustmentPercentage = new BigDecimal("0.07"); // 7% increase
                break;
            case 3:
                adjustmentPercentage = new BigDecimal("0.04"); // 4% increase
                break;
            case 2:
                adjustmentPercentage = new BigDecimal("0.02"); // 2% increase
                break;
            case 1:
                adjustmentPercentage = new BigDecimal("0.00"); // 0% increase
                break;
            default:
                adjustmentPercentage = new BigDecimal("0.00"); // Default to no increase
                break;
        }
        return baseSalary.add(baseSalary.multiply(adjustmentPercentage));
    }
}