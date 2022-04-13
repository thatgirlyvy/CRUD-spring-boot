package com.example.springbootcrud.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EmployeeFilterDTO {

  private String firstName;

  private String lastName;

  private String position;

  private LocalDate startDate;

  private BigDecimal salary;

  public EmployeeFilterDTO() {
  }

  public EmployeeFilterDTO(String firstName, String lastName, String position, LocalDate startDate, BigDecimal salary) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.startDate = startDate;
    this.salary = salary;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPosition() {
    return position;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }



}
