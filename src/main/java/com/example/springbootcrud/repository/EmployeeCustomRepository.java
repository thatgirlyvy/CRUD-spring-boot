package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Employee;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EmployeeCustomRepository {

  public List<Employee> findEmployeesByProperties(String firstName, String lastName,
                                                  String position, LocalDate startDate,
                                                  BigDecimal salary, Pageable page);
}
