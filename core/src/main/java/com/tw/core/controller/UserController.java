package com.tw.core.controller;

import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/users")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    private JSONSerializer serializer = new JSONSerializer();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getUsers() {

        List<User> users = userService.getUsers();
        return  serializer.include("employee").serialize(users);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getUserById(@RequestBody User user){

        if(!userService.getUserByName(user.getName())) {

            user.setPassword(EncryptionHelper.md5(user.getPassword()));
            userService.createUser(user);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUserById(@PathVariable int id){

        return userService.getUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id){

        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {

        employeeService.updateEmployee(user.getEmployee());

        user.setPassword(EncryptionHelper.md5(user.getPassword()));
        userService.updateUser(user);
    }

}
