package com.tw.core.service;

import com.tw.core.dao.CourseDao;
import com.tw.core.entity.Course;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private RelationService relationService;

    public List<Course> getCourses() {

        return courseDao.getCourses();
    }

    public void createCourse(Course course) {

        courseDao.createCourse(course);
    }

    public Boolean getCourseByName(String name) {

        return courseDao.getCourseByName(name);
    }

    public Course getCourseById(int id) {

        return courseDao.getCourseById(id);
    }

    public void updateCourse(Course course) {

        courseDao.updateCourse(course);
    }

    public List<Course> getCoursesByEmployee(Employee employee) {

        return courseDao.getCoursesByEmployee(employee);
    }

    public List<Course> getPublicCourses(List<Course> courses) {

        List<Course> publicCourses = new ArrayList<Course>();
        for (Course course : courses) {
            if (relationService.getRelationsByCourse(course).size() == 0) {

                publicCourses.add(course);
            }
        }

        return publicCourses;
    }

    public void deleteCourseById(int id) {

        courseDao.deleteCourseById(id);
    }
}
