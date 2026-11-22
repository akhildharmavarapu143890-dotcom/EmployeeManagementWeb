package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/employee/*")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService service;

    @Override
    public void init() {

        WebApplicationContext context =
            WebApplicationContextUtils
            .getRequiredWebApplicationContext(
                getServletContext()
            );

        service = context.getBean(EmployeeService.class);
    }

    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || path.equals("/list")) {

            List<Employee> employees =
                service.getAllEmployees();

            request.setAttribute("employees", employees);

            request.getRequestDispatcher(
                "/viewEmployees.jsp"
            ).forward(request, response);

        } else if (path.equals("/edit")) {

            try {
                int id = Integer.parseInt(
                    request.getParameter("id")
                );

                Employee employee =
                    service.getEmployeeById(id);

                if (employee == null) {
                    response.sendError(404, "Employee not found");
                    return;
                }

                request.setAttribute("employee", employee);

                request.getRequestDispatcher(
                    "/updateEmployee.jsp"
                ).forward(request, response);

            } catch (NumberFormatException e) {
                response.sendError(400, "Invalid employee ID");
            }
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = request.getPathInfo();

        try {

            if ("/add".equals(path)) {

                Employee employee = readEmployee(request);

                validateEmployee(employee);

                service.addEmployee(employee);

                response.sendRedirect(
                    request.getContextPath()
                    + "/employee/list?success=added"
                );

            } else if ("/update".equals(path)) {

                int id = Integer.parseInt(
                    request.getParameter("employeeId")
                );

                Employee employee =
                    service.getEmployeeById(id);

                if (employee == null) {
                    response.sendError(404);
                    return;
                }

                Employee input = readEmployee(request);
                validateEmployee(input);

                employee.setEmployeeName(input.getEmployeeName());
                employee.setEmail(input.getEmail());
                employee.setPhone(input.getPhone());
                employee.setGender(input.getGender());
                employee.setDepartmentId(input.getDepartmentId());
                employee.setJobId(input.getJobId());
                employee.setJoiningDate(input.getJoiningDate());
                employee.setSalary(input.getSalary());

                service.updateEmployee(employee);

                response.sendRedirect(
                    request.getContextPath()
                    + "/employee/list?success=updated"
                );

            } else if ("/delete".equals(path)) {

                int id = Integer.parseInt(
                    request.getParameter("employeeId")
                );

                service.deleteEmployee(id);

                response.sendRedirect(
                    request.getContextPath()
                    + "/employee/list?success=deleted"
                );

            } else {
                response.sendError(404);
            }

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                "/addEmployee.jsp"
            ).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            throw new ServletException(
                "Employee operation failed :" + e.getMessage(),e
            );
        }
    }

    private Employee readEmployee(HttpServletRequest request) {

        Employee employee = new Employee();

        employee.setEmployeeName(
            request.getParameter("employeeName").trim()
        );

        employee.setEmail(
            request.getParameter("email").trim()
        );

        employee.setPhone(
            request.getParameter("phone").trim()
        );

        employee.setGender(
            request.getParameter("gender")
        );

        employee.setDepartmentId(
            Integer.parseInt(request.getParameter("departmentId"))
        );

        employee.setJobId(
            Integer.parseInt(request.getParameter("jobId"))
        );

        employee.setSalary(
            new BigDecimal(request.getParameter("salary"))
        );

        employee.setJoiningDate(
            LocalDate.parse(request.getParameter("joiningDate"))
        );

        return employee;
    }

    private void validateEmployee(Employee e) {

        if (e.getEmployeeName() == null ||
            e.getEmployeeName().length() < 3) {
            throw new IllegalArgumentException(
                "Employee name must have at least 3 characters."
            );
        }

        if (e.getEmail() == null ||
            !e.getEmail().matches(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"
            )) {
            throw new IllegalArgumentException(
                "Invalid email address."
            );
        }

        if (e.getPhone() == null ||
            !e.getPhone().matches("[0-9]{10,11}")) {
            throw new IllegalArgumentException(
                "Phone number must contain 10 or 11 digits."
            );
        }

        if (e.getSalary() == null ||
            e.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                "Salary cannot be negative."
            );
        }

        if (e.getDepartmentId() <= 0 ||
            e.getJobId() <= 0 ||
            e.getJoiningDate() == null) {
            throw new IllegalArgumentException(
                "Enter valid department, job and joining date."
            );
        }
    }
}
