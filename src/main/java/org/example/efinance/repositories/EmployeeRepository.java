package org.example.efinance.repositories;

import org.example.efinance.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.lastName = ?1")
    Iterable<Employee> findByLastName(String lastName);

    @Query(value = "SELECT * FROM EMPLOYEES WHERE manager_id = ?1", nativeQuery = true)
    List<Employee> findByManagerId(Long managerId);

    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1")
    Iterable<Employee> findByFirstName(String firstName);

    @Query("SELECT e FROM Employee e WHERE e.salary between :low and :high")
    List<Employee> findBySalaryRange(@Param("low") Long low, @Param("high") Long high);
}
