
package com.employee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_title")
    private String jobTitle;

    public Job() {
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
