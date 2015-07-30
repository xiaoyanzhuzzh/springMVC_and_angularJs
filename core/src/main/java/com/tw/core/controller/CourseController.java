package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.CourseService;
import com.tw.core.service.EmployeeService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private EmployeeService employeeService;

    private JSONSerializer serializer = new JSONSerializer();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getCourses() {

        List<Course> courses = courseService.getCourses();
        return  serializer.include("employee").serialize(courses);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getUserById(@RequestBody Course course) {

        if (!courseService.getCourseByName(course.getName())) {

            courseService.createCourse(course);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Course course) {

        course.setEmployee(course.getEmployee());
        courseService.updateCourse(course);
    }


    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public ModelAndView getUpdateSchedulePage(@PathVariable int id,
                                              HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            ModelAndView modelAndView = new ModelAndView("updateCourse", "course", courseService.getCourseById(id));
            modelAndView.addObject("coaches", employeeService.getAllCoaches());
            return modelAndView;
        }
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ModelAndView updateSchedule(@RequestParam int id,
                                 @RequestParam String name,
                                 @RequestParam int coachId) {

        Employee employee = employeeService.getEmployeeById(coachId);

        if(!courseService.getCourseByName(name)) {

            courseService.updateCourse(new Course(id, name, employee));
        }

        return new ModelAndView("redirect:/courses/");
    }

}
