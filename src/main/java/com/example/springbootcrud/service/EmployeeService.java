package com.example.springbootcrud.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springbootcrud.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.repository.EmployeeRepository;

@Service

public class EmployeeService {
  @Autowired
  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee save(Employee employee) {
    if (employee.getId() == null) {
      employee.setId(UUID.randomUUID());
    }
    return employeeRepository.save(employee);
  }


  public void removeAll(List<UUID> ids) {
    ids.forEach(employeeRepository::deleteById);
  }

  public Optional<Employee> get(UUID uuid) {
    return employeeRepository.findById(uuid);
  }

  public List<Employee> list(String firstName, String lastName, Position position, Date startDate, BigDecimal salary, Integer page,
                             Integer pageSize, String field) {
    return employeeRepository.findEmployeesByProperties(firstName, lastName, position, startDate, salary, PageRequest.of(page, pageSize, Sort.by(field)));
  }

}
