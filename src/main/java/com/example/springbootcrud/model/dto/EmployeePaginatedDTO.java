package com.example.springbootcrud.model.dto;

import com.example.springbootcrud.model.Employee;

import java.util.List;

public class EmployeePaginatedDTO {

  private List<Employee> data;

  private Long totalElements;

  public EmployeePaginatedDTO( List<Employee> data, Long totalElements) {
    this.data = data;
    this.totalElements = totalElements;
  }

  public List<Employee> getData() {
    return data;
  }

  public void setData(List<Employee> data) {
    this.data = data;
  }

  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }
}
