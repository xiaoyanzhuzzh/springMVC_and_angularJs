package com.tw.core.dao;

import com.tw.core.entity.Course;
import com.tw.core.entity.CourseCustomerRelation;
import com.tw.core.entity.Customer;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelationDao {
    public List<CourseCustomerRelation> getRelationsByCustomer(Customer customer) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from CourseCustomerRelation where customer=:customer";
        Query query = session.createQuery(hql);

        query.setParameter("customer", customer);

        return query.list();
    }

    public void createRelation(CourseCustomerRelation courseCustomerRelation) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(courseCustomerRelation);
        session.getTransaction().commit();
        session.close();
    }

    public List<CourseCustomerRelation> getRelations() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<CourseCustomerRelation> relations = session.createQuery("from CourseCustomerRelation").list();

        session.close();
        return relations;
    }

    public List<CourseCustomerRelation> getRelationsByCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from CourseCustomerRelation where course=:course";
        Query query = session.createQuery(hql);

        query.setParameter("course", course);

        return query.list();

    }
}
