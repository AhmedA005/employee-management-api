package org.example.efinance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOBS")
public class Job {
    @Id
    @Column(name = "JOB_ID", nullable = false)
    private String id;
    @Column(name = "JOB_TITLE")
    private String jobTitle;


    @Column(name = "MIN_SALARY")
    private Long minsalary;
    @Column(name = "MAX_SALARY")
    private Long maxsalary;

    public Long getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(Long minsalary) {
        this.minsalary = minsalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(Long maxsalary) {
        this.maxsalary = maxsalary;
    }
}
