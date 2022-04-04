package com.example.springbootcrud.model;

import java.util.List;

public class ResponseEmployee {
  private long total;
  private int totalPage;
  private List<Employee> employees;
  public long getTotal() {
    return total;
  }
  public void setTotal(long total) {
    this.total = total;
  }
  public int getTotalPage() {
    return totalPage;
  }
  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }
  public List<Employee> getEmployees() {
    return employees;
  }
  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
  public ResponseEmployee(long total, String typeTotalDictionary) {
    super();
    this.total = total;
    employees = employees;
  }
  public ResponseEmployee() {
    super();
  }
}
