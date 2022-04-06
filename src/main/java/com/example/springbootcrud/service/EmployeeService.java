package com.example.springbootcrud.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springbootcrud.model.Position;
import com.example.springbootcrud.model.dto.EmployeeFilterDTO;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.repository.EmployeeRepository;

@Service
public class EmployeeService {

  // si tu utilises l'injection par le constructeur, tu n'as pas besoin de @Autowired
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


  // il n'est pas recommandé d'utiliser plus de 7 attributs dans une méthode.
  //  tu peux utiliser un POJO ou un DTO
  public List<Employee> list(String firstName, String lastName, Position position, Date startDate, BigDecimal salary, Integer page,
                             Integer pageSize, String field) {

                              // j'ai vu que tu as utilisé les criteria.
                              // Spring Data t'offre un élément assez intéressant
    return employeeRepository.findEmployeesByProperties(firstName, lastName, position, startDate, salary, PageRequest.of(page, pageSize, Sort.by(field)));
  }

  public List<Employee> find(int page, int size, String field, EmployeeFilterDTO dto) {

    // je crée l'employé à rechercher
    Employee employee = new Employee();
    employee.setFirstName(dto.getFirstName());
    employee.setLastName(dto.getLastName());
    employee.setPosition(dto.getPosition());
    employee.setSalary(dto.getSalary());
    employee.setStartDate(dto.getStartDate());

    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withIgnoreNullValues();

    Example<Employee> example = Example.of(employee, exampleMatcher);

    return employeeRepository.findAll(example, PageRequest.of(page, size, Sort.by(field))).getContent();

  }

}
