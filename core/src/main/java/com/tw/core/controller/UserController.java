package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("users", "users", userService.getUsers());
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Employee> currentEmployees = new ArrayList<Employee>();

            List<Employee> employees = employeeService.getEmployees();

            System.out.println("");
            for(int i = 0; i < employees.size(); i++) {

                if(!userService.getUserByEmployee(employees.get(i))) {

                    currentEmployees.add(employees.get(i));
                }
            }
            return new ModelAndView("createUser", "employees", currentEmployees);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEmployee(@RequestParam String name,
                                       @RequestParam int employeeId,
                                       @RequestParam String password){

        if(!userService.getUserByName(name)) {

            Employee employee = employeeService.getEmployeeById(employeeId);
            User user = new User(name, EncryptionHelper.md5(password), employee);
            userService.createUser(user);
        }

        return new ModelAndView("redirect:/users/");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id){

        userService.deleteUserById(id);
        return "yes";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(HttpServletRequest request,
                                    @PathVariable int id){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            User user = userService.getUserById(id);
            return new ModelAndView("updateUser", "user", user);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView UpdateUser(@RequestParam int id,
                                   @RequestParam int employee_id,
                                   @RequestParam String nickName,
                                   @RequestParam String name,
                                   @RequestParam String role,
                                   @RequestParam String password,
                                   @RequestParam String gender,
                                   @RequestParam int age,
                                   @RequestParam String email){

        Employee employee = new Employee(employee_id, name, gender, age, email, role);
        userService.updateUser(new User(id, nickName, EncryptionHelper.md5(password), employee));

        return new ModelAndView("redirect:/users/");
    }
}
