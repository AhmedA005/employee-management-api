package org.example.efinance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false, unique = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "EMP_SEQ", allocationSize = 1)
    private Long employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "hire_date")
    private Date hireDate;
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "commission_pct")
    private double commissionPct;
    @Column(name = "manager_id")
    private Long managerId;
    @Column(name = "department_id")
    private Long departmentId;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

}
