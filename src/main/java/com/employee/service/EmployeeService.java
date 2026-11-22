package com.employee.service;
import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
