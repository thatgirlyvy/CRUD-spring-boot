package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, UUID>, EmployeeCustomRepository {

  Employee getById(UUID uuid);

}
