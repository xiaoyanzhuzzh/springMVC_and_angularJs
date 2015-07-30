package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.service.CourseService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private JSONSerializer serializer = new JSONSerializer();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getCourses() {

        List<Course> courses = courseService.getCourses();
        return  serializer.include("employee").serialize(courses);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getCourseById(@RequestBody Course course) {

        if (!courseService.getCourseByName(course.getName())) {

            courseService.createCourse(course);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {

        course.setEmployee(course.getEmployee());
        courseService.updateCourse(course);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable int id) {

        courseService.deleteCourseById(id);
    }
}
