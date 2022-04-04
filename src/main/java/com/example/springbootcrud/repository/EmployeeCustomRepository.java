package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.model.Position;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EmployeeCustomRepository {

  public List<Employee> findEmployeesByProperties(String firstName, String lastName,
                                                  Position position, Date startDate,
                                                  BigDecimal salary, Pageable page);
}
