package com.example.springbootcrud.model.dto;

import com.example.springbootcrud.model.Position;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeFilterDTO {

  private String firstName;

  private String lastName;

  private Position position;

  private Date startDate;

  private BigDecimal salary;

  public EmployeeFilterDTO() {
  }

  public EmployeeFilterDTO(String firstName, String lastName, Position position, Date startDate, BigDecimal salary) {
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

  public Position getPosition() {
    return position;
  }

  public Date getStartDate() {
    return startDate;
  }

  public BigDecimal getSalary() {
    return salary;
  }



}
