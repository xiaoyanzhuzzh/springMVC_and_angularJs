package com.tw.core.controller;

import com.google.gson.Gson;
import com.tw.core.entity.Employee;
import com.tw.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getEmployees(){

        List<Employee> employees = employeeService.getEmployees();
        Gson gson = new Gson();
        return gson.toJson(employees);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee employee){

        System.out.println(employee);
        employeeService.createEmployee(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void UpdateEmployee(@RequestBody Employee employee){

        employeeService.updateEmployee(employee);
    }
}
