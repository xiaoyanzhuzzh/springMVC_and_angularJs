package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getRegisterPage() {

        return new ModelAndView("register");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String nickName,
                              @RequestParam String name,
                              @RequestParam String role,
                              @RequestParam String password,
                              @RequestParam String gender,
                              @RequestParam int age,
                              @RequestParam String email,
                              HttpServletRequest request){


        Employee employee = new Employee(name, gender, age, email, role);
        employeeService.createEmployee(employee);

        if (!userService.getUserByName(nickName)) {

            User user = new User(nickName, EncryptionHelper.md5(password), employee);
            userService.createUser(user);
            request.getSession().setAttribute("currentUser", name);
        }

        return new ModelAndView("redirect:/employees/");
    }
}
