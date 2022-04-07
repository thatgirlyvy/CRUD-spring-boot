package com.example.springbootcrud.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.example.springbootcrud.model.Position;
import com.example.springbootcrud.model.dto.EmployeeFilterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.service.EmployeeService;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("employees")

public class EmployeesController {

  private final EmployeeService employeeService;

  public EmployeesController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("{id}")
  public ResponseEntity<Employee> get(@PathVariable UUID id) {
    return employeeService.get(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound()
        .build());
  }


  @GetMapping
  public ResponseEntity<List<Employee>> list(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String position, @RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) BigDecimal salary, @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "id") String field){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {

      Date start = null;

      Position pos = null;

      if (startDate != null)
        start = dateFormat.parse(startDate);

      if (position != null)
        pos = Position.valueOf(position.toUpperCase());

      EmployeeFilterDTO dto = new EmployeeFilterDTO(firstName, lastName, pos, start, salary);

      List<Employee> employees = employeeService.find(page, pageSize, field, dto);

      return ResponseEntity.ok(employees);

    } catch (ParseException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  public UUID save(@RequestBody Employee employee) {
    return employeeService.save(employee)
      .getId();
  }

  @PutMapping("{id}")
  public Employee edit(@PathVariable UUID id, @RequestBody Employee employee) {
    employee.setId(id);
    return employeeService.save(employee);
  }

  @PostMapping("/delete")
  public void remove(@RequestBody List<UUID> ids) {
    employeeService.removeAll(ids);
  }
}
