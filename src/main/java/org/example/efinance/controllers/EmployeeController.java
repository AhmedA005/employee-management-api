package org.example.efinance.controllers;

import org.example.efinance.entities.Employee;
import org.example.efinance.services.EmployeeService;
import org.example.efinance.util.ManagerHasSubordinatesException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/by-firstname/{firstName}")
    @PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
    public Iterable<Employee> getEmployeeByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping(path = "/by-lastname/{lastName}")
    @PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
    public Iterable<Employee> getEmployeeByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeesByLastName(lastName);
    }

    @GetMapping(path = "/{low}/{high}")
    @PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
    public List<Employee> getEmployeesByRangeSalary(@PathVariable Long low, @PathVariable Long high){
        return employeeService.getEmployeesWithSalaryBetween(low, high);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setEmployeeId(null);
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws ManagerHasSubordinatesException {
        this.employeeService.deleteEmployeeAndSubordinates(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @DeleteMapping(path = "/manager/{id}")
//    public ResponseEntity<Void> deleteEmployeeAndSubordinates(@PathVariable Long id) {
//        employeeService.deleteEmployeeAndSubordinates(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
