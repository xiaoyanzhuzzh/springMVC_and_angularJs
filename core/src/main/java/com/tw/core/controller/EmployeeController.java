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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateEmployeePage(HttpServletRequest request) {
        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("createEmployee");
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEmployee(@RequestParam String name,
                                       @RequestParam String role,
                                       @RequestParam String gender,
                                       @RequestParam int age,
                                       @RequestParam String email){


        Employee employee = new Employee(name, gender, age, email, role);
        employeeService.createEmployee(employee);

        return new ModelAndView("redirect:/employees/");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUpdateEmployeePage(@PathVariable int id) {

        Employee employee = employeeService.getEmployeeById(id);
        return new ModelAndView("updateEmployee", "employee", employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView UpdateEmployee(@RequestParam int id,
                                       @RequestParam String name,
                                       @RequestParam String role,
                                       @RequestParam String gender,
                                       @RequestParam int age,
                                       @RequestParam String email){

        Employee employee = new Employee(id, name, gender, age, email, role);
        employeeService.updateEmployee(employee);

        return new ModelAndView("redirect:/employees/");
    }
}
