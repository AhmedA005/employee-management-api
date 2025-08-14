package org.example.efinance.services;

import org.example.efinance.entities.Employee;
import org.example.efinance.util.ManagerHasSubordinatesException;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployeeById(Long id);
    Iterable<Employee> getAllEmployees();
    List<Employee> getEmployeesByFirstName(String firstName);
    Iterable<Employee> getEmployeesByLastName(String lastName);
    List<Employee> getEmployeesWithSalaryBetween(Long min, Long max);
    void deleteEmployeeAndSubordinates(Long id) throws ManagerHasSubordinatesException;
}
