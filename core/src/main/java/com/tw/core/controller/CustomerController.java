package com.tw.core.controller;

import com.tw.core.entity.*;
import com.tw.core.service.*;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CourseService courseService;

    private JSONSerializer serializer = new JSONSerializer();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getAllCustomers(){

        List<Customer> customers = customerService.getCustomers();
        return  serializer.include("employee").serialize(customers);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createCustomer(@RequestBody Customer customer) {

        customerService.createCustomer(customer);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUpdateCustomerPage(@PathVariable int id){

        return new ModelAndView("updateCustomer", "customer", customerService.getCustomerById(id));
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ModelAndView getUpdateCustomerPage(@RequestParam int id,
                                          @RequestParam String name){


        customerService.updateCustomer(new Customer(id, name, null));
        return new ModelAndView("redirect:/customers");
    }
}
