package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.entity.Employee;
import com.tw.core.service.CourseService;
import com.tw.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView getCourses(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("courses", "courses", courseService.getCourses());
        }
    }

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView getCreateCoursePage(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("createCourse", "coaches", employeeService.getAllCoaches());
        }
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public ModelAndView createCourse(@RequestParam String name,
                                     @RequestParam int coachId) {

        Employee employee = employeeService.getEmployeeById(coachId);
        if(!courseService.getCourseByName(name)){

            courseService.createCourse(new Course(name, employee));
        }

        return new ModelAndView("redirect:/courses/");
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
