package com.tw.core.service;

import com.tw.core.dao.RelationDao;
import com.tw.core.entity.Course;
import com.tw.core.entity.CourseCustomerRelation;
import com.tw.core.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService {

    @Autowired
    private RelationDao relationDao;

    public List<CourseCustomerRelation> getRelationsByCustomer(Customer customer) {
        return relationDao.getRelationsByCustomer(customer);
    }

    public void createRelation(CourseCustomerRelation courseCustomerRelation) {

        relationDao.createRelation(courseCustomerRelation);
    }

    public List<CourseCustomerRelation> getRelations() {

        return relationDao.getRelations();
    }

    public List<CourseCustomerRelation> getRelationsByCourse(Course course) {

        return relationDao.getRelationsByCourse(course);
    }
}
