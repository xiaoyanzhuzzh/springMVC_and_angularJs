package com.tw.core.controller;

import com.google.gson.Gson;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getUsers() {

        JSONSerializer serializer = new JSONSerializer();
        List<User> users = userService.getUsers();

//        for (int i = 0; i < users.size(); i++) {
//            Integer.toString(users.get(i).getEmployee().getAge());
//        }

        return  serializer.exclude("age").serialize(users);
//                Gson gson = new Gson();
//        String result = gson.toJson("[");
//        List<User> users = userService.getUsers();
//        for (int i = 0; i < users.size(); i++) {
//            users.get(i).setEmployee(users.get(i).getEmployee());
//            result += gson.toJson(users.get(i));
//        }
//        result += gson.toJson("]");
//        return result;
//        return gson.toJson(users);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Employee> currentEmployees = new ArrayList<Employee>();

            List<Employee> employees = employeeService.getEmployees();

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
