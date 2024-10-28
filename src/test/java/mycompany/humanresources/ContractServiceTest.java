package mycompany.humanresources;

import mycompany.humanresources.entity.Contract;
import mycompany.humanresources.entity.Employee;
import mycompany.humanresources.repository.EmployeeRepository;
import mycompany.humanresources.service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class ContractServiceTest {
    @Autowired
    private ContractService contractService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        //create contract for a new employee
        Contract contract = new Contract();
        //create new employee
        Employee employee1 = new Employee();
        employee1.setFirstName("Argyris");
        employee1.setLastName("Pap");
        employee1.setEmail("targpap@hotmail.com");
        employee1.setPhoneNumber("6977686711");
        employee1.setHireDate(LocalDate.of(2024, 9, 13));
        //save new employee
        Employee savedEmployee = employeeRepository.save(employee1);
        //save contract details
        contract.setEmployee(savedEmployee);
        contract.setStartDate(employee1.getHireDate());
        contract.setSalary(new BigDecimal("1200.0"));
        contractService.saveContract(contract);
    }

    @Test
    //delete only contract
    void testDeleteContract() {
        contractService.deleteContract(UUID.fromString("5951e7d8-f761-499a-9ee5-3792ba1722be"));
    }
}
