package org.example.efinance.services.impl;

import org.example.efinance.entities.Employee;
import org.example.efinance.repositories.EmployeeRepository;
import org.example.efinance.services.EmployeeService;
import org.example.efinance.util.ManagerHasSubordinatesException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setEmployeeId(id);
                    existingEmployee.setFirstName(employee.getFirstName());
                    existingEmployee.setLastName(employee.getLastName());
                    existingEmployee.setEmail(employee.getEmail());
                    existingEmployee.setPhoneNumber(employee.getPhoneNumber());
                    existingEmployee.setHireDate(employee.getHireDate());
                    existingEmployee.setJobId(employee.getJobId());
                    existingEmployee.setSalary(employee.getSalary());
                    existingEmployee.setCommissionPct(employee.getCommissionPct());
                    existingEmployee.setManagerId(employee.getManagerId());
                    existingEmployee.setDepartmentId(employee.getDepartmentId());
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.findById(id)
                .ifPresentOrElse(
                        employee -> employeeRepository.delete(employee),
                        () -> {
                            throw new RuntimeException("Employee not found with id: " + id);
                        }
                );
    }

    @Override
    public void deleteEmployeeAndSubordinates(Long id) {
        try {
            deleteIfNotManager(id);
        } catch (ManagerHasSubordinatesException e) {
            deleteSubordinates(id);
            employeeRepository.findById(id).ifPresent(employeeRepository::delete);
        }
    }

    private void deleteIfNotManager(Long id) throws ManagerHasSubordinatesException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        List<Employee> subordinates = employeeRepository.findByManagerId(id);
        if (!subordinates.isEmpty()) {
            throw new ManagerHasSubordinatesException(
                    "Employee " + id + " is a manager of " + subordinates.size() + " subordinates."
            );
        }

        employeeRepository.delete(employee);
    }

    private void deleteSubordinates(Long id) {
        List<Employee> subordinates = employeeRepository.findByManagerId(id);
        for (Employee subordinate : subordinates) {
            deleteSubordinates(subordinate.getEmployeeId());
            employeeRepository.delete(subordinate);
        }
    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return (List<Employee>) employeeRepository.findByFirstName(firstName);
    }

    @Override
    public Iterable<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryBetween(Long min, Long max) {
        return employeeRepository.findBySalaryRange(min, max);
    }
}
