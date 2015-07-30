package com.tw.core.controller;

import com.tw.core.entity.*;
import com.tw.core.service.*;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer) {

        customer.setEmployee(customer.getEmployee());
        customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomerById(id);
    }
}
