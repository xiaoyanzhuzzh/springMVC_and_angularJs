package com.tw.core.controller;

import com.tw.core.entity.*;
import com.tw.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Customer> customers = customerService.getCustomers();
            return new ModelAndView("customers", "customers", customers);
        }
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public ModelAndView getCreateCustomerPage(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            ModelAndView modelAndView = new ModelAndView("createCustomer");
            modelAndView.addObject("employees", employeeService.getAllCoaches());
            modelAndView.addObject("courses", courseService.getCourses());

            return modelAndView;
        }
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ModelAndView createCustomer(@RequestParam String name){

        Customer customer = new Customer(name, null);
        customerService.createCustomer(customer);

        return new ModelAndView("redirect:/customers/");
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
