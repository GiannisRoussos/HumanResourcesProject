package mycompany.humanresources.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class EmployeeUpdateRequestDto {
  private UUID employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private LocalDate hireDate;
  private String title;
  private String description;
  private String personalData;

  private UUID departmentId;
  private boolean isHead;
  private boolean availability;
  private String skills;
}
