package com.tw.core.service;

import com.tw.core.dao.CustomerDao;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {

        return customerDao.getCustomers();
    }

    public void createCustomer(Customer customer) {

        customerDao.createCustomer(customer);
    }

    public Customer getCustomerById(int id) {

        return customerDao.getCustomerById(id);
    }

    public void updateCustomer(Customer customer) {

        customerDao.updateCustomer(customer);
    }

    public Customer getCustomerByEmployee(Employee employee) {

        return customerDao.getCustomerByEmployee(employee);
    }
}
