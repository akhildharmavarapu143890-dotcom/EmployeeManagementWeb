package com.employee.dao;

import com.employee.model.Employee;
import jakarta.transaction.Transactional;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().persist(employee);
    }

    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM Employee", Employee.class)
            .list();
    }

    public Employee getEmployeeById(int id) {
        return sessionFactory.getCurrentSession()
            .get(Employee.class, id);
    }

    public void updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().merge(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);

        if (employee != null) {
            sessionFactory.getCurrentSession().remove(employee);
        }
    }
}
