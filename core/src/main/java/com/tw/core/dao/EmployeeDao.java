package com.tw.core.dao;

import com.tw.core.entity.Employee;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    public void createEmployee(Employee employee){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(employee);
        session.getTransaction().commit();
        session.close();
    }


    public List<Employee> getEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("from Employee ").list();

        session.close();
        return employees;
    }

    public void deleteEmployeeById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee = (Employee) session.load(Employee.class, id);
        session.delete(employee);

        session.getTransaction().commit();
        session.close();
    }

    public Employee getEmployeeById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = (Employee) session.get(Employee.class, id);
        session.close();
        return employee;
    }

    public List<Employee> getAllCoaches() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Employee where role=?";
        Query query = session.createQuery(hql);

        query.setString(0, "coach");

        List<Employee> coaches = query.list();

        session.close();
        return coaches;

    }

    public Boolean getEmployeeByNameAndRole(String name, String role) {
        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Employee where name=? and role=?";
        Query query = session.createQuery(hql);

        query.setString(0, name);
        query.setString(1, role);

        List<Employee> employees = query.list();
        if(employees.size() != 0) {
            result = true;
        }
        session.close();

        return result;
    }

    public void updateEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.update(employee);
        session.getTransaction().commit();
        session.close();
    }
}
