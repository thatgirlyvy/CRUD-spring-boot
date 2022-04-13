package com.example.springbootcrud.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.springbootcrud.model.dto.EmployeeFilterDTO;
import com.example.springbootcrud.model.dto.EmployeePaginatedDTO;
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
  public Employee getById(@PathVariable UUID id) {
    return employeeService.getById(id);
  }


  @GetMapping
  public ResponseEntity<EmployeePaginatedDTO> list(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                                                   @RequestParam(required = false) String position, @RequestParam(required = false) String startDate,
                                                   @RequestParam(required = false) BigDecimal salary, @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "id") String field){

 //   try {

      LocalDate start = null;

      String pos = null;

      if (startDate != null)
        start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

      if (position != null)
        pos = String.valueOf(position.toUpperCase());

      EmployeeFilterDTO dto = new EmployeeFilterDTO(firstName, lastName, pos, start, salary);

      EmployeePaginatedDTO employees = employeeService.find(page, pageSize, field, dto);

      return ResponseEntity.ok(employees);

//    } catch (ParseException e) {
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//    }
  }

  @PostMapping
  public Employee save(@RequestBody Employee employee) {
    return employeeService.save(employee);
  }

  @PutMapping
  public Employee update(@RequestBody Employee employee){
    return employeeService.update(employee);
  }

  @DeleteMapping("{uuid}")
  public boolean delete(@PathVariable UUID uuid) {
    return employeeService.deleteById(uuid);
  }
}
