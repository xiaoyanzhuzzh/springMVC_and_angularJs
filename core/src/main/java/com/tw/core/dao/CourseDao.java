package com.tw.core.dao;

import com.tw.core.entity.Course;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao {

    public List<Course> getCourses(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = session.createQuery("from Course").list();

        session.close();
        return courses;
    }

    public void createCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();
        session.close();
    }

    public Boolean getCourseByName(String name) {

        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from User where name=?";
        Query query = session.createQuery(hql);

        query.setString(0, name);

        List<Course> courses = query.list();
        if(courses.size() != 0) {
            result = true;
        }
        session.close();

        return result;
    }

    public Course getCourseById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Course course = (Course) session.get(Course.class, id);
        session.close();
        return course;
    }

    public void updateCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.update(course);
        session.getTransaction().commit();
        session.close();
    }

    public List<Course> getCoursesByEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Course where employee=:employee";
        Query query = session.createQuery(hql);

        query.setParameter("employee", employee);
        return query.list();
    }

    public void deleteCourseById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Course course = (Course) session.load(Course.class, id);
        session.delete(course);

        session.getTransaction().commit();
        session.close();
    }
}
