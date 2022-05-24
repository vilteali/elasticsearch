package com.ali.elasticsearch.repository;

import com.ali.elasticsearch.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends GenericElasticsearchRepository<Employee> {

    List<Employee> findByName(String name);

}
