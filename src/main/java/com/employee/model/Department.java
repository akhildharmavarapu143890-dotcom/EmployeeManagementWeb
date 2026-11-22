
package com.employee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "Department_id")
    private int departmentId;

    @Column(name = "Department_name")
    private String departmentName;

    public Department() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
