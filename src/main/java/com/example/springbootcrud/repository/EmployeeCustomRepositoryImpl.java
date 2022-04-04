package com.example.springbootcrud.repository;

import com.example.springbootcrud.model.Employee;
import com.example.springbootcrud.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{

  @Autowired
  MongoTemplate mongoTemplate;

  public List<Employee> findEmployeesByProperties(String firstName, String lastName,
                                                  Position position, Date startDate,
                                                  BigDecimal salary, Pageable page) {
    final Query query = new Query().with(page);
//     query.fields().include("id").include("name");
    final List<Criteria> criteria = new ArrayList<>();
    if (firstName != null && !firstName.isEmpty())
      criteria.add(Criteria.where("firstName").is(firstName));
    if (lastName != null && !lastName.isEmpty())
      criteria.add(Criteria.where("lastName").is(lastName));
    if (position != null )
      criteria.add(Criteria.where("position").in(position));
    if (startDate != null )
      criteria.add(Criteria.where("startDate").is(startDate));
    if (salary != null)
      criteria.add(Criteria.where("salary").is(salary));

    if (!criteria.isEmpty())
      query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
    return mongoTemplate.find(query, Employee.class);
  }
}
