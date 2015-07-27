package com.tw.core.service;

import com.tw.core.dao.EmployeeDao;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    public void createEmployee(Employee employee) {

        employeeDao.createEmployee(employee);
    }

    public List<Employee> getEmployees() {

        return employeeDao.getEmployees();
    }

    public void deleteEmployeeById(int id) {

        employeeDao.deleteEmployeeById(id);
    }

    public Employee getEmployeeById(int id) {

        return employeeDao.getEmployeeById(id);
    }

    public List<Employee> getAllCoaches() {

        return employeeDao.getAllCoaches();
    }

    public Boolean getEmployeeByNameAndType(String name, String role) {

        return employeeDao.getEmployeeByNameAndRole(name, role);
    }

    public void updateEmployee(Employee employee) {

        employeeDao.updateEmployee(employee);
    }
}
