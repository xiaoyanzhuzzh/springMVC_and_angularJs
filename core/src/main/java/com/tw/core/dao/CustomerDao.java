package com.tw.core.dao;

import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {
    public List<Customer> getCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery("from Customer ").list();

        session.close();
        return customers;
    }

    public void createCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    public Customer getCustomerById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Customer customer = (Customer) session.get(Customer.class, id);
        session.close();
        return customer;
    }

    public void updateCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }

    public Customer getCustomerByEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Customer where employee=:employee";
        Query query = session.createQuery(hql);


        query.setParameter("employee", employee);

        List<Customer> customers = query.list();
        session.close();

        return customers.get(0);
    }
}
