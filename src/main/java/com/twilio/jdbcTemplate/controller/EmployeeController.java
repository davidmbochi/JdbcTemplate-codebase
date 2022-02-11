package com.twilio.jdbcTemplate.controller;

import com.twilio.jdbcTemplate.dao.EmployeeDao;
import com.twilio.jdbcTemplate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @PostMapping("/add")
    public void addEmployee(@Valid @RequestBody Employee employee){
        employeeDao.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employee> findAll(){
        return employeeDao.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") int id){
        return employeeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("employee not found"));
    }

    @GetMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
         employeeDao.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public int updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") int id){
        return employeeDao.updateEmployee(id,employee);
    }
}
