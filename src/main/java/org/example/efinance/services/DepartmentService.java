package org.example.efinance.services;

import org.example.efinance.entities.Department;

import java.util.Optional;

public interface DepartmentService {
    Department createDepartment(Department department);

    Optional<Department> getDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    void deleteDepartmentById(Long id);

    Iterable<Department> getAllDepartments();
}
