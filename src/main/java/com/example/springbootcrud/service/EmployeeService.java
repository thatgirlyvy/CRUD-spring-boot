package com.example.springbootcrud.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.example.springbootcrud.model.dto.EmployeeFilterDTO;
import com.example.springbootcrud.model.dto.EmployeePaginatedDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.repository.EmployeeRepository;

@Service
public class EmployeeService {

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

  public Employee update(Employee employee) {
    Employee employee1 = employeeRepository.findById(employee.getId()).orElse(null);
    System.out.println(employee);
    if(employee1 == null) {
      System.out.println("employee not found");
      return employeeRepository.save(employee);
    } else {
      employee1.setFirstName(employee.getFirstName());
      employee1.setLastName(employee.getLastName());
      employee1.setPosition(employee.getPosition());
      employee1.setStartDate(employee.getStartDate());
      employee1.setSalary(employee.getSalary());
      employeeRepository.save(employee1);
    }
    return employee;
  }

  public boolean deleteById(UUID uuid) {
    Employee employee = employeeRepository.getById(uuid);
    if(employee != null) {
      employeeRepository.deleteById(uuid);
      return true;
    }
    return false;
    }


  public Employee getById(UUID uuid) {
    return employeeRepository.findById(uuid).orElse(null);
  }

  public List<Employee> list(String firstName, String lastName, String position, LocalDate startDate, BigDecimal salary, Integer page,
                             Integer pageSize, String field) {
    return employeeRepository.findEmployeesByProperties(firstName, lastName, position, startDate, salary, PageRequest.of(page, pageSize, Sort.by(field)));
  }

  public EmployeePaginatedDTO find(int page, int size, String field, EmployeeFilterDTO dto) {

    // Dans le cas où nous souhaitons avoir toutes les données dans la BD.
    if (dto.getFirstName() == null && dto.getLastName() == null && dto.getPosition() == null && dto.getStartDate() == null && dto.getSalary() == null){
      Page<Employee> pageFilter = employeeRepository.findAll(PageRequest.of(page, size, Sort.by(field)));
      return new EmployeePaginatedDTO(pageFilter.getContent(), pageFilter.getTotalElements()) ;
  }

    // Dans le cas où nous souhaitons appliquer un filtre sur les données à rechercher.
    Employee employee = new Employee();
    employee.setFirstName(dto.getFirstName());
    employee.setLastName(dto.getLastName());
    employee.setPosition(dto.getPosition());
    employee.setSalary(dto.getSalary());
    employee.setStartDate(dto.getStartDate());

    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withIgnoreNullValues();

    Example<Employee> example = Example.of(employee, exampleMatcher);

    Page<Employee> pageFind = employeeRepository.findAll(example, PageRequest.of(page, size, Sort.by(field)));

    return new EmployeePaginatedDTO(pageFind.getContent(), pageFind.getTotalElements()) ;

  }

  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }


}
