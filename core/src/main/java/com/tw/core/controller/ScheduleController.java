package com.tw.core.controller;

import com.google.gson.Gson;
import com.tw.core.entity.*;
import com.tw.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getSchedules(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null) {

            return new ModelAndView("redirect:/login");
        } else {

            List<Schedule> schedules = scheduleService.getSchedules();
            List<Schedule> publicSchedules = scheduleService.getPublicSchedules(schedules);
            List<Schedule> privateSchedules = scheduleService.getPrivateSchedules(schedules);


            ModelAndView modelAndView = new ModelAndView("schedules");

            modelAndView.addObject("publicSchedules", publicSchedules);
            modelAndView.addObject("privateSchedules", privateSchedules);
            return modelAndView;
        }
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView getCreateSchedulePage(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Course> courses = courseService.getCourses();

            return new ModelAndView("createSchedule", "courses", courseService.getPublicCourses(courses));
        }
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createSchedule(@RequestParam int courseId,
                                       @RequestParam String time) {

        Course course = courseService.getCourseById(courseId);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.createSchedule(new Schedule(time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value="/createPrivate", method= RequestMethod.GET)
    public ModelAndView getCreatePrivateCoursePage(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Course> courses = courseService.getCourses();
            ModelAndView modelAndView = new ModelAndView("createPrivateSchedule", "customers", customerService.getCustomers());
            modelAndView.addObject("coaches", employeeService.getAllCoaches());
            modelAndView.addObject("courses", courseService.getPublicCourses(courses));
            return modelAndView;
        }
    }

    @RequestMapping(value="/createPrivate", method= RequestMethod.POST)
    public ModelAndView createPrivateCourse(@RequestParam int customerId,
                                            @RequestParam int coachId,
                                            @RequestParam int courseId,
                                            @RequestParam String time) {

        Employee employee = employeeService.getEmployeeById(coachId);
        List<Course> courses = courseService.getCoursesByEmployee(employee);

        Boolean isExisted = false;
        for(Course course: courses) {
            isExisted = scheduleService.getScheduleByCourseAndTime(course, time);
            if(isExisted){
                break;
            }
        }

        if(!isExisted) {

            String name = customerService.getCustomerById(customerId).getName();
            Customer customer = new Customer(customerId, name, employee);
            customerService.updateCustomer(customer);

            Course currentCourse = courseService.getCourseById(courseId);
            Course newCourse  = new Course(currentCourse.getName(), employee);
            courseService.createCourse(newCourse);

            scheduleService.createSchedule(new Schedule(time, newCourse, customer));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public void getUpdateSchedulePage(@PathVariable int id,
                                        HttpServletResponse response) throws IOException {

//        if(request.getSession().getAttribute("currentUser") == null){
//
//            return new ModelAndView("redirect:/login");
//        } else {

            Schedule schedule = scheduleService.getScheduleById(id);

            Gson gson = new Gson();
            String schedulestr = "[" + gson.toJson(schedule) + ",";
            schedulestr += gson.toJson(courseService.getCourses()) + "]";

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(schedulestr.toString());

//            modelAndView.addObject("courses", courseService.getCourses());
//            return modelAndView;
//        }
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ModelAndView updateSchedule(@RequestParam int id,
                                       @RequestParam int courseId,
                                       @RequestParam String time) {

        Course course = courseService.getCourseById(courseId);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.updateSchedule(new Schedule(id, time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSchedule(@PathVariable int id){

        scheduleService.deleteScheduleById(id);
    }
}
