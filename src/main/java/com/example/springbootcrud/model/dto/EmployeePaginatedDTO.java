package com.example.springbootcrud.model.dto;

import com.example.springbootcrud.model.Employee;

import java.util.List;

public class EmployeePaginatedDTO {

  private List<Employee> data;

  private Integer totalElements;

  public EmployeePaginatedDTO( List<Employee> data, Integer totalElements) {
    this.data = data;
    this.totalElements = totalElements;
  }

  public List<Employee> getData() {
    return data;
  }

  public void setData(List<Employee> data) {
    this.data = data;
  }

  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }
}
