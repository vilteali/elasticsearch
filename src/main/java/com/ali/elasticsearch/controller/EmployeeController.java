package com.ali.elasticsearch.controller;

import com.ali.elasticsearch.elastic.ElasticQueryFilter;
import com.ali.elasticsearch.service.EmployeeService;
import com.ali.elasticsearch.domain.Employee;
import com.ali.elasticsearch.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/elastic/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public void save(@RequestBody EmployeeDto employeeDto) {
        employeeService.save(employeeDto);
    }

    @GetMapping("{name}")
    public List<Employee> findByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("id/{id}")
    public EmployeeDto findById(@PathVariable String id) {
        Employee employee = employeeService.findById(id);

        return EmployeeDto.from(employee);
    }

    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("query")
    public SearchHits<Employee> findDocumentWithQuery(@Valid @RequestBody ElasticQueryFilter<EmployeeDto> elasticQuery) {

        return employeeService.findDocumentsWithQuery(elasticQuery);
    }

    @PutMapping()
    public void update(@RequestBody EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
    }
}
