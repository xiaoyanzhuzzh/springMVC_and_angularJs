package com.tw.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "course_customer_relations")
public class CourseCustomerRelation {

    private int id;
    private Course course;
    private Customer customer;

    public CourseCustomerRelation() {
    }

    public CourseCustomerRelation(Course course, Customer customer) {
        this.course = course;
        this.customer = customer;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    @OneToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
