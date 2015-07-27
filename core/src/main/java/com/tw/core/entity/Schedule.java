package com.tw.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {

    private int id;
    private String time;
    private Course course;
    private Customer customer;

    public Schedule() {
    }

    public Schedule(String time, Course course) {

        this.time = time;
        this.course = course;
    }

    public Schedule(int id, String time, Course course) {
        this.id = id;
        this.time = time;
        this.course = course;
    }

    public Schedule(String time, Course course, Customer customer) {
        this.time = time;
        this.course = course;
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    @ManyToOne(cascade = CascadeType.ALL)
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
